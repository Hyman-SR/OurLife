package com.ourlife.base.jdk.dumpdemo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhangchao
 * @createdOn 2020/6/1
 */
public class DumpDemo {

    private static List<Student> list = null;
    public static void main(String[] args) {
        list = new ArrayList<>();
        while (true) {
            Student student = new Student();
            student.setId(new Random().nextInt(100));
            student.setAge(new Random().nextInt(100));
            student.setName("隔壁老王");
            list.add(student);
        }
    }
}

@Data
class Student {
    private Integer id;
    private Integer age;
    private String name;
}
