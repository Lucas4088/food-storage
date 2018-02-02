package com.wat.foodmanager.model;

public class NullCategory extends AbstractCategory {

    public NullCategory() {
        super("NULL CATEGORY");
    }

    @Override
    public boolean isNil() {
        return true;
    }
}
