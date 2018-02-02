package com.wat.foodmanager;

import com.foodmanager.guiport.StorageFoodFrontendService;
import com.wat.foodmanager.jpa.repoport.StorageProductRepositoryService;
import com.wat.foodmanager.model.StorageProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageFoodServiceCore implements StorageFoodFrontendService {

    private StorageProductRepositoryService storageProductRepositoryService;

    public StorageFoodServiceCore(StorageProductRepositoryService storageProductRepositoryService) {
        this.storageProductRepositoryService = storageProductRepositoryService;
    }

    @Override
    public StorageProduct getStoredProduct(int id) {
        return storageProductRepositoryService.getStoredProduct(id).join();
    }

    @Override
    public List<StorageProduct> listStoredProducts() {
        return storageProductRepositoryService.listStoredProducts().join();
    }

    @Override
    public void addStoredProduct(StorageProduct storageProduct) {
        storageProductRepositoryService.addStorageProduct(storageProduct);
    }

    @Override
    public void deleteStoredProduct(StorageProduct storageProduct) {
        storageProductRepositoryService.deleteStorageProduct(storageProduct);
    }

    @Override
    public void updateStoredProduct(StorageProduct storageProduct) {
        storageProductRepositoryService.updateStorageProduct(storageProduct);
    }
}
