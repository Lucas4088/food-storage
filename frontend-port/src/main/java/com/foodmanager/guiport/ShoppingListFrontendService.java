package com.foodmanager.guiport;

import com.wat.foodmanager.model.ShoppingListProduct;

import java.util.List;

public interface ShoppingListFrontendService {
    ShoppingListProduct getShoppingListProduct(int id);

    List<ShoppingListProduct> listShoppingListProducts();

    void addShoppingListProduct(ShoppingListProduct shoppingListProduct);

    void deleteShoppingListProduct(int id);

    void updateShoppingListProduct(int id);
}
