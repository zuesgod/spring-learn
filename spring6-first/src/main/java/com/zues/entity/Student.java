package com.zues.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private Clazz clazz;
    private String[] hobbies;

    public Student() {
        System.out.println("hello程序的无参构造执行了");
    }

    public void sayHello(){
        System.out.println("hello spring");
    }
}
