package com.simon.interview.demo.designpattern.prototype;

public class AbstractSpoon implements Cloneable{

    private String spoonName;

    public String getSpoonName() {
        return spoonName;
    }

    public void setSpoonName(String spoonName) {
        this.spoonName = spoonName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("AbstractSpoon is not Cloneable");
        }
        return object;
    }
}
