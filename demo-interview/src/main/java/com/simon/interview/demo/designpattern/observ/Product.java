package com.simon.interview.demo.designpattern.observ;

import java.util.Observable;

public class Product extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
