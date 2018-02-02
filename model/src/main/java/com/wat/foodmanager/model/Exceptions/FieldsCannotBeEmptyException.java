package com.wat.foodmanager.model.Exceptions;

public class FieldsCannotBeEmptyException extends Exception {
    public FieldsCannotBeEmptyException(String message) {
        super("Following fields cannot be empty: " + message);
    }
}
