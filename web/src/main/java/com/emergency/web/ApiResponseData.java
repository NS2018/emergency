package com.emergency.web;



/**
 * 响应数据包装内部类
 */
public final class ApiResponseData{
    /**
     * 响应代码
     * 规定0未登录，负数为失败，正数为成功
     */
    private int code;
    /**
     * 响应说明信息
     */
    private String mess;
    /**
     * 响应的数据
     */
    private Object data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMess() {
        return mess;
    }

    public Object getData() {
        return data;
    }
}