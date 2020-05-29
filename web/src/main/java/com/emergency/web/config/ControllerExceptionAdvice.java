package com.emergency.web.config;


import com.emergency.web.ApiResponse;
import com.emergency.web.ApiResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * controller层异常处理增强
 * @author gengyuanbo
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseData globalException(Exception e){
        logger.error("服务器内部异常！",e);
        return ApiResponse.fail("服务器内部异常！");
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseData ioException(IOException e){
        logger.error("服务器内部IO错误！",e);
        return ApiResponse.fail("服务器内部IO错误！");
    }
}
