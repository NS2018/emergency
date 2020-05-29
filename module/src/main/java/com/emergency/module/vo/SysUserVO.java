package com.emergency.module.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求参数
 */

@Data
public class SysUserVO implements Serializable {

    private String userName;

    private String password;

}
