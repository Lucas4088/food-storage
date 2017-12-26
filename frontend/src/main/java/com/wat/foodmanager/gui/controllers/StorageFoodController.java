package com.wat.foodmanager.gui.controllers;

import com.foodmanager.guiport.StorageFoodFrontendService;
import com.wat.foodmanager.model.StoredProduct;

import java.util.List;

public class StorageFoodController implements StorageFoodFrontendService {
    @Override
    public StoredProduct getStoredProduct(int id) {
        return null;
    }

    @Override
    public List<StoredProduct> listStoredProducts() {
        return null;
    }

    @Override
    public void addStoredProduct(StoredProduct storedProduct) {

    }

    @Override
    public void deleteStoredProduct(int id) {

    }

    @Override
    public void updateStoredProduct(int id) {

    }
}
