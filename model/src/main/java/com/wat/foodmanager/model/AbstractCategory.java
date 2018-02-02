package com.wat.foodmanager.model;

public abstract class AbstractCategory {

    protected String name;

    public AbstractCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isNil();
}
