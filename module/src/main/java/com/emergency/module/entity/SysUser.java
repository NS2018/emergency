package com.emergency.module.entity;

import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
@TableName(value = "vwf_sys_user")
public class SysUser {

    @JdbcId(strategy = IdGenerationType.APP_MANUAL)
    private String id;

    private String logonName;

    private String password;

    private String displayName;

    private String roleId;

    private String locked;

    private Date createTime;

    private String description;

    private Long lockTime;

    private Long unlockTime;

    private String lockReason;

    private Long userExpireTime;

    private Short failNumber;

    private String pwdExpireType;

    private Long pwdExpireTime;

    private Long pwdChangedTime;

    private String phone;

    private String email;

    private Date lastLoginTime;

    private String defaultUser;

    private String userType;

    private String productType;

    private String oemType;

    private Short rightType;

    private String allowAccessIp;

    private String custom;

    private String smId;

    private String loginDn;

    private String unit;

    private String gender;

    private String category;

    private String birthday;

    private String loginNameOfSm;

    private String userSourceIp;
    @Tolerate
    public SysUser(String id) {
        this.id = id;
    }

    @Tolerate
    public SysUser() {
    }
}