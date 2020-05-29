package com.emergency.dao.mybatis.mapper.handlepreplan;


import com.emergency.dao.mybatis.common.GeneralMapper;
import com.emergency.module.entity.HandlePrePlan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface HandlePrePlanMapper extends GeneralMapper<HandlePrePlan> {

    @Delete("delete from box_preplanact_boxtasktype_mapping  where handle_action_id in\n" +
            " (select id FROM box_handle_preplan_action WHERE  handlePrePlanId = ${handlePrePlanId})")
    void deleteJobs(@Param("handlePrePlanId") String handlePrePlanId);
}