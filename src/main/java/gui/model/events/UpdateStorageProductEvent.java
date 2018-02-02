package gui.model.events;

import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.StorageProduct;
import gui.controllers.StorageProductController;
import gui.dialogs.StorageFoodDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;


public class UpdateStorageProductEvent implements EventHandler<ActionEvent> {

    private StorageProduct storageProduct;
    private TableView<StorageProduct> storageProductTableView;
    private StorageProductController storageProductController;
    private StorageFoodDialog storageFoodDialog;

    public UpdateStorageProductEvent(StorageProduct storageProduct,
                                     TableView<StorageProduct> storageProductTableView,
                                     StorageProductController storageProductController,
                                     StorageFoodDialog storageFoodDialog) {
        this.storageProduct = storageProduct;
        this.storageProductTableView = storageProductTableView;
        this.storageProductController = storageProductController;
        this.storageFoodDialog = storageFoodDialog;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            storageFoodDialog.validateInput();
            StorageProduct oldStorageProduct = storageProduct;
            storageProductController.updateStoredProduct(storageProduct);

            storageProductTableView.getItems().remove(oldStorageProduct);
            storageProductTableView.getItems().add(storageProduct);
            storageFoodDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
