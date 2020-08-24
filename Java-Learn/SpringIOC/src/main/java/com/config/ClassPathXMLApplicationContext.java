package com.config;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.lang.reflect.Field;
import java.util.List;

public class ClassPathXMLApplicationContext {
    private static String path;
    private static String ID;
    private static String CLASS;
    private static String NAME;
    private static String VALUE;
    public void init(){
        ID="id";
        CLASS="class";
        NAME="name";
        VALUE="value";
    }
    //读取传入路径
    public ClassPathXMLApplicationContext(String path) {
        this.init();//初始化属性值
        this.path=path;
    }
    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        if (StringUtils.isEmpty(beanId)){
            return null;
        }
        SAXReader saxReader=new SAXReader();
        Document read = saxReader.read(this.getClass().getClassLoader().getResource(path));
        Element rootElement = read.getRootElement();//获取的是application 的beanIds节点
        List<Element> elements = rootElement.elements();
        for (Element element:elements){
            //二级节点
             String id=element.attributeValue(ID); //获取bean节点的id值
             if(!beanId.equals(id)){
                    //结束本次循环   表示找到的
                    continue;
             }
             String attClass=element.attributeValue(CLASS);//表示从配置文件获取到bean 第2步
            //使用java反射机制初始化类
            Class<?> aClass = Class.forName(attClass);
            Object o = aClass.newInstance(); //Users对象
            List<Element> elements1 = element.elements();//获取属性值 property节点
            for (Element e:elements1){
                //获取配置文件属性名称
                String attField = e.attributeValue(NAME); //property节点 name
                String attFieldValue = e.attributeValue(VALUE); //property节点 value
                Field declaredField = aClass.getDeclaredField(attField);// 根据property节点 name 获取 property节点 value
                //赋权
                declaredField.setAccessible(true);
                declaredField.set(o, attFieldValue); //把配置文件 bean标签的value值 赋值给Users对象
            }
            return o;
        }

        return null;
    }

}
