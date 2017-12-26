package com.wat.foodmanager.model;

import java.util.List;

public class ShoppingList {
    List<ShoppingListProduct> products;

    public List<ShoppingListProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShoppingListProduct> products) {
        this.products = products;
    }
}
