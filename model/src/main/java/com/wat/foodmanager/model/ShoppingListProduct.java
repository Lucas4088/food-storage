package com.wat.foodmanager.model;

public class ShoppingListProduct {
    private int id;
    private String name;
    private AbstractCategory category;
    private Integer quantity;
    private Unit unit;

    public ShoppingListProduct(String name, AbstractCategory category, Integer quantity, Unit unit) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public ShoppingListProduct(int id, String name, AbstractCategory category, Integer quantity, Unit unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractCategory getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
