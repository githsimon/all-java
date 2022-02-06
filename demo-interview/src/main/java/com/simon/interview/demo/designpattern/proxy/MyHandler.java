package com.simon.interview.demo.designpattern.proxy;

import com.simon.interview.demo.designpattern.inter.AnimalRun;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

    AnimalRun animalRun;

    public MyHandler(AnimalRun animalRun) {
        this.animalRun = animalRun;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.animalRun, args);
    }
}
