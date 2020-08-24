package com.entity;

public class Users {
    private String name;
    private String id;

    public Users(String name, String id) {
        System.out.println("使用java反射机制创建有参对象 name:"+name+",id:"+id);
        this.name = name;
        this.id = id;
    }

    public Users() {
        System.out.println("使用java反射机制创建无参对象");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
