package com.entity;

public class Users {
    private String name;
    private Integer age;

    public Users() {

    }
    //  int是基本数据类型  默认为0    封装了int的方法
    //  Integer  可以为空

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Users(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
