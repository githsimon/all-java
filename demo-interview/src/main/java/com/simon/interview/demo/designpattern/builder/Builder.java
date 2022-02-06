package com.simon.interview.demo.designpattern.builder;

import com.simon.interview.demo.model.Student;

public interface Builder {
    void buildA();
    void buildB();
    Student getStudent();
}
