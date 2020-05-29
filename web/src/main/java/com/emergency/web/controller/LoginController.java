package com.emergency.web.controller;

import com.emergency.framework.HttpSession;
import com.emergency.module.annotation.Log;
import com.emergency.module.enums.AuditLogTypeEnum;
import com.emergency.module.vo.SysUserVO;
import com.emergency.service.LoginService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.emergency.web.config.filter.AuthenticationFilter;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param req HttpServletRequest
     * @param sysUser 登录信息
     * @return ApiResponseData
     */
    @PostMapping("/login")
    @Log(value = AuditLogTypeEnum.ADM_IN_OUT,desc = "登录")
    public ApiResponseData login(HttpServletRequest req, @RequestBody SysUserVO sysUser){
        if(sysUser == null){
            return ApiResponse.fail("参数异常");
        }
        String userName = sysUser.getUserName();
        String password = sysUser.getPassword();
        if(StringUtils.isEmpty(userName))
            return ApiResponse.paramIsNull("userName");
        if(StringUtils.isEmpty(password))
            return ApiResponse.paramIsNull("password");
        Object statues = loginService.login(req,userName, password);
        if(statues.equals(-1)){
            return ApiResponse.fail("登录失败，用户名或密码错误！");
        }else if(statues.equals(-2)){
            return ApiResponse.fail("登录失败，用户无权限！");
        }
        return ApiResponse.ok("登录成功！",statues);
    }

    /**
     * 登出
     *
     * @param req HttpServletRequest
     * @param sessionId 会话id
     * @return ApiResponseData
     */
    @GetMapping("/logout")
    @Log(value = AuditLogTypeEnum.ADM_IN_OUT,desc = "退出")
    public ApiResponseData logout(HttpServletRequest req, @Param("sessionId") String sessionId){
        //优先使用header中的sessionId
        String sId = req.getHeader(AuthenticationFilter.HTTP_SESSION_HEADER);
        if(StringUtils.isEmpty(sId)){
            sId = sessionId;
        }
        if(!StringUtils.isEmpty(sId)){
            HttpSession.clearUserSession(sId); //清除会话信息
            return ApiResponse.ok("登出成功！");
        }
        return null;
    }


}
