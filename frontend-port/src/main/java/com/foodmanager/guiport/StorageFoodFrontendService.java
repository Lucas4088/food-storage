package com.foodmanager.guiport;

import com.wat.foodmanager.model.StorageProduct;

import java.util.List;

public interface StorageFoodFrontendService {
    StorageProduct getStoredProduct(int id);

    List<StorageProduct> listStoredProducts();

    void addStoredProduct(StorageProduct storageProduct);

    void deleteStoredProduct(StorageProduct storageProduct);

    void updateStoredProduct(StorageProduct storageProduct);
}
