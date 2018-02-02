package gui.controllers;

import com.foodmanager.guiport.StorageFoodFrontendService;
import com.wat.foodmanager.model.StorageProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageProductController {

    private StorageFoodFrontendService storageFoodFrontendService;

    public StorageProductController(StorageFoodFrontendService storageFoodFrontendService) {
        this.storageFoodFrontendService = storageFoodFrontendService;
    }

    public StorageProduct getStoredProduct(int id) {
        return storageFoodFrontendService.getStoredProduct(id);
    }

    public List<StorageProduct> listStoredProducts() {
        return storageFoodFrontendService.listStoredProducts();
    }

    public void addStoredProduct(StorageProduct storageProduct) {
        storageFoodFrontendService.addStoredProduct(storageProduct);
    }

    public void deleteStoredProduct(StorageProduct storageProduct) {
        storageFoodFrontendService.deleteStoredProduct(storageProduct);
    }

    public void updateStoredProduct(StorageProduct storageProduct) {
        storageFoodFrontendService.updateStoredProduct(storageProduct);
    }
}
