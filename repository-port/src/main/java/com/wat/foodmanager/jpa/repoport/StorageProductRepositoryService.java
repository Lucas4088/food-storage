package com.wat.foodmanager.jpa.repoport;

import com.wat.foodmanager.model.StorageProduct;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StorageProductRepositoryService {
    void addStorageProduct(StorageProduct storageProduct);

    CompletableFuture<List<StorageProduct>> listStoredProducts();

    CompletableFuture<StorageProduct> getStoredProduct(int id);

    void deleteStorageProduct(StorageProduct storageProduct);

    void updateStorageProduct(StorageProduct storageProduct);
}
