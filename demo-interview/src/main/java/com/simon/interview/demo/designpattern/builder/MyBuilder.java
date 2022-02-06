package com.simon.interview.demo.designpattern.builder;

import com.simon.interview.demo.model.Student;

public class MyBuilder implements Builder{

    private Student student = new Student();

    @Override
    public void buildA() {
        student.setName("buildA");
    }

    @Override
    public void buildB() {
        student.setAge(100);
    }

    @Override
    public Student getStudent() {
        return this.student;
    }
}
