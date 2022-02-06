package com.simon.interview.demo.designpattern.observ;

import java.util.Observable;
import java.util.Observer;

public class ProductNameChangeObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("name changed");
    }
}
