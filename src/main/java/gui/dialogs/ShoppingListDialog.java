package gui.dialogs;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.Unit;
import gui.controllers.CategoryController;
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


public class ShoppingListDialog extends Dialog<Pair<String, String>> {

    private TextField nameField;
    private ComboBox<String> categoryComboBox;
    private TextField quantityField;
    private ComboBox unitComboBox;

    private Button deleteShoppingProductButton;
    private Button updateShoppingProductButton;
    private Button storeProductButton;
    private Button buyShoppingProductButton;


    private CategoryController categoryController;
    private UnitService unitService;
    private Action action;


    public ShoppingListDialog() {
        initializeView();
    }

    public ShoppingListDialog(Button storeProductButton,
                              Action action) {
        this.nameField = new TextField();
        this.categoryComboBox = new ComboBox<>();
        this.quantityField = new TextField();
        this.unitComboBox = new ComboBox();
        this.storeProductButton = storeProductButton;
        this.action = action;

        initializeView();
    }

    public ShoppingListDialog(ShoppingListProduct shoppingListProduct,
                              CategoryController categoryController,
                              UnitService unitService,
                              Button deleteShoppingProductButton,
                              Button updateShoppingProductButton,
                              Button buyShoppingProductButton,
                              Action action) {
        this.deleteShoppingProductButton = deleteShoppingProductButton;
        this.updateShoppingProductButton = updateShoppingProductButton;
        this.buyShoppingProductButton = buyShoppingProductButton;

        this.categoryController = categoryController;
        this.unitService = unitService;

        this.nameField = new TextField(shoppingListProduct.getName());
        this.categoryComboBox = new ComboBox<>();
        categoryComboBox.getSelectionModel().select(shoppingListProduct.getCategory().getName());
        categoryComboBox.getItems().addAll(categoryController.listCategories()
                .stream()
                .map(AbstractCategory::getName)
                .collect(Collectors.toList()));
        this.quantityField = new TextField(shoppingListProduct.getQuantity().toString());
        this.unitComboBox = new ComboBox();
        unitComboBox.getSelectionModel().select(shoppingListProduct.getUnit().getName());
        unitComboBox.getItems().addAll(unitService.listUnits()
                .stream()
                .map(Unit::getName)
                .collect(Collectors.toList())
        );
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
        Label category = I18N.labelForKey("category.product");
        categoryComboBox.setPromptText(I18N.get("category.product"));

        Label quantity = I18N.labelForKey("quantity.product");
        quantityField.setPromptText(I18N.get("quantity.product"));
        unitComboBox.setPromptText(I18N.get("unit.product"));

        grid.addColumn(0,
                name,
                category,
                quantity);

        grid.add(nameField, 1, 0, 2, 1);
        grid.add(categoryComboBox, 1, 1, 2, 1);
        grid.add(quantityField, 1, 2, 1, 1);
        grid.add(unitComboBox, 2, 2, 1, 1);
        grid.setPadding(new Insets(10, 10, 10, 10));

        if (action == Action.ADD) {
            storeProductButton.setPadding(new Insets(10, 10, 10, 10));
            GridPane.setMargin(storeProductButton, new Insets(15, 5, 0, 5));
            grid.add(storeProductButton, 2, 3, 1, 1);
        } else {
            updateShoppingProductButton.setPadding(new Insets(10, 10, 10, 10));
            deleteShoppingProductButton.setPadding(new Insets(10, 10, 10, 10));
            buyShoppingProductButton.setPadding(new Insets(10, 10, 10, 10));

            GridPane.setMargin(updateShoppingProductButton, new Insets(15, 5, 0, 5));
            GridPane.setMargin(deleteShoppingProductButton, new Insets(15, 5, 0, 5));
            GridPane.setMargin(buyShoppingProductButton, new Insets(15, 5, 0, 5));

            grid.add(updateShoppingProductButton, 2, 3, 1, 1);
            grid.add(deleteShoppingProductButton, 1, 3, 1, 1);
            grid.add(buyShoppingProductButton, 0, 3, 1, 1);
        }
        titleProperty().bind(I18N.createStringBinding("addProductShoppingListDialog.title"));

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

    public ComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public void setCategoryComboBox(ComboBox<String> categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public void setQuantityField(TextField quantityField) {
        this.quantityField = quantityField;
    }

    public ComboBox getUnitComboBox() {
        return unitComboBox;
    }

    public void setUnitComboBox(ComboBox unitComboBox) {
        this.unitComboBox = unitComboBox;
    }
}
