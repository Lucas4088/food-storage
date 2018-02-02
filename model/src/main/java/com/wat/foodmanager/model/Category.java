package com.wat.foodmanager.model;

public class Category extends AbstractCategory {


    public Category(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}
