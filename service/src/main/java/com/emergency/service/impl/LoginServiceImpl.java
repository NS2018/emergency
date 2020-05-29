package com.emergency.service.impl;

import com.emergency.dao.mybatis.mapper.common.SysUserMapper;
import com.emergency.framework.HttpSession;
import com.emergency.framework.UserSessionInfo;
import com.emergency.module.entity.SysUser;
import com.emergency.service.LoginService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public Object login(HttpServletRequest req,String loginName, String password) {
        SysUser sysUser = sysUserMapper.getByLogonnameAndPassword(loginName, password);
        if(sysUser == null){
            return -1;//用户不存在
        }
        if("4".equals(sysUser.getRoleId())) { //用户角色id必须是4，只允许第三方用户
            UserSessionInfo userSessionInfo = new UserSessionInfo();
            userSessionInfo.setUserId(sysUser.getId());
            userSessionInfo.setUserName(sysUser.getLogonName());
            userSessionInfo.setLoginTime(new Date());
            userSessionInfo.setRoleId(sysUser.getRoleId());
            userSessionInfo.setRoleName("");
            userSessionInfo.setLoginIp(req.getRemoteAddr());
            String sessionId = generateSessionId(sysUser.getLogonName(), sysUser.getPassword());
            HttpSession.saveUserSession(sessionId, userSessionInfo); //将用户信息设置到session中
            userSessionInfo.setSessionId(sessionId);
            return userSessionInfo;
        }else{
            return -2;//用户无权限
        }
    }

    /**
     * 生成sessionId
     * userName和password用作生成sessionId的源数据
     * @param userName 用户名
     * @param password 密码
     * @return String
     */
    private String generateSessionId(String userName,String password){
        String uuid = UUID.randomUUID().toString();
        String src = userName + password + uuid;
        byte[] encode = Base64.getEncoder().encode(src.getBytes());
        String sessionId = Md5Crypt.md5Crypt(encode);
        return sessionId;
    }
}
