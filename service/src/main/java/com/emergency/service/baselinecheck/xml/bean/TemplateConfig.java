/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.baselinecheck.xml.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author gengyuanbo
 * 2019/01/09
 */
public class TemplateConfig {
    private static final Logger logger = LoggerFactory.getLogger(TemplateConfig.class);

    private Map<String, Template> templateConfigMap = new TreeMap();
    public static final String DEFAULTTEMPLATE = "NETWORK_DEFAULT";

    public Map<String, Template> getTemplateConfigMap() {
        return this.templateConfigMap;
    }

    public void setTemplateConfigMap(Map<String, Template> templateConfigMap) {
        this.templateConfigMap = templateConfigMap;
    }

    public Template getTemplate(String type) {
        Template template = (Template)getTemplateConfigMap().get(type);
        if (template != null) {
            return template;
        }
        logger.info(type + " not config offline template");
        if ((type.startsWith("/NETWORK")) || (type.startsWith("/SECURITY"))) {
            logger.info(type + " use default offline template");
            return (Template)getTemplateConfigMap().get("NETWORK_DEFAULT");
        }
        return null;
    }

    public void addTemplate(Template t)
    {
        this.templateConfigMap.put(t.getType(), t);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
        sb.append("<TemplateConfig>").append("\n").append("\t<Templates>").append("\n");
        Set<String> key = this.templateConfigMap.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();)
        {
            String s = (String)it.next();
            Template os = (Template)this.templateConfigMap.get(s);
            sb.append("\t\t").append(os.toString()).append("\n");
        }
        sb.append("\t</Templates>").append("\n");
        sb.append("</CollectorConfig>").append("\n");
        return sb.toString();
    }

}
