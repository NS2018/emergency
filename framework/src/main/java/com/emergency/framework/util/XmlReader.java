/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.framework.util;


import org.apache.commons.digester3.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author gengyuanbo
 * 2019/01/09
 */
public abstract class XmlReader<T> {
    private Logger logger = LoggerFactory.getLogger(XmlReader.class);
    protected Digester digester;

    public final T read(File xmlFile) throws Exception {
        return read(xmlFile, null);
    }

    public final T read(File xmlFile, File xsdFile) {
        //通过xsd文件对xml文件进行正确性验证
        boolean isValidatePass = false;
        try {
            isValidatePass = validateXMLFile(xmlFile, xsdFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(""+e);
        }
        if(!isValidatePass){
            logger.warn("对xml文件进行正确性验证不通过，可能导致生成的JavaBean对象不正确！");
        }
        if (this.digester == null) {
            this.digester = new Digester();
            this.digester.setNamespaceAware(true);
            this.digester.setValidating(false);
            initRule(this.digester);
        }
        return parse(xmlFile);
    }

    protected abstract void initRule(Digester paramDigester);


    protected final T parse(File xmlFile) {
        try {
            T obj = this.digester.parse(xmlFile);
            return obj;
        } catch (Exception e) {
            logger.error("xml 文件解析错误！",e.getCause());
        }
        return null;
    }

    protected boolean validateXMLFile(File xmlFile, File xsdFile) throws IOException {
        if(xmlFile == null)
            throw new IllegalArgumentException("xmlFile 参数不能为空！");
        if (!xmlFile.exists()) {
            throw new FileNotFoundException();
        }
        if (xsdFile != null && xsdFile.exists()) {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            try {
                Schema schema = factory.newSchema(xsdFile);
                Validator validator = schema.newValidator();
                Source s = new StreamSource(xmlFile);
                validator.validate(s);
                return true;
            } catch (SAXException saxe) { //当抛出SAXException时，即是校验失败
                return false;
            }
        } else { //没有xsd文件
            return true;
        }
    }
}
