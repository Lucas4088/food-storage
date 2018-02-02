package com.foodmanager.guiport;

import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.StorageProduct;

import java.util.List;

public interface ShoppingListFrontendService {
    ShoppingListProduct getShoppingListProduct(int id);

    List<ShoppingListProduct> listShoppingListProducts();

    void addShoppingListProduct(ShoppingListProduct shoppingListProduct);

    void deleteShoppingListProduct(ShoppingListProduct shoppingListProduct);

    void updateShoppingListProduct(ShoppingListProduct shoppingListProduct);

    void buyShoppingListProduct(ShoppingListProduct shoppingListProduct, StorageProduct storageProduct);
}
