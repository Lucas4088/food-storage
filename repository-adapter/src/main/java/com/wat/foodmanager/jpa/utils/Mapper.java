package com.wat.foodmanager.jpa.utils;

import com.wat.foodmanager.jpa.entities.CategoryEntity;
import com.wat.foodmanager.jpa.entities.ShoppingListProductEntity;
import com.wat.foodmanager.jpa.entities.StorageProductEntity;
import com.wat.foodmanager.jpa.entities.UnitEntity;
import com.wat.foodmanager.model.*;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.wat.foodmanager.repositories")
public class Mapper {

    private static final Mapper INSTANCE = new Mapper();

    private Mapper() {
    }

    public static Mapper getMapper() {
        return Mapper.INSTANCE;
    }

    public static AbstractCategory map(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return new NullCategory();
        }
        return new Category(categoryEntity.getCategoryName());
    }

    public static CategoryEntity map(AbstractCategory category) {
        return new CategoryEntity(category.getName());
    }

    public static StorageProduct map(StorageProductEntity storageProductEntity) {
        return new StorageProduct(storageProductEntity.getId(),
                storageProductEntity.getName(),
                storageProductEntity.getBrand(),
                Mapper.map(storageProductEntity.getCategory()),
                storageProductEntity.getExpirationDate(),
                storageProductEntity.getDateOfPurchase(),
                storageProductEntity.getExpired(),
                storageProductEntity.getQuantity(),
                Mapper.map(storageProductEntity.getUnit()),
                storageProductEntity.getOpened(),
                storageProductEntity.getExpirationDateOpened());
    }

    public static StorageProductEntity map(StorageProduct storageProduct) {
        return new StorageProductEntity(
                storageProduct.getId(),
                storageProduct.getName(),
                storageProduct.getBrand(),
                Mapper.map(storageProduct.getCategory()),
                storageProduct.getExpirationDate(),
                storageProduct.getDateOfPurchase(),
                storageProduct.getQuantity(),
                Mapper.map(storageProduct.getUnit()),
                storageProduct.getOpened(),
                storageProduct.getExpirationDateOpened());
    }

    public static Unit map(UnitEntity unitEntity) {
        return new Unit(unitEntity.getUnitName());
    }

    public static UnitEntity map(Unit unit) {
        return new UnitEntity(unit.getName());
    }

    public static ShoppingListProduct map(ShoppingListProductEntity shoppingListProductEntity) {
        return new ShoppingListProduct(
                shoppingListProductEntity.getId(),
                shoppingListProductEntity.getName(),
                Mapper.map(shoppingListProductEntity.getCategory()),
                shoppingListProductEntity.getQuantity(),
                Mapper.map(shoppingListProductEntity.getUnit()));
    }

    public static ShoppingListProductEntity map(ShoppingListProduct shoppingListProduct) {
        return new ShoppingListProductEntity(
                shoppingListProduct.getId(),
                shoppingListProduct.getName(),
                Mapper.map(shoppingListProduct.getCategory()),
                shoppingListProduct.getQuantity(),
                Mapper.map(shoppingListProduct.getUnit()));
    }
}
