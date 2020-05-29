package com.emergency.framework;

import com.alibaba.fastjson.JSON;
import com.emergency.framework.localcache.LocalCachedUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义session 存储用户登录信息
 */
public class HttpSession {
    /**
     * session 超时，单位ms
     */
   private static final long SESSION_TIMEOUT = 30*60*1000;
    /**
     * session 缓存块key
     */
   public static final String SESSION_CACHE_BLOCK_KEY = "session_block";
    /**
     * 保存在请求头中的session header key常量
     */
    public static final String HTTP_SESSION_HEADER = "SessionId";

    /**
     * 保存在request 参数中的session key常量
     */
    public static final String HTTP_SESSION_REQUEST = "sessionId";

    /**
     * 保存用户会话信息
     * @param sessionId 会话id
     * @param sessionInfo 会话信息  {@link UserSessionInfo}
     *
     * @throws IllegalArgumentException
     * @see LocalCachedUtil
     */
   public static void saveUserSession(String sessionId, UserSessionInfo sessionInfo){
       if(StringUtils.isEmpty(sessionId)){
           throw new IllegalArgumentException("sessionId 不能为空！");
       }
       if(sessionInfo == null){
           throw new IllegalArgumentException("sessionInfo 不能为空！");
       }
      //将用户会话保存到缓存中
       LocalCachedUtil.put(SESSION_CACHE_BLOCK_KEY,sessionId, JSON.toJSONString(sessionInfo),SESSION_TIMEOUT);
   }

    /**
     * 获取用户会话信息
     *
     * @param sessionId 会话ID
     * @return userSessionInfo
     *
     * @throws IllegalArgumentException
     */
   public static UserSessionInfo getUserSession(String sessionId){
       if(StringUtils.isEmpty(sessionId)){
           throw new IllegalArgumentException("sessionId 不能为空！");
       }
       UserSessionInfo userSessionInfo = LocalCachedUtil.get(SESSION_CACHE_BLOCK_KEY, sessionId, UserSessionInfo.class);
       return userSessionInfo;
   }

    /**
     * 清除会话信息
     * @param sessionId 会话ID
     * @throws IllegalArgumentException
     */
    public static void clearUserSession(String sessionId){
        if(StringUtils.isEmpty(sessionId)){
            throw new IllegalArgumentException("sessionId 不能为空！");
        }
        LocalCachedUtil.remove(SESSION_CACHE_BLOCK_KEY,sessionId);
    }

    /**
     * 从request中获取sessionId
     * @param req HttpServletRequest
     * @return sessionId
     */
    public static String getSessionId(HttpServletRequest req){
        String sessionId = req.getHeader(HTTP_SESSION_HEADER);

        if(StringUtils.isEmpty(sessionId)){ //如果header中取不到sessionId,去request的参数中取
            sessionId = req.getParameter(HTTP_SESSION_REQUEST);
        }
        return sessionId;
    }


}
