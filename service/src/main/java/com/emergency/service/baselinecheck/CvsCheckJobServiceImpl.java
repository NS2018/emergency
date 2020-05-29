package com.emergency.service.baselinecheck;

import com.emergency.dao.mybatis.mapper.baseline.CvsCheckJobMapper;
import com.emergency.dao.mybatis.mapper.baseline.CvsResultScriptMapper;
import com.emergency.dao.mybatis.mapper.baseline.CvsScanResultMapper;
import com.emergency.framework.UserSessionInfo;
import com.emergency.module.constant.BaseLineConstants;
import com.emergency.module.entity.CvsCheckJob;
import com.emergency.module.vo.CVSCheckJobVO;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 基线核查
 */
@Service
public class CvsCheckJobServiceImpl implements CvsCheckJobService{

    @Resource
    private CvsCheckJobMapper cvsCheckJobMapper;
    @Resource
    private CvsScanResultMapper cvsScanResultMapper;
    @Resource
    private CvsResultScriptMapper resultScriptMapper;


    /**
     * * @param jobName 作业名称
     * * @param policyId 策略id
     * * @param scanMethodId 扫描方式id
     * @param sessionInfo
     * @return
     */
    @Override
    public long addJob(UserSessionInfo sessionInfo, CVSCheckJobVO cvsCheckJobVO) {
        CvsCheckJob cvsCheckJob = new CvsCheckJob();
        cvsCheckJob.setName(cvsCheckJobVO.getName());
        cvsCheckJob.setPId( (long)cvsCheckJobVO.getPolicyId());
        cvsCheckJob.setSdId((long)cvsCheckJobVO.getScanMethodId());
        cvsCheckJob.setUserId(sessionInfo.getUserId());
        cvsCheckJob.setCrtName(sessionInfo.getUserName());
        cvsCheckJob.setCrtTime(new Date().getTime());
        cvsCheckJob.setScTime(new Date().getTime());
        cvsCheckJob.setScEndTime(0L);
        cvsCheckJob.setScNextTime(0L);
        cvsCheckJob.setCycleDay(0L);
        cvsCheckJob.setClusterId("local");
        cvsCheckJob.setOutAsset(0);
        cvsCheckJob.setIsStart(Integer.valueOf(BaseLineConstants.Progress.DISPATCHING));
        cvsCheckJob.setStId(0L);
        cvsCheckJobMapper.save(cvsCheckJob);
        return cvsCheckJob.getId();
    }

    @Override
    public CvsCheckJob getJob(Long jobId) {
        CvsCheckJob cvsCheckJob = new CvsCheckJob(jobId);
        CvsCheckJob checkJob = cvsCheckJobMapper.getById(cvsCheckJob);
        return checkJob;
    }

    @Override
    public List<CvsCheckJob> getList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CvsCheckJob> cvsCheckJobs = cvsCheckJobMapper.get(new CvsCheckJob());
        return cvsCheckJobs;
    }

    @Override
    public void deleteJob(Long jobId) {
        CvsCheckJob cvsCheckJob = new CvsCheckJob(jobId);
        cvsCheckJobMapper.del(cvsCheckJob);
    }

    @Override
    public boolean isExistJobName(String jobName) {
        CvsCheckJob checkJob = CvsCheckJob.builder()
                .name(jobName).build();
        List<CvsCheckJob> cvsCheckJobs = cvsCheckJobMapper.get(checkJob);
        if(cvsCheckJobs.size() == 0){
            return false;
        }
        return true;
    }
}
