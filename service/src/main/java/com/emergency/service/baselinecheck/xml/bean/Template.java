/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.baselinecheck.xml.bean;

import java.util.*;

/**
 * @author gengyuanbo
 * 2019/01/09
 */
public class Template {
    private String classPath;
    private String type;
    private String readMe;
    private List<ReplaceStr> templateReplaces = new ArrayList();
    private Map<String, TemplatePath> templatePaths = new TreeMap();

    public String getClassPath()
    {
        return this.classPath;
    }

    public void setClassPath(String classPath)
    {
        this.classPath = classPath;
    }

    public String getReadMe()
    {
        return this.readMe;
    }

    public void setReadMe(String readMe)
    {
        this.readMe = readMe;
    }

    public List<ReplaceStr> getTemplateReplaces()
    {
        return this.templateReplaces;
    }

    public void setTemplateReplaces(List<ReplaceStr> templateReplaces)
    {
        this.templateReplaces = templateReplaces;
    }

    public Map<String, TemplatePath> getTemplatePaths()
    {
        return this.templatePaths;
    }

    public void setTemplatePaths(Map<String, TemplatePath> templatePaths)
    {
        this.templatePaths = templatePaths;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void addReplaceStr(ReplaceStr rs)
    {
        this.templateReplaces.add(rs);
    }

    public void addTemplatePath(TemplatePath tp)
    {
        this.templatePaths.put(tp.getKey(), tp);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<Template type=\"").append(this.type).append("\">").append("\n");
        sb.append("\t\t<ReplaceStrs isTemplate=\"true\">").append("\n");
        for (ReplaceStr cbu : this.templateReplaces) {
            sb.append("\t\t\t").append(cbu.toString()).append("\n");
        }
        sb.append("\t\t</ReplaceStrs>").append("\n");

        sb.append("\t\t<TemplatePaths isTemplate=\"true\">").append("\n");
        Set<String> set = this.templatePaths.keySet();
        for (Object it = set.iterator(); ((Iterator)it).hasNext();)
        {
            String key = (String)((Iterator)it).next();
            TemplatePath tp = (TemplatePath)this.templatePaths.get(key);
            sb.append("\t\t\t").append(tp.toString()).append("\n");
        }
        sb.append("\t\t</TemplatePaths>").append("\n");
        sb.append("\t\t<classPath>").append(this.classPath).append("</classPath>").append("\n");
        sb.append("\t\t<readMe>").append(this.readMe).append("</readMe>").append("\n");
        sb.append("\t\t</Template>");
        return sb.toString();
    }
}
