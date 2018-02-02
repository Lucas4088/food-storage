package gui.dialogs;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.StorageProduct;
import com.wat.foodmanager.model.Unit;
import gui.controllers.CategoryController;
import gui.controllers.StorageProductController;
import gui.model.Action;
import gui.services.UnitService;
import gui.utils.I18N;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageFoodDialog extends Dialog<Pair<String, String>> {
    private TextField nameField;
    private TextField brandField;
    private ComboBox<String> categoryComboBox;
    private DatePicker expirationDatePicker;
    private DatePicker dateOfPurchasePicker;
    private TextField quantityField;
    private ComboBox<String> unitComboBox;

    private Button storeProductButton;
    private Button deleteProductButton;
    private Button updateProductButton;
    private Button buyStorageProductButton;

    private Action action;
    private StorageProductController storageProductController;
    private CategoryController categoryController;
    private UnitService unitService;

    public StorageFoodDialog(StorageProduct storageProduct,
                             Button deleteProductButton,
                             Button updateProductButton,
                             CategoryController categoryController,
                             UnitService unitService,
                             StorageProductController storageProductController,
                             Button buyStorageProductButton,
                             Action action) {
        this.categoryController = categoryController;
        this.buyStorageProductButton = buyStorageProductButton;
        this.unitService = unitService;
        nameField = new TextField(storageProduct.getName());
        brandField = new TextField(storageProduct.getBrand());
        categoryComboBox = new ComboBox<>();
        categoryComboBox.getSelectionModel().select(storageProduct.getCategory().getName());
        categoryComboBox.getItems().setAll(
                categoryController.listCategories().stream()
                        .map(AbstractCategory::getName)
                        .collect(Collectors.toList()));
        dateOfPurchasePicker = new DatePicker(storageProduct.getDateOfPurchase());
        expirationDatePicker = new DatePicker(storageProduct.getExpirationDate());
        quantityField = new TextField(storageProduct.getQuantity().toString());
        unitComboBox = new ComboBox<>();
        unitComboBox.getSelectionModel().select(storageProduct.getUnit().getName());
        unitComboBox.getItems().setAll(
                unitService.listUnits().stream()
                        .map(Unit::getName)
                        .collect(Collectors.toList()));
        storeProductButton = new Button();

        this.deleteProductButton = deleteProductButton;
        this.updateProductButton = updateProductButton;
        this.buyStorageProductButton = buyStorageProductButton;

        this.action = action;
        initializeView();

    }

    public StorageFoodDialog(Button storeProductButton, Action action) {
        this.nameField = new TextField();
        this.brandField = new TextField();
        this.categoryComboBox = new ComboBox<>();
        this.expirationDatePicker = new DatePicker();
        this.dateOfPurchasePicker = new DatePicker();
        this.quantityField = new TextField();
        this.unitComboBox = new ComboBox<>();
        this.storeProductButton = storeProductButton;
        this.action = action;

        initializeView();
    }

    private void initializeView() {
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(3);
        grid.setPadding(new Insets(20, 20, 1, 20));
        getDialogPane().setContent(grid);
        Label name = I18N.labelForKey("name.product");
        nameField.setPromptText(I18N.get("name.product"));
        Label brand = I18N.labelForKey("brand.product");
        brandField.setPromptText(I18N.get("brand.product"));
        Label category = I18N.labelForKey("category.product");
        categoryComboBox.setPromptText(I18N.get("category.product"));

        Label expirationDate = I18N.labelForKey("expirationDate.product");
        Label dateOfPurchase = I18N.labelForKey("dateOfPurchase.product");
        expirationDatePicker.setPromptText(I18N.get("expirationDate.product"));
        Label quantity = I18N.labelForKey("quantity.product");
        quantityField.setPromptText(I18N.get("quantity.product"));
        unitComboBox.setPromptText(I18N.get("unit.product"));

        grid.addColumn(0,
                name,
                brand,
                category,
                dateOfPurchase,
                expirationDate,
                quantity);

        grid.add(nameField, 1, 0, 2, 1);
        grid.add(brandField, 1, 1, 2, 1);
        grid.add(categoryComboBox, 1, 2, 2, 1);
        grid.add(dateOfPurchasePicker, 1, 3, 2, 1);
        grid.add(expirationDatePicker, 1, 4, 2, 1);
        grid.add(quantityField, 1, 5, 1, 1);
        grid.add(unitComboBox, 2, 5, 1, 1);
        grid.setPadding(new Insets(10, 10, 10, 10));

        storeProductButton.setPadding(new Insets(10, 10, 10, 10));

        GridPane.setMargin(storeProductButton, new Insets(15, 5, 0, 5));
        if (action == Action.ADD) {
            grid.add(storeProductButton, 2, 6, 1, 1);
            titleProperty().bind(I18N.createStringBinding("addProductDialog.title"));
        } else if (action == Action.BUY) {
            buyStorageProductButton.setPadding(new Insets(10, 10, 10, 10));
            grid.add(buyStorageProductButton, 2, 6, 1, 1);
            titleProperty().bind(I18N.createStringBinding("buyShoppingProduct.title"));
            nameField.disabledProperty();
            categoryComboBox.disabledProperty();
        } else {
            deleteProductButton.setPadding(new Insets(10, 10, 10, 10));
            updateProductButton.setPadding(new Insets(10, 10, 10, 10));
            storeProductButton.setPadding(new Insets(10, 10, 10, 10));
            grid.add(deleteProductButton, 2, 6, 1, 1);
            grid.add(updateProductButton, 1, 6, 1, 1);
            titleProperty().bind(I18N.createStringBinding("previewStorageFood.dialog"));
        }


    }

    public void validateInput() throws FieldsCannotBeEmptyException {
        List<String> emptyFields = new ArrayList<>();

        if (StringUtils.trimAllWhitespace(nameField.getText()).isEmpty()) {
            emptyFields.add("name");
        }

        if (categoryComboBox.getSelectionModel().isEmpty()) {
            emptyFields.add("category");
        }

        if (StringUtils.trimAllWhitespace(quantityField.getText()).isEmpty()) {
            emptyFields.add("quantity");
        }

        if (unitComboBox.getSelectionModel().isEmpty()) {
            emptyFields.add("unit");
        }

        if (dateOfPurchasePicker.getEditor().getText().isEmpty()) {
            emptyFields.add("date of purchase");
        }

        if (expirationDatePicker.getEditor().getText().isEmpty()) {
            emptyFields.add("expiration date");
        }

        if (!emptyFields.isEmpty()) {
            throw new FieldsCannotBeEmptyException(String.join(", ", emptyFields));
        }
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getBrandField() {
        return brandField;
    }

    public void setBrandField(TextField brandField) {
        this.brandField = brandField;
    }

    public ComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public void setCategoryComboBox(ComboBox<String> categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public DatePicker getExpirationDatePicker() {
        return expirationDatePicker;
    }

    public void setExpirationDatePicker(DatePicker expirationDatePicker) {
        this.expirationDatePicker = expirationDatePicker;
    }

    public DatePicker getDateOfPurchasePicker() {
        return dateOfPurchasePicker;
    }

    public void setDateOfPurchasePicker(DatePicker dateOfPurchasePicker) {
        this.dateOfPurchasePicker = dateOfPurchasePicker;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(TextField quantityField) {

        this.quantityField = quantityField;
    }

    public ComboBox<String> getUnitComboBox() {
        return unitComboBox;
    }

    public void setUnitComboBox(ComboBox<String> unitComboBox) {
        this.unitComboBox = unitComboBox;
    }
}