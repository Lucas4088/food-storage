package com.foodmanager.guiport;

import com.wat.foodmanager.model.StoredProduct;

import java.util.List;

public interface StorageFoodFrontendService {
    StoredProduct getStoredProduct(int id);

    List<StoredProduct> listStoredProducts();

    void addStoredProduct(StoredProduct storedProduct);

    void deleteStoredProduct(int id);

    void updateStoredProduct(int id);
}
