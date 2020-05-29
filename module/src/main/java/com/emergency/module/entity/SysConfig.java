package com.emergency.module.entity;


import com.emergency.module.annotation.IdGenerationType;
import com.emergency.module.annotation.JdbcId;
import com.emergency.module.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;


/**
 * 系统配置实体
 */
@Data
@Builder
@TableName(value = "vwf_sys_config")
public class SysConfig implements Serializable {

	@JdbcId(strategy = IdGenerationType.APP_MANUAL)
	private String id;
	private Integer sessionMaxInactiveInterval;
	private Integer maxOnlineUser;
	private Integer pwdMinLength;
	private Integer pwdMaxLength;
	private String pwdTag;
	private String pwdRegExp;
	private Integer loginTryNum;
	private Integer refuseTime;
	private String productTypes;
	private String editionTypes;
	private String oemTypes;
	private Integer updateNewUserPwd;
	private Integer pwdExpireAble;
	private Integer pwdExpireTime;
	private Integer pwdExpireTipType;
	private Long pwdExpiredEnableTime;
	@Tolerate
	public SysConfig(String id) {
		this.id = id;
	}

	@Tolerate
	public SysConfig() {
	}
}
