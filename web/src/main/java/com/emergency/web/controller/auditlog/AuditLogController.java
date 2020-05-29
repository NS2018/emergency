package com.emergency.web.controller.auditlog;

import com.emergency.framework.util.DateUtil;
import com.emergency.framework.util.JaxbUtil;
import com.emergency.framework.util.UpOrDownUtil;
import com.emergency.module.dto.AuditLogXml;
import com.emergency.module.entity.AuditLog;
import com.emergency.service.auditlog.AuditLogService;
import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auditlog")
public class AuditLogController {

    @Resource
    private AuditLogService auditLogService;


    /**
     * 查询日志
     * @param req
     * @return
     */
    @GetMapping
    public ApiResponseData search(HttpServletRequest req){

        List<AuditLog> auditLogs = listAuditLog(req);
        PageInfo<AuditLog> auditLogPageInfo = new PageInfo<>(auditLogs);
        return ApiResponse.ok(auditLogPageInfo);
    }

    /**
     * 导出日志
     * @param req
     * @return
     */
    @GetMapping("/export")
    public ApiResponseData export(HttpServletRequest req, HttpServletResponse response){

        List<AuditLog> auditLogs = listAuditLog(req);

        //导出
        if(CollectionUtils.isEmpty(auditLogs)){
            return ApiResponse.fail("内容为空");
        }
        AuditLogXml auditLogXml = new AuditLogXml(auditLogs);
        String xmlContent = JaxbUtil.convertToXml(auditLogXml);
        File file = new File(DateUtil.today() + "_auditLog.xml");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(xmlContent);
            UpOrDownUtil.download(response,file.getAbsolutePath());
        }catch (IOException e){
            return ApiResponse.fail(e.getMessage());
        }
        return null;
    }

    /**
     * 导入日志
     * @param file 上传的文件
     * @return
     */
    @PostMapping("/import")
    public ApiResponseData upload(MultipartFile file){
        try{
            byte[] bytes = file.getBytes();
            String content = new String(bytes);
            AuditLogXml auditLogXml = JaxbUtil.convertToJavaBean(content, AuditLogXml.class);
            auditLogService.save(auditLogXml);
        }catch (Exception e){
            return ApiResponse.fail(e.getMessage());
        }
        return ApiResponse.ok("导入成功");
    }


    private List<AuditLog> listAuditLog(HttpServletRequest req){
        String[] fields = new String[5];
        String[] values = new String[5];
        String paras = req.getParameter("paras");
        String offset = req.getParameter("pageNo");
        String limit = req.getParameter("pageSize");
        String[] conditions = paras.split("%");
        for (int i = 0; i < conditions.length; i++) {
            String[] field_value = conditions[i].split(":");
            fields[i] = field_value[0];
            if(field_value.length == 1){
                values[i] = null;
            }else {
                values[i] = field_value[1];
            }
        }
        List<AuditLog> result = auditLogService.query(fields,values,offset,limit);
        return result;
    }
}
