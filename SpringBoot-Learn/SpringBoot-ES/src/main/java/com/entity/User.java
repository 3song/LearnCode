package com.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "root_index")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Integer sex;
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
