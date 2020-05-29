package com.emergency.service;

/**
 * 全文检索服务类
 */
public interface LuceneService {

    /**
     * 删除索引
     * @param id id
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteIndexById(String id);
}
