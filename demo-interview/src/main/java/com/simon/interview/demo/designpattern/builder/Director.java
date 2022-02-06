package com.simon.interview.demo.designpattern.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void doBuild(){
        builder.buildA();
        builder.buildB();
    }
}
