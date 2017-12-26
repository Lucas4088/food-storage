package com.wat.foodmanager.model;

import java.util.List;

public class Storage {
    public List<StoredProduct> getProducts() {
        return products;
    }

    public void setProducts(List<StoredProduct> products) {
        this.products = products;
    }

    private List<StoredProduct> products;
}
