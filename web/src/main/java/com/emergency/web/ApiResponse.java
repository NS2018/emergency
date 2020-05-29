package com.emergency.web;

/**
 * API响应对象
 */
public class ApiResponse {

    /**
     * 不带数据的成功响应
     * @return
     */
    public static ApiResponseData ok(){
        return ok("successful!");
    }

    /**
     * 自定义消息的成功响应
     * @param mess
     * @return ApiResponseData
     */
    public static ApiResponseData ok(String mess){
        return ok(mess,null);
    }

    public static ApiResponseData ok(Object data){
        return ok("successful!",data);
    }
    /**
     * 带数据的成功响应
     * @param data 要返回的数据，任意类型
     * @return
     */
    public static ApiResponseData ok(String mess,Object data){
        ApiResponseData apiResponseData = new ApiResponseData();
        apiResponseData.setCode(1);
        apiResponseData.setMess(mess);
        apiResponseData.setData(data);
        return apiResponseData;
    }

    public static ApiResponseData fail(String mess){
        return fail(mess,null);
    }

    public static ApiResponseData fail(Object data){
        return fail("failed!",data);
    }

    public static ApiResponseData fail(String mess,Object data){
        ApiResponseData apiResponseData = new ApiResponseData();
        apiResponseData.setCode(-1);
        apiResponseData.setMess(mess);
        apiResponseData.setData(data);
        return apiResponseData;
    }

    public static ApiResponseData paramIsNull(String paramName){
        return fail(String.format("%s参数不能为空！",paramName));
    }

    /**
     * 用户未登录响应
     * @return ApiResponseData
     */
    public static ApiResponseData noLogin(){
        return noLogin("user not login!");
    }

    /**
     * 用户未登录响应
     * @return ApiResponseData
     */
    public static ApiResponseData noLogin(String mess){
        ApiResponseData apiResponseData = new ApiResponseData();
        apiResponseData.setCode(0);
        apiResponseData.setMess(mess);
        apiResponseData.setData(null);
        return apiResponseData;
    }

}
