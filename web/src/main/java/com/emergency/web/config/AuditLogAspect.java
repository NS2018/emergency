package com.emergency.web.config;

import com.emergency.dao.mybatis.mapper.auditlog.AuditLogMapper;
import com.emergency.framework.HttpSession;
import com.emergency.framework.UserSessionInfo;
import com.emergency.module.annotation.Log;
import com.emergency.module.entity.AuditLog;
import com.emergency.module.enums.AuditLogTypeEnum;
import com.emergency.web.ApiResponseData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * 日志切面
 */
@Component
@Aspect
public class AuditLogAspect implements Ordered {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal<AuditLog> AUDIT_LOCAL_THREAD_LOCAL = ThreadLocal.withInitial(AuditLog::new);

    @Resource
    private AuditLogMapper auditLogMapper;

    @Pointcut("@annotation(com.emergency.module.annotation.Log)")
    public void logAspect(){

    }

    @Before("logAspect()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Log annotation = signature.getMethod().getAnnotation(Log.class);
        if(annotation == null){
            return;
        }
        LOG.info("进入方法前执行...");
        AuditLog auditLog = AUDIT_LOCAL_THREAD_LOCAL.get();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String sessionId = HttpSession.getSessionId(request);

        auditLog.setDevGuid("");
        auditLog.setId(UUID.randomUUID().toString().replace("-",""));
        AuditLogTypeEnum logType = annotation.value();
        String desc = annotation.desc();
        String type = logType.name();
        String operation = logType.getOperation();
        auditLog.setLoginType(type);
        auditLog.setOperation(operation);
        auditLog.setOperContent(desc);
        String loginIp = request.getRemoteAddr();
        auditLog.setUserIp(loginIp);
        if(StringUtils.isEmpty(sessionId)){
            return;
        }
        UserSessionInfo userSession = HttpSession.getUserSession(sessionId);
        String userName = userSession.getUserName();
        auditLog.setUserName(userName);
    }

    @Around("logAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{

        Object result = null;
        AuditLog auditLog = AUDIT_LOCAL_THREAD_LOCAL.get();
        try {
            result = joinPoint.proceed();

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            String sessionId = HttpSession.getSessionId(request);

            //是否不是登录
            auditLog.setEnding(1);
            //登录时请求头sessionId为空
            if(StringUtils.isEmpty(sessionId)){
                auditLog.setEnding(0);
                ApiResponseData data = (ApiResponseData) result;
                if(data.getCode() == 1){
                    sessionId = ((UserSessionInfo)((ApiResponseData)result).getData()).getSessionId();
                }else {
                    return result;
                }
            }
            if(StringUtils.isEmpty(sessionId)){
                //是否是退出
                auditLog.setFlag(1);
                return result;
            }
            auditLog.setFlag(0);
            UserSessionInfo userSession = HttpSession.getUserSession(sessionId);
            String userName = userSession.getUserName();
            auditLog.setUserName(userName);
            String operContent = auditLog.getOperContent();
            operContent.concat("成功");
            auditLog.setOperContent(operContent);
            return result;
        }catch (Exception ex){
            auditLog.setFlag(0);
            String operContent = auditLog.getOperContent();
            operContent.concat("失败");
            auditLog.setOperContent(operContent);
            throw  ex;
        }
    }

    @AfterReturning(returning = "ret", pointcut = "logAspect()")
    public void doAfterReturning(Object ret){
        LOG.info("方法的返回值 : " + ret);
    }

    @AfterThrowing("logAspect()")
    public void doAfterThrowing(JoinPoint joinPoint){
        LOG.info("方法异常时执行.....");
    }

    @After("logAspect()")
    public void doAfterAll(JoinPoint joinPoint) {
        LOG.info("方法最后执行.....");
        AuditLog auditLog = AUDIT_LOCAL_THREAD_LOCAL.get();
        Date logTime = new Date();
        auditLog.setLogTime(logTime);
        auditLog.setCreateTime(logTime.getTime());
        saveLog(auditLog);
        AUDIT_LOCAL_THREAD_LOCAL.remove();
    }

    private void saveLog(AuditLog auditLog) {
        auditLogMapper.save(auditLog);
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
