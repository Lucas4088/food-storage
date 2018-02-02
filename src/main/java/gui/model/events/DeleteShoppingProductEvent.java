package gui.model.events;

import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.ShoppingListProduct;
import gui.controllers.ShoppingListController;
import gui.dialogs.ShoppingListDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class DeleteShoppingProductEvent implements EventHandler<ActionEvent> {

    private ShoppingListController shoppingListController;
    private ShoppingListProduct shoppingListProduct;
    private TableView<ShoppingListProduct> shoppingListProductTableView;
    private ShoppingListDialog shoppingListDialog;

    public DeleteShoppingProductEvent(ShoppingListController shoppingListController,
                                      ShoppingListProduct shoppingListProduct,
                                      TableView<ShoppingListProduct> shoppingListProductTableView,
                                      ShoppingListDialog shoppingListDialog) {
        this.shoppingListController = shoppingListController;
        this.shoppingListProduct = shoppingListProduct;
        this.shoppingListProductTableView = shoppingListProductTableView;
        this.shoppingListDialog = shoppingListDialog;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            shoppingListDialog.validateInput();
            shoppingListController.deleteShoppingListProduct(shoppingListProduct);
            shoppingListProductTableView.getItems().remove(shoppingListProduct);
            shoppingListDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
