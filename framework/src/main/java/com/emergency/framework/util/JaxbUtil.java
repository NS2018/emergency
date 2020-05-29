package com.emergency.framework.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * jaxb工具bean 与 xml相互转换
 */
public class JaxbUtil {

    public static String convertToXml(Object object){
        return convertToXml(object,"UTF-8");
    }

    /**
     * javabean 转换成 xml
     * @param object 对象
     * @param charset 编码
     * @return
     */
    private static String convertToXml(Object object, String charset) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING,charset);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(object,writer);
            result = writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * xml 转换成 bean
     * @param xml
     * @param clz
     * @param <T>
     * @return
     */
    public static<T> T convertToJavaBean(String xml,Class<T> clz){
        T t  = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

}
