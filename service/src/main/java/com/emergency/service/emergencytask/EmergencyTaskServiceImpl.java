package com.emergency.service.emergencytask;

import com.emergency.dao.mybatis.mapper.common.PreserveFileMapper;
import com.emergency.dao.mybatis.mapper.emergencytask.EmergencyTaskActionFileMappingMapper;
import com.emergency.dao.mybatis.mapper.emergencytask.EmergencyTaskActionMapper;
import com.emergency.dao.mybatis.mapper.emergencytask.EmergencyTaskAffectSoftHardwareMapper;
import com.emergency.dao.mybatis.mapper.emergencytask.EmergencyTaskMapper;
import com.emergency.framework.util.FileUtils;
import com.emergency.module.entity.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmergencyTaskServiceImpl implements EmergencyTaskService {

    @Value("${preserveFilePath}")
    private String preserveFilePath;

    @Resource
    private EmergencyTaskMapper emergencyTaskMapper;

    @Resource
    private EmergencyTaskAffectSoftHardwareMapper emergencyTaskAffectSoftHardwareMapper;

    @Resource
    private EmergencyTaskActionMapper emergencyTaskActionMapper;

    @Resource
    private EmergencyTaskActionFileMappingMapper emergencyTaskActionFileMappingMapper;

    @Resource
    private PreserveFileMapper preserveFileMapper;

    @Override
    public EmergencyTask get(String taskId) {
        EmergencyTask emergencyTask = new EmergencyTask(taskId);
        EmergencyTask task = emergencyTaskMapper.getById(emergencyTask);
        return task;
    }

    @Override
    public List<EmergencyTask> getEmergencyTaskByName(String taskName) {
        EmergencyTask emergencyTask = EmergencyTask.builder()
                .taskName(taskName)
                .build();
        return emergencyTaskMapper.get(emergencyTask);
    }

    @Override
    public List<EmergencyTask> listEmergencyTask(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<EmergencyTask> emergencyTasks = emergencyTaskMapper.get(new EmergencyTask());
        return emergencyTasks;
    }

    @Override
    @Transactional
    public void deleteEmergencyTask(String taskId) {
        //第一步：先通过taskId删除应急任务EmergencyTask
        EmergencyTask emergencyTask = EmergencyTask.builder()
                .id(taskId).build();
        emergencyTaskMapper.del(emergencyTask);

        //第二步：删除应急任务对应的受影响的软硬件
        EmergencyTaskAffectSoftHardware emergencyTaskAffectSoftHardware =
                EmergencyTaskAffectSoftHardware.builder().taskId(taskId).build();
        emergencyTaskAffectSoftHardwareMapper.del(emergencyTaskAffectSoftHardware);

        //第三步：删除应急任务对应的动作和对应的附件
        //3.1 先查询出所有的动作,为了获取Action对应的附件
        EmergencyTaskAction taskAction = EmergencyTaskAction.builder()
                .taskId(taskId)
                .build();

        List<EmergencyTaskAction> emergencyTaskActions = emergencyTaskActionMapper.get(taskAction);
        for (EmergencyTaskAction emergencyTaskAction : emergencyTaskActions) {
            String actionId = emergencyTaskAction.getId();
            EmergencyTaskActionFileMapping emergencyTaskActionFileMapping =
                    EmergencyTaskActionFileMapping.builder()
                    .taskActionId(actionId).build();

            List<EmergencyTaskActionFileMapping> taskActionFileMappings =
                    emergencyTaskActionFileMappingMapper.get(emergencyTaskActionFileMapping);

            for (EmergencyTaskActionFileMapping taskActionFileMapping : taskActionFileMappings) {
                String fileId = taskActionFileMapping.getFileId();

                PreserveFile preserveFile = new PreserveFile(fileId);
                //查询出文件在文件系统的存储路径
                List<PreserveFile> preserveFiles = preserveFileMapper.get(preserveFile);
                preserveFiles.forEach(item -> {
                    String storeName = item.getStoreName();
                    FileUtils.deleteFile(preserveFilePath + storeName);
                });
                //3.2 删除Action对应的附件
                preserveFileMapper.del(preserveFile);//删除文件
                EmergencyTaskActionFileMapping fileMapping =
                        EmergencyTaskActionFileMapping.builder()
                        .fileId(fileId).build();
                //3.3 删除关联表数据
                emergencyTaskActionFileMappingMapper.del(fileMapping);
            }
        }
        //3.2 删除动作Action
        emergencyTaskActionMapper.del(taskAction);
    }
}
