package com.emergency.service;

import com.emergency.module.entity.HandleCase;

import java.util.List;

/**
 * 应急处置案例
 */
public interface HandleCaseService {


    List<HandleCase> getList(int pageNum, int pageSize);

    HandleCase get(String id);

    boolean isUsing(String id);

    void delete(String id);
}
