package com.emergency.service.impl;

import com.emergency.dao.mybatis.mapper.common.BoxAttachmentMapper;
import com.emergency.dao.mybatis.mapper.emergencytask.EmergencyTaskMapper;
import com.emergency.dao.mybatis.mapper.handlecase.HandleCaseActionMapper;
import com.emergency.dao.mybatis.mapper.handlecase.HandleCaseMapper;
import com.emergency.module.entity.BoxAttachment;
import com.emergency.module.entity.EmergencyTask;
import com.emergency.module.entity.HandleCase;
import com.emergency.module.entity.HandleCaseAction;
import com.emergency.service.HandleCaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class HandleCaseServiceImpl implements HandleCaseService {

    @Resource
    private HandleCaseMapper handleCaseMapper;

    @Resource
    private EmergencyTaskMapper emergencyTaskMapper;

    @Resource
    private HandleCaseActionMapper handleCaseActionMapper;

    @Resource
    private BoxAttachmentMapper boxAttachmentMapper;

    @Override
    public List<HandleCase> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<HandleCase> handleCases = handleCaseMapper.get(new HandleCase());
        return handleCases;
    }

    @Override
    public HandleCase get(String id) {
        HandleCase handleCase = new HandleCase(id);
        return handleCaseMapper.getById(handleCase);
    }

    @Override
    public boolean isUsing(String id) {
        EmergencyTask emergencyTask = EmergencyTask.builder()
                .handlePreplanId(id)
                .build();
        int count = emergencyTaskMapper.get(emergencyTask).size();
        if (count > 0)
            return true;
        else
            return false;
    }

    @Override
    @Transactional
    public void delete(String id) {
        //删除关联的附件
        HandleCaseAction handleCaseAction = HandleCaseAction.builder()
                .caseId(id)
                .build();
        List<HandleCaseAction> handleCaseActions = handleCaseActionMapper.get(handleCaseAction);
        for (HandleCaseAction handleCase : handleCaseActions) {
            BoxAttachment boxAttachment = BoxAttachment.builder()
                    .attachmentBelongs("BoxCaseAction")
                    .belongsId(handleCase.getId())
                    .build();
            boxAttachmentMapper.del(boxAttachment);
        }

        //删除案例动作（Action）
        handleCaseActionMapper.del(handleCaseAction);

        //删除该案例
        handleCaseMapper.del(new HandleCase(id));
    }
}
