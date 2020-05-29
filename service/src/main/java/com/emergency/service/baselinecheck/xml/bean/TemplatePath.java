/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.baselinecheck.xml.bean;

/**
 * @author gengyuanbo
 * 2019/01/09
 */
public class TemplatePath {
    private String key;
    private String value;

    public String getKey()
    {
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public static long getSerialversionuid()
    {
        return 4844227691344550797L;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<templatePath key=\"").append(this.key).append(" value=\"").append(this.value).append("\">");
        return sb.toString();
    }
}
