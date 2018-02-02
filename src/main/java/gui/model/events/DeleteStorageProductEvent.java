package gui.model.events;

import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.StorageProduct;
import gui.controllers.StorageProductController;
import gui.dialogs.StorageFoodDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class DeleteStorageProductEvent implements EventHandler<ActionEvent> {

    private StorageProductController storageProductController;
    private TableView<StorageProduct> storageProductTableView;
    private StorageProduct storageProduct;
    private StorageFoodDialog storageFoodDialog;

    public DeleteStorageProductEvent(StorageProductController storageProductController,
                                     TableView<StorageProduct> storageProductTableView,
                                     StorageProduct storageProduct,
                                     StorageFoodDialog storageFoodDialog) {
        this.storageProductController = storageProductController;
        this.storageProductTableView = storageProductTableView;
        this.storageProduct = storageProduct;
        this.storageFoodDialog = storageFoodDialog;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            storageFoodDialog.validateInput();
            storageProductController.deleteStoredProduct(storageProduct);
            storageProductTableView.getItems().remove(storageProduct);
            storageFoodDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
