package com.wat.foodmanager;

import com.foodmanager.guiport.ShoppingListFrontendService;
import com.wat.foodmanager.jpa.repoport.ShoppingListProductRepositoryService;
import com.wat.foodmanager.jpa.repoport.StorageProductRepositoryService;
import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.StorageProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingListServiceCore implements ShoppingListFrontendService {

    private ShoppingListProductRepositoryService shoppingListProductRepositoryService;
    private StorageProductRepositoryService storageProductRepositoryService;

    public ShoppingListServiceCore(ShoppingListProductRepositoryService shoppingListProductRepositoryService,
                                   StorageProductRepositoryService storageProductRepositoryService) {
        this.shoppingListProductRepositoryService = shoppingListProductRepositoryService;
        this.storageProductRepositoryService = storageProductRepositoryService;
    }

    @Override
    public ShoppingListProduct getShoppingListProduct(int id) {
        return shoppingListProductRepositoryService.getShoppingListProduct(id).join();
    }

    @Override
    public List<ShoppingListProduct> listShoppingListProducts() {
        return shoppingListProductRepositoryService.ListShoppingListProducts().join();
    }

    @Override
    public void addShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListProductRepositoryService.addShoppingListProduct(shoppingListProduct);
    }

    @Override
    public void deleteShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListProductRepositoryService.deleteShoppingListProduct(shoppingListProduct);
    }

    @Override
    public void updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListProductRepositoryService.updateShoppingListProduct(shoppingListProduct);
    }

    @Override
    public void buyShoppingListProduct(ShoppingListProduct shoppingListProduct, StorageProduct storageProduct) {
        ShoppingListProduct toBeDeletedShoppingListProduct = shoppingListProductRepositoryService.getShoppingListProduct(shoppingListProduct.getId()).join();
        shoppingListProductRepositoryService.deleteShoppingListProduct(toBeDeletedShoppingListProduct);
        storageProductRepositoryService.addStorageProduct(storageProduct);
    }
}
