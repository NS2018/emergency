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
public class ReplaceStr {
    private String key;
    private String replaceTemplate;

    public String getKey()
    {
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getReplaceTemplate()
    {
        return this.replaceTemplate;
    }

    public void setReplaceTemplate(String replaceTemplate)
    {
        this.replaceTemplate = replaceTemplate;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<replaceStr key=\"").append(this.key).append(" replaceTemplate=\"").append(this.replaceTemplate).append("\">");
        return sb.toString();
    }
}
