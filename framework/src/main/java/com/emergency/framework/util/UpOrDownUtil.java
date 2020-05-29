package com.emergency.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class UpOrDownUtil {

    private static final Logger LOG = LoggerFactory.getLogger(UpOrDownUtil.class);

    public static void download(HttpServletResponse response,String filePath) throws IOException {

        File file = new File(filePath);
        if(!file.exists()){
            LOG.error("【文件下载】文件不存在");
            return ;
        }
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filePath,"UTF-8"));
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = fileInputStream.read(buff)) > 0){
            outputStream.write(buff,0,len);
        }
        outputStream.flush();
    }

}
