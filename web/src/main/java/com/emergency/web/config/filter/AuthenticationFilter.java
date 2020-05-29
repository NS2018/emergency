package com.emergency.web.config.filter;

import com.alibaba.fastjson.JSON;
import com.emergency.framework.HttpSession;
import com.emergency.framework.UserSessionInfo;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 用户认证过滤器，过滤所有请求
 *
 */

@WebFilter(filterName = "AuthenticationFilter",urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    /**
     * 保存在请求头中的session header key常量
     */
    public static final String HTTP_SESSION_HEADER = "SessionId";

    /**
     * 保存在request 参数中的session key常量
     */
    public static final String HTTP_SESSION_REQUEST = "sessionId";

    private Set<String> excludeUri = new HashSet();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUri.add("/api/login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String requestURI = req.getRequestURI();
        if(isExcludeUri(requestURI)){ //如果是排除在外的uri，直接通过
            filterChain.doFilter(req,resp);
            return;
        }

        String sessionId = req.getHeader(HTTP_SESSION_HEADER);

        if(StringUtils.isEmpty(sessionId)){ //如果header中取不到sessionId,去request的参数中取
            sessionId = req.getParameter(HTTP_SESSION_REQUEST);
        }
        if(StringUtils.isEmpty(sessionId)){ //如果request中也取不到sessionId,用户未登录
            ApiResponseData data = ApiResponse.noLogin();
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(data));
            resp.flushBuffer();
            return;
        }
        UserSessionInfo userSession = HttpSession.getUserSession(sessionId);
        if(userSession == null){ //会话过期
            ApiResponseData data = ApiResponse.fail("会话过期,请重新登录！");
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(data));
            resp.flushBuffer();
        }else{
            filterChain.doFilter(req,resp);
        }

    }

    @Override
    public void destroy() {

    }

    private boolean isExcludeUri(String reqUri) {
        if(reqUri == null)
            return false;
        Iterator<String> iterator = excludeUri.iterator();
        while(iterator.hasNext()){
            if(reqUri.matches(iterator.next())){
                return true;
            }
        }
        return false;
    }


}
