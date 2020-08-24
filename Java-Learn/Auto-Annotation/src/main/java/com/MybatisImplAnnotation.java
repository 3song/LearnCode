package com;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

//对应数据库表名称
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String tableName();
}
//数据库字段对应注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Property{
    String name() default "";
    int length() default 0;
}
@Table(tableName = "Student")
class Student{
    @Property(name = "student_id",length=10)
    private String id;
    @Property(name = "student_name")
    private String name;
    @Property(name = "student_age")
    private String age;

    Student(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
public class MybatisImplAnnotation {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> student = Class.forName("com.Student");
        //获取所有Table类的方法
        Field[] declaredFields = student.getDeclaredFields();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(" select ");
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            Property property = declaredField.getAnnotation(Property.class);
            stringBuffer.append(property.name());//获取Property注解类内的参数 表名@Property(name = "student_name、id、age")
            if (i<declaredFields.length-1){
                stringBuffer.append(" , ");
            }
        }
        //表示获取类上的注解参数
        Table declaredAnnotation =student.getDeclaredAnnotation(Table.class);
        stringBuffer.append(" from "+ declaredAnnotation.tableName()); //获取注解类内的参数
        System.out.println(stringBuffer.toString());

    }

}
