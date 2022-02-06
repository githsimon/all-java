package com.simon.interview.demo.designpattern.inter.impl;

import com.simon.interview.demo.designpattern.inter.AnimalRun;

public class MyCat implements AnimalRun {
    @Override
    public void doRun() {
        System.out.println("This is a Cat");
    }
}
