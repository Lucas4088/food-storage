package com.wat.foodmanager.jpa.services;

import com.wat.foodmanager.jpa.entities.ShoppingListProductEntity;
import com.wat.foodmanager.jpa.repoport.ShoppingListProductRepositoryService;
import com.wat.foodmanager.jpa.repositories.CategoryRepository;
import com.wat.foodmanager.jpa.repositories.ShoppingListRepository;
import com.wat.foodmanager.jpa.repositories.UnitRepository;
import com.wat.foodmanager.jpa.utils.Mapper;
import com.wat.foodmanager.model.ShoppingListProduct;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ShoppingListProductRepositoryServiceImp implements ShoppingListProductRepositoryService {

    private ShoppingListRepository shoppingListRepository;
    private CategoryRepository categoryRepository;
    private UnitRepository unitRepository;

    public ShoppingListProductRepositoryServiceImp(ShoppingListRepository shoppingListRepository,
                                                   CategoryRepository categoryRepository,
                                                   UnitRepository unitRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    @Async
    public void addShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListRepository.save(
                new ShoppingListProductEntity(
                        shoppingListProduct.getName(),
                        categoryRepository.findByCategoryName(shoppingListProduct.getCategory().getName()),
                        shoppingListProduct.getQuantity(),
                        unitRepository.getByUnitName(shoppingListProduct.getUnit().getName())
                )
        );
    }

    @Override
    @Async
    public CompletableFuture<List<ShoppingListProduct>> ListShoppingListProducts() {
        return CompletableFuture.completedFuture(shoppingListRepository.findAll()
                .stream()
                .map(Mapper::map)
                .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<ShoppingListProduct> getShoppingListProduct(int id) {
        return null;
    }

    @Override
    @Async
    public void deleteShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListRepository.delete(Mapper.map(shoppingListProduct));
    }

    @Override
    @Async
    public void updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListRepository.save(Mapper.map(shoppingListProduct));
    }
}
