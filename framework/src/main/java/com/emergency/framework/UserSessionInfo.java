package com.emergency.framework;



import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户会话信息实体类
 */
public class UserSessionInfo {
    private String userId;
    private String userName;
    @JsonIgnore
    private String roleId;
    private String roleName;
    private String loginIp;
    private Date   loginTime;
    private String sessionId;
    @JsonIgnore
    private ConcurrentHashMap<String,Object> attributes = new ConcurrentHashMap<>();

    public Object getAttribute(String key){
        return attributes.get(key);
    }

    public void setAttribute(String key,Object value){
        attributes.put(key,value);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
