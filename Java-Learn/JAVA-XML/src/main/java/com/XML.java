package com;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

public class XML {
    public static void main(String[] args) throws DocumentException {
        //表示读取Dom4jApi
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read("E:\\IdeaProjects\\Java-Learn\\JAVA-XML\\src\\main\\resources\\Test.xml");
        Element rootElement=read.getRootElement();
        getNodes(rootElement);
    }
    static public void getNodes(Element rootElement){
        rootElement.getName(); //取到根节点标签值 Config
        System.out.println("节点名称："+rootElement.getName());
        List<Attribute> attributes = rootElement.attributes();//获取节点属性
        for (Attribute attribute:attributes){
            System.out.println("属性名称："+attribute.getName()+"，属性值："+attribute.getValue());
        }
        String value=rootElement.getStringValue().trim();// 获取所有的 节点值
        String textTrim = rootElement.getTextTrim();
        if (!textTrim.equals(""));{
            System.out.println("节点的value值："+textTrim);
        }
        Iterator<Element> elementIterator = rootElement.elementIterator();
        //判断是否有下一个节点
        while (elementIterator.hasNext()){
            Element next = elementIterator.next();
            getNodes(next);
        }

    }
}
