package gui.model;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.StorageProduct;
import com.wat.foodmanager.model.Unit;
import gui.FrontendController;
import gui.controllers.CategoryController;
import gui.controllers.StorageProductController;
import gui.dialogs.StorageFoodDialog;
import gui.services.UnitService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.stream.Collectors;

public class StorageFoodBottomOptionView extends BottomOptionView {

    private StorageFoodDialog storageFoodDialog;
    private TableView storageProductTableView;
    private StorageProductController storageProductController;
    private CategoryController categoryController;
    private UnitService unitService;

    public StorageFoodBottomOptionView(Button customButton, FrontendController frontendController,
                                       BorderPane root,
                                       StorageFoodDialog storageFoodDialog,
                                       TableView storageProductTableView,
                                       StorageProductController storageProductController,
                                       CategoryController categoryController,
                                       UnitService unitService) {
        super(customButton, frontendController, root);
        this.storageFoodDialog = storageFoodDialog;
        this.storageProductTableView = storageProductTableView;
        this.storageProductController = storageProductController;
        this.categoryController = categoryController;
        this.unitService = unitService;
    }

    @Override
    protected void setAction() {
        customButton.setOnAction(event -> openAddProductDialog());
    }

    private void openAddProductDialog() {
        Button storeProductButton = frontendController.getStoreProductButton();

        storageFoodDialog = new StorageFoodDialog(
                storeProductButton,
                Action.ADD
        );

        storageFoodDialog.getCategoryComboBox().getItems().setAll(
                categoryController.listCategories().stream()
                        .map(AbstractCategory::getName)
                        .collect(Collectors.toList()));

        storageFoodDialog.getUnitComboBox().getItems().setAll(
                unitService.listUnits()
                        .stream()
                        .map(Unit::getName)
                        .collect(Collectors.toList())
        );

        storeProductButton.setOnAction(event -> storeProduct()
        );

        storageFoodDialog.getDialogPane().addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                storeProduct();
            }
        });
        storageFoodDialog.show();
    }

    private void storeProduct() {
        try {
            storageFoodDialog.validateInput();
            StorageProduct storageProduct = new StorageProduct.Builder().name(storageFoodDialog.getNameField().getText())
                    .brand(storageFoodDialog.getBrandField().getText())
                    .category(categoryController.getCategoryByName(storageFoodDialog.getCategoryComboBox().getSelectionModel().getSelectedItem()))
                    .dateOfPurchase(storageFoodDialog.getDateOfPurchasePicker().getValue())
                    .expirationDate(storageFoodDialog.getExpirationDatePicker().getValue())
                    .quantity(Integer.valueOf(storageFoodDialog.getQuantityField().getText()))
                    .unit(unitService.getUnit(storageFoodDialog.getUnitComboBox().getSelectionModel().getSelectedItem()))
                    .build();

            storageProductController.addStoredProduct(
                    storageProduct
            );
            storageProductTableView.getItems().add(storageProduct);
            storageFoodDialog.close();
        } catch (FieldsCannotBeEmptyException e) {
            (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
        }
    }
}
