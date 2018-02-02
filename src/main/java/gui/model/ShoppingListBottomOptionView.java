package gui.model;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.Unit;
import gui.FrontendController;
import gui.controllers.CategoryController;
import gui.controllers.ShoppingListController;
import gui.dialogs.ShoppingListDialog;
import gui.services.UnitService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.stream.Collectors;


public class ShoppingListBottomOptionView extends BottomOptionView {

    private ShoppingListDialog shoppingListDialog;
    private TableView shoppingListProductTableView;
    private ShoppingListController shoppingListController;
    private CategoryController categoryController;
    private UnitService unitService;


    public ShoppingListBottomOptionView(Button customButton,
                                        FrontendController frontendController,
                                        BorderPane root,
                                        ShoppingListDialog shoppingListDialog,
                                        TableView shoppingListProductTableView,
                                        ShoppingListController shoppingListController,
                                        CategoryController categoryController,
                                        UnitService unitService) {
        super(customButton, frontendController, root);
        this.shoppingListDialog = shoppingListDialog;
        this.shoppingListProductTableView = shoppingListProductTableView;
        this.shoppingListController = shoppingListController;
        this.categoryController = categoryController;
        this.unitService = unitService;
    }

    @Override
    protected void setAction() {
        this.customButton.setOnAction(event -> openAddProductShoppingListDialog());
    }

    private void openAddProductShoppingListDialog() {
        Button storeProductButton = frontendController.getAddProductToShoppingListDialog();
        shoppingListDialog = new ShoppingListDialog(
                storeProductButton,
                Action.ADD
        );
        shoppingListDialog.getCategoryComboBox().getItems().setAll(
                categoryController.listCategories().stream()
                        .map(AbstractCategory::getName)
                        .collect(Collectors.toList()));

        shoppingListDialog.getUnitComboBox().getItems().setAll(
                unitService.listUnits()
                        .stream()
                        .map(Unit::getName)
                        .collect(Collectors.toList())
        );

        storeProductButton.setOnAction(event -> storeShoppingListProduct());

        shoppingListDialog.getDialogPane().addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                storeShoppingListProduct();
            }
        });
        shoppingListDialog.show();
    }

    private void storeShoppingListProduct() {
        try {
            shoppingListDialog.validateInput();
            ShoppingListProduct shoppingListProduct = new ShoppingListProduct(
                    shoppingListDialog.getNameField().getText(),
                    categoryController.getCategoryByName(shoppingListDialog.getCategoryComboBox().getSelectionModel().getSelectedItem()),
                    Integer.valueOf(shoppingListDialog.getQuantityField().getText()),
                    unitService.getUnit(shoppingListDialog.getUnitComboBox().getSelectionModel().getSelectedItem().toString())
            );
            shoppingListController.addShoppingListProduct(
                    shoppingListProduct
            );
            shoppingListProductTableView.getItems().add(shoppingListProduct);
            shoppingListDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
