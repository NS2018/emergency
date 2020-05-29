/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.baselinecheck.xml;

import com.emergency.framework.util.XmlReader;
import com.emergency.service.baselinecheck.xml.bean.ReplaceStr;
import com.emergency.service.baselinecheck.xml.bean.Template;
import com.emergency.service.baselinecheck.xml.bean.TemplateConfig;
import com.emergency.service.baselinecheck.xml.bean.TemplatePath;
import org.apache.commons.digester3.Digester;

import java.io.File;

/**
 * 基线核查模板配置xml文件Reader
 * 将xml转化为java 对象
 *
 * @author gengyuanbo
 * 2019/01/09
 */
public class TemplateConfigReader extends XmlReader<TemplateConfig> {
    private static TemplateConfig config;

    public TemplateConfigReader() {
        this.digester = new Digester();
    }

    /**
     * 获取模板配置对象
     * @param xmlFile xml配置文件
     * @param xsdFile xsd文件
     * @return TemplateConfig
     */
    public static TemplateConfig getTemplateConfig(File xmlFile,File xsdFile){
        if(config == null){
            synchronized (TemplateConfigReader.class) {
                if(config == null) {
                    TemplateConfigReader templateConfigReader = new TemplateConfigReader();
                    config = templateConfigReader.read(xmlFile, xsdFile);
                }
            }
        }
        return config;
    }

    @Override
    protected void initRule(Digester paramDigester) {
        digester.clear();

        digester.addObjectCreate("TemplateConfig", TemplateConfig.class);

        digester.addObjectCreate("TemplateConfig/Templates/Template", Template.class);
        digester.addSetProperties("TemplateConfig/Templates/Template");
        digester.addCallMethod("TemplateConfig/Templates/Template/classPath", "setClassPath", 0);
        digester.addCallMethod("TemplateConfig/Templates/Template/readMe", "setReadMe", 0);

        digester.addObjectCreate("TemplateConfig/Templates/Template/ReplaceStrs/replaceStr", ReplaceStr.class);
        digester.addSetProperties("TemplateConfig/Templates/Template/ReplaceStrs/replaceStr");
        digester.addSetNext("TemplateConfig/Templates/Template/ReplaceStrs/replaceStr", "addReplaceStr");

        digester.addObjectCreate("TemplateConfig/Templates/Template/TemplatePaths/templatePath", TemplatePath.class);
        digester.addSetProperties("TemplateConfig/Templates/Template/TemplatePaths/templatePath");
        digester.addSetNext("TemplateConfig/Templates/Template/TemplatePaths/templatePath", "addTemplatePath");

        digester.addSetNext("TemplateConfig/Templates/Template", "addTemplate");
    }
}
