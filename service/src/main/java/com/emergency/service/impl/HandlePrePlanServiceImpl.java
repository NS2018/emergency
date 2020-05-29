package com.emergency.service.impl;

import com.emergency.dao.mybatis.mapper.common.BoxLabelMapper;
import com.emergency.dao.mybatis.mapper.common.DictionaryMapper;
import com.emergency.dao.mybatis.mapper.handlepreplan.HandlePrePlanActionMapper;
import com.emergency.dao.mybatis.mapper.handlepreplan.HandlePrePlanMapper;
import com.emergency.module.entity.BoxLabel;
import com.emergency.module.entity.Dictionary;
import com.emergency.module.entity.HandlePrePlan;
import com.emergency.module.entity.HandlePrePlanAction;
import com.emergency.service.HandlePrePlanService;
import com.emergency.service.LuceneService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandlePrePlanServiceImpl implements HandlePrePlanService {

    @Resource
    private HandlePrePlanMapper handlePrePlanMapper;
    @Resource
    private BoxLabelMapper boxLabelMapper;
    @Resource
    private LuceneService luceneService;
    @Resource
    private HandlePrePlanActionMapper handlePrePlanActionMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public HandlePrePlan get(String id) {
        HandlePrePlan handlePrePlan = handlePrePlanMapper.getById(new HandlePrePlan(id));
        return handlePrePlan;
    }

    @Override
    public List<HandlePrePlan> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<HandlePrePlan> handlePrePlans = handlePrePlanMapper.get(new HandlePrePlan());
        return handlePrePlans;
    }

    @Override
    @Transactional
    public void delete(String id) {
        //删除该应急预案下的标签
        BoxLabel boxLabel = BoxLabel.builder()
                .labelBelongs("BoxHandlePreplan")
                .belongsId(id)
                .build();
        boxLabelMapper.del(boxLabel);
        //删除作业
        handlePrePlanMapper.deleteJobs(id);
        //删除索引
        luceneService.deleteIndexById(id);
        //删除预案动作
        HandlePrePlanAction handlePrePlanAction = HandlePrePlanAction.builder()
                .handlePrePlanId(id)
                .build();
        handlePrePlanActionMapper.del(handlePrePlanAction);
        //删除该预案
        handlePrePlanMapper.del(new HandlePrePlan(id));
    }

    @Override
    public List<Dictionary> getAllHandlePrePlanType() {
        Dictionary dictionary = Dictionary.builder()
                .dictionaryType("preplanType")
                .build();
        return dictionaryMapper.get(dictionary);
    }
}
