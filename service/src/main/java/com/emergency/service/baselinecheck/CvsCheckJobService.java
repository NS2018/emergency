package com.emergency.service.baselinecheck;

import com.emergency.framework.UserSessionInfo;
import com.emergency.module.entity.CvsCheckJob;
import com.emergency.module.vo.CVSCheckJobVO;

import java.util.List;

public interface CvsCheckJobService {

    /**
     * 添加基线核查作业
     * @param  cvsCheckJobVO
     * @return int 作业id
     */
    long addJob(UserSessionInfo sessionInfo, CVSCheckJobVO cvsCheckJobVO);

    /**
     * 根据id获取基线核查作业
     * @param jobId 任务id
     * @return CvsCheckJob
     */
    CvsCheckJob getJob(Long jobId);

    /**
     * 分页获基线核查作业列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return List<CvsCheckJob>
     */
    List<CvsCheckJob> getList(int pageNum, int pageSize);


    void deleteJob(Long jobId);

    /**
     * 作业名称是否已经存在
     * @param jobName 作业名称
     * @return boolean
     */
    boolean isExistJobName(String jobName);
}
