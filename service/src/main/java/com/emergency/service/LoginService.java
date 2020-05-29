package com.emergency.service;


import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    Object login(HttpServletRequest req, String loginName, String password);

    
}
