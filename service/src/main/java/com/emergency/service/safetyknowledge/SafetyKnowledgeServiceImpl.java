package com.emergency.service.safetyknowledge;

import com.emergency.dao.mybatis.mapper.safetyknowledge.SafetyKnowledgeMapper;
import com.emergency.module.entity.SafetyKnowledge;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SafetyKnowledgeServiceImpl implements SafetyKnowledgeService {


    @Resource
    private SafetyKnowledgeMapper safetyKnowledgeMapper;


    @Override
    public SafetyKnowledge get(String id) {
        SafetyKnowledge safetyKnowledge = new SafetyKnowledge(id);
        return safetyKnowledgeMapper.getById(safetyKnowledge);
    }

    @Override
    public List<SafetyKnowledge> listSafetyKnowledge(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SafetyKnowledge> safetyKnowledges = safetyKnowledgeMapper.get(new SafetyKnowledge());
        return safetyKnowledges;
    }
}
