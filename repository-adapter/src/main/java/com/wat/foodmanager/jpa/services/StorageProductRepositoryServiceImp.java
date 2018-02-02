package com.wat.foodmanager.jpa.services;

import com.wat.foodmanager.jpa.entities.StorageProductEntity;
import com.wat.foodmanager.jpa.repoport.StorageProductRepositoryService;
import com.wat.foodmanager.jpa.repositories.CategoryRepository;
import com.wat.foodmanager.jpa.repositories.StorageProductRepository;
import com.wat.foodmanager.jpa.repositories.UnitRepository;
import com.wat.foodmanager.jpa.utils.Mapper;
import com.wat.foodmanager.model.StorageProduct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class StorageProductRepositoryServiceImp implements StorageProductRepositoryService {

    private StorageProductRepository storageProductRepository;
    private CategoryRepository categoryRepository;
    private UnitRepository unitRepository;

    public StorageProductRepositoryServiceImp(StorageProductRepository storageProductRepository, CategoryRepository categoryRepository, UnitRepository unitRepository) {
        this.storageProductRepository = storageProductRepository;
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    @Async
    public void addStorageProduct(StorageProduct storageProduct) {
        storageProductRepository.save(new StorageProductEntity(
                        storageProduct.getName(),
                        storageProduct.getBrand(),
                        categoryRepository.findByCategoryName(storageProduct.getCategory().getName()),
                        storageProduct.getExpirationDate(),
                        storageProduct.getDateOfPurchase(),
                        storageProduct.getQuantity(),
                        unitRepository.getByUnitName(storageProduct.getUnit().getName()),
                        false,
                        storageProduct.getExpirationDateOpened()
                )
        );
    }

    @Override
    @Async
    public CompletableFuture<List<StorageProduct>> listStoredProducts() {
        return CompletableFuture.completedFuture(storageProductRepository.findAll()
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<StorageProduct> getStoredProduct(int id) {
        return null;
    }

    @Override
    @Async
    public void deleteStorageProduct(StorageProduct storageProduct) {
        storageProductRepository.delete(Mapper.map(storageProduct));
    }

    @Override
    @Async
    public void updateStorageProduct(StorageProduct storageProduct) {
        storageProductRepository.save(Mapper.map(storageProduct));
    }
}
