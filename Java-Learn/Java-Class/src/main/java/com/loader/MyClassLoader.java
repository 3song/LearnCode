package com.loader;

import com.entity.Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyClassLoader extends ClassLoader {
    //传递class文件地址 读取class文件
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            //1.获取文件名称
            String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
            //2.读取文件名称 读取文件流
            String resource = this.getClass().getResource("").getPath();
            System.out.println(resource);
            File file=new File("E:\\IdeaProjects\\Java-Learn\\Java-Class\\target\\classes\\com\\entity\\"+fileName);
            InputStream resourceAsStream = new FileInputStream(file);
            byte[] bytes=new byte[resourceAsStream.available()];
            //3.将读取byte数组给jvm识别Class对象
            resourceAsStream.read(bytes);
            resourceAsStream.close();
            return defineClass(name, bytes, 0,bytes.length );
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}
