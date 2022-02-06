package com.simon.interview.demo.designpattern.singleton;

import com.simon.interview.demo.model.Student;

public class StuSingleton {

    private volatile static Student student;

    public Student getInstance(){
        if(student == null){
            synchronized (StuSingleton.class){
                if(student == null){
                    student = new Student();
                    student.setName(System.currentTimeMillis() + "");
                    return student;
                }
            }
        }
        return student;
    }
}
