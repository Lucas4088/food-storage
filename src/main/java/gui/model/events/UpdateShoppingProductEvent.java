package gui.model.events;

import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.ShoppingListProduct;
import gui.controllers.ShoppingListController;
import gui.dialogs.ShoppingListDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;


public class UpdateShoppingProductEvent implements EventHandler<ActionEvent> {
    private ShoppingListController shoppingListController;
    private ShoppingListProduct shoppingListProduct;
    private TableView<ShoppingListProduct> shoppingListProductTableView;
    private ShoppingListDialog shoppingListDialog;

    public UpdateShoppingProductEvent(ShoppingListController shoppingListController,
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
            ShoppingListProduct oldShoppingListProduct = shoppingListProduct;
            shoppingListController.updateShoppingListProduct(shoppingListProduct);

            shoppingListProductTableView.getItems().remove(oldShoppingListProduct);
            shoppingListProductTableView.getItems().add(shoppingListProduct);
            shoppingListDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
