package com.wat.foodmanager.jpa.repoport;

import com.wat.foodmanager.model.ShoppingListProduct;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ShoppingListProductRepositoryService {
    void addShoppingListProduct(ShoppingListProduct shoppingListProduct);

    CompletableFuture<List<ShoppingListProduct>> ListShoppingListProducts();

    CompletableFuture<ShoppingListProduct> getShoppingListProduct(int id);

    void deleteShoppingListProduct(ShoppingListProduct shoppingListProduct);

    void updateShoppingListProduct(ShoppingListProduct shoppingListProduct);
}
