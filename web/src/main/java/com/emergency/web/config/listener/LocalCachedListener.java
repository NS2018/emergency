package com.emergency.web.config.listener;

import com.emergency.framework.localcache.LocalCachedUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * LocalCachedListener用于在servlet容器启动时启动LocalCached清理线程，
 * Servlet容器关闭时，销毁LocalCached清理线程。
 */

@WebListener
public class LocalCachedListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LocalCachedUtil.startCache();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LocalCachedUtil.stopCache();
    }
}
