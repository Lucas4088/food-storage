package gui.controllers;

import com.foodmanager.guiport.ShoppingListFrontendService;
import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.StorageProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShoppingListController {

    private ShoppingListFrontendService shoppingListFrontendService;

    public ShoppingListController(ShoppingListFrontendService shoppingListFrontendService) {
        this.shoppingListFrontendService = shoppingListFrontendService;
    }

    public ShoppingListProduct getShoppingListProduct(int id) {
        return null;
    }

    public List<ShoppingListProduct> listShoppingListProducts() {
        return shoppingListFrontendService.listShoppingListProducts();
    }

    public void addShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListFrontendService.addShoppingListProduct(shoppingListProduct);
    }

    public void updateShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListFrontendService.updateShoppingListProduct(shoppingListProduct);
    }

    public void deleteShoppingListProduct(ShoppingListProduct shoppingListProduct) {
        shoppingListFrontendService.deleteShoppingListProduct(shoppingListProduct);
    }

    public void buyShoppingListProduct(ShoppingListProduct shoppingListProduct, StorageProduct storageProduct) {
        shoppingListFrontendService.buyShoppingListProduct(shoppingListProduct, storageProduct);
    }
}
