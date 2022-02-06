package com.simon.interview.demo.designpattern.factory;

public class MyFactory extends Animal{

    public Animal create(Class<?> clazz){
        if(clazz == Dog.class){
            return new Dog();
        }else if(clazz == Cat.class){
            return new Cat();
        }
        throw new IllegalArgumentException("Animal error");
    }
}
