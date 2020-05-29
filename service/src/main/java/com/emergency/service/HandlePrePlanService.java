package com.emergency.service;

import com.emergency.module.entity.Dictionary;
import com.emergency.module.entity.HandlePrePlan;

import java.util.List;

/**
 * 应急处置预案
 */
public interface HandlePrePlanService {

    HandlePrePlan get(String id);

    List<HandlePrePlan> getList(int pageNum, int pageSize);

    /**
     * 删除应急处置预案
     * @param id 应急处置预案id
     */
    void delete(String id);

    /**
     * 获取所有处置预案的类型
     * @return 字典列表
     */
    List<Dictionary> getAllHandlePrePlanType();

}
