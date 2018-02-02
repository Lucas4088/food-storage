package gui;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.FieldsCannotBeEmptyException;
import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Recipe;
import com.wat.foodmanager.model.ShoppingListProduct;
import com.wat.foodmanager.model.StorageProduct;
import gui.controllers.CategoryController;
import gui.controllers.RecipesController;
import gui.controllers.ShoppingListController;
import gui.controllers.StorageProductController;
import gui.dialogs.CategoryDialog;
import gui.dialogs.RecipeDialog;
import gui.dialogs.ShoppingListDialog;
import gui.dialogs.StorageFoodDialog;
import gui.model.Action;
import gui.model.CategoryBottomOptionView;
import gui.model.ShoppingListBottomOptionView;
import gui.model.StorageFoodBottomOptionView;
import gui.model.events.*;
import gui.services.UnitService;
import gui.utils.I18N;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@ComponentScan("gui")
public class Main extends Application {

    private static FrontendController frontendController;
    private static ShoppingListController shoppingListController;
    private static StorageProductController storageProductController;
    private static RecipesController recipesController;
    private static UnitService unitService;
    private static TableView<StorageProduct> storageProductTableView = new TableView<>();
    private static List<TableColumn<StorageProduct, String>> storageProductTableColumns = new ArrayList<>();
    private static TableView<ShoppingListProduct> shoppingListProductTableView = new TableView<>();
    private static CategoryController categoryController;
    private static StorageFoodDialog storageFoodDialog;
    private static ShoppingListDialog shoppingListDialog;
    private static CategoryDialog categoryDialog;
    private List<String> languages;
    private VBox menuView;
    private ListView categoriesListView = new ListView();
    private ListView<Recipe> recipesListView = new ListView<>();

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FrontendContextConfiguration.class);

        initialize(context);

        launch(Main.class, args);

    }

    private static void initialize(ApplicationContext context) {

        frontendController = context.getBean(FrontendController.class);
        categoryController = context.getBean(CategoryController.class);
        shoppingListController = context.getBean(ShoppingListController.class);
        storageProductController = context.getBean(StorageProductController.class);
        recipesController = context.getBean(RecipesController.class);
        initializeStorageListView();
        initializeShoppingListView();
        unitService = context.getBean(UnitService.class);
    }

    private static void initializeShoppingListView() {
        shoppingListController.listShoppingListProducts().forEach(shoppingListProduct ->
                shoppingListProductTableView.getItems().add(shoppingListProduct)
        );

        Button deleteShoppingProductButton = frontendController.getDeleteShoppingProductButton();
        Button updateShoppingProductButton = frontendController.getUpdateShoppingProductButton();
        Button buyStorageProductButton = frontendController.getBuyStorageProductButton();
        Button buyShoppingProductButton = frontendController.getBuyShoppingProductButton();


        TableColumn<ShoppingListProduct, String> nameColumn = I18N.tableColumnForKey("name.product");
        nameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getName()));
        TableColumn<ShoppingListProduct, String> categoryColumn = I18N.tableColumnForKey("category.product");
        categoryColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getCategory().getName()));
        TableColumn<ShoppingListProduct, String> quantityColumn = I18N.tableColumnForKey("quantity.product");
        quantityColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getQuantity()));
        TableColumn<ShoppingListProduct, String> unitColumn = I18N.tableColumnForKey("unit.product");
        unitColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getUnit().getName()));

        shoppingListProductTableView.setRowFactory(param -> {
                    TableRow<ShoppingListProduct> tableRowShoppingList = new TableRow<>();
                    tableRowShoppingList.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2 && (!tableRowShoppingList.isEmpty())) {
                            ShoppingListProduct shoppingListProduct = tableRowShoppingList.getTableView().getSelectionModel().getSelectedItem();
                            ShoppingListDialog shoppingListDialog = new ShoppingListDialog(shoppingListProduct,
                                    categoryController,
                                    unitService,
                                    deleteShoppingProductButton,
                                    updateShoppingProductButton,
                                    buyShoppingProductButton,
                                    Action.PREVIEW);
                            shoppingListDialog.show();

                            updateShoppingProductButton.setOnAction(new UpdateShoppingProductEvent(
                                    shoppingListController,
                                    shoppingListProduct,
                                    shoppingListProductTableView,
                                    shoppingListDialog
                            ));
                            deleteShoppingProductButton.setOnAction(new DeleteShoppingProductEvent(
                                    shoppingListController,
                                    shoppingListProduct,
                                    shoppingListProductTableView,
                                    shoppingListDialog
                            ));

                            buyShoppingProductButton.setOnAction(event1 -> {
                                StorageProduct storageProduct = new StorageProduct.Builder().name(shoppingListDialog.getNameField().getText())
                                        .brand("")
                                        .category(categoryController.getCategoryByName(shoppingListDialog.getCategoryComboBox().getSelectionModel().getSelectedItem()))
                                        .dateOfPurchase(LocalDate.now())
                                        .expirationDate(null)
                                        .quantity(Integer.valueOf(shoppingListDialog.getQuantityField().getText()))
                                        .unit(unitService.getUnit(shoppingListDialog.getUnitComboBox().getSelectionModel().getSelectedItem().toString()))
                                        .build();
                                StorageFoodDialog storageFoodDialog = new StorageFoodDialog(
                                        storageProduct,
                                        deleteShoppingProductButton,
                                        updateShoppingProductButton,
                                        categoryController,
                                        unitService,
                                        storageProductController,
                                        buyStorageProductButton,
                                        Action.BUY);
                                storageFoodDialog.show();
                                buyStorageProductButton.setOnAction(event2 -> {
                                    try {
                                        storageFoodDialog.validateInput();
                                        StorageProduct storageProduct1 = new StorageProduct.Builder().name(storageFoodDialog.getNameField().getText())
                                                .brand(storageFoodDialog.getBrandField().getText())
                                                .category(categoryController.getCategoryByName(storageFoodDialog.getCategoryComboBox().getSelectionModel().getSelectedItem()))
                                                .dateOfPurchase(storageFoodDialog.getDateOfPurchasePicker().getValue())
                                                .expirationDate(storageFoodDialog.getExpirationDatePicker().getValue())
                                                .quantity(Integer.valueOf(storageFoodDialog.getQuantityField().getText()))
                                                .unit(unitService.getUnit(storageFoodDialog.getUnitComboBox().getSelectionModel().getSelectedItem().toString()))
                                                .build();
                                        ShoppingListProduct shoppingListProduct1 = tableRowShoppingList.getTableView().getSelectionModel().getSelectedItem();
                                        shoppingListController.buyShoppingListProduct(shoppingListProduct1, storageProduct1);

                                        storageProductTableView.getItems().add(storageProduct1);
                                        shoppingListProductTableView.getItems().remove(shoppingListProduct1);
                                        shoppingListDialog.close();
                                        storageFoodDialog.close();
                                    } catch (FieldsCannotBeEmptyException e) {
                                        (new Alert(Alert.AlertType.WARNING, e.getMessage())).show();
                                    }

                                });
                            });
                        }
                    });
                    return tableRowShoppingList;
                }
        );
        shoppingListProductTableView.getColumns().

                addAll(Arrays.asList(nameColumn, categoryColumn, quantityColumn, unitColumn));
        shoppingListProductTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private static void initializeStorageListView() {
        List<StorageProduct> storageProducts = storageProductController.listStoredProducts();
        Button updateProductButton = frontendController.getUpdateProductButton();
        Button deleteProductButton = frontendController.getDeleteProductButton();
        Button buyStorageProductButton = frontendController.getBuyStorageProductButton();

        storageProducts.forEach(storageProduct ->
                storageProductTableView.getItems().addAll(storageProduct)
        );

        TableColumn<StorageProduct, String> nameColumn = I18N.tableColumnForKey("name.product");
        nameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getName()));
        TableColumn<StorageProduct, String> brandColumn = I18N.tableColumnForKey("brand.product");
        brandColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getBrand()));
        TableColumn<StorageProduct, String> categoryColumn = I18N.tableColumnForKey("category.product");
        categoryColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getCategory().getName()));
        TableColumn<StorageProduct, String> expirationDateColumn = I18N.tableColumnForKey("expirationDate.product");
        expirationDateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getExpirationDate().toString()));
        TableColumn<StorageProduct, String> dateOfPurchaseColumn = I18N.tableColumnForKey("dateOfPurchase.product");
        dateOfPurchaseColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getDateOfPurchase()));
        TableColumn<StorageProduct, String> quantityColumn = I18N.tableColumnForKey("quantity.product");
        quantityColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getQuantity()));
        TableColumn<StorageProduct, String> unitColumn = I18N.tableColumnForKey("unit.product");
        unitColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getUnit().getName()));
        TableColumn<StorageProduct, String> openedColumn = I18N.tableColumnForKey("opened.product");
        openedColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getOpened()));
        storageProductTableView.setRowFactory(param -> {
            TableRow<StorageProduct> tableRow = new TableRow<StorageProduct>() {
                protected void updateItem(StorageProduct storageProduct, boolean flag) {
                    super.updateItem(storageProduct, flag);
                    int difference = -1000;
                    if (storageProduct != null)
                        difference = Period.between(LocalDate.now(), storageProduct.getExpirationDate()).getDays();
                    getStyleClass().clear();
                    if (difference == -1000) {

                    } else if (-1000 < difference && difference < 0) {
                        getStyleClass().add("expired-row");
                    } else if (0 <= difference && difference <= 1) {
                        getStyleClass().add("red-row");
                    } else if (1 < difference && difference <= 3) {
                        getStyleClass().add("orange-row");
                    } else if (3 < difference && difference <= 5) {
                        getStyleClass().add("yellow-row");
                    } else if (difference > 5) {
                        getStyleClass().add("green-row");
                    }

                }
            };
            tableRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!tableRow.isEmpty())) {
                    StorageProduct storageProduct = tableRow.getTableView().getSelectionModel().getSelectedItem();
                    storageFoodDialog = new StorageFoodDialog(storageProduct,
                            deleteProductButton,
                            updateProductButton,
                            categoryController,
                            unitService,
                            storageProductController,
                            buyStorageProductButton,
                            Action.EDIT);
                    storageFoodDialog.show();
                    deleteProductButton.setOnAction(new DeleteStorageProductEvent(
                            storageProductController,
                            storageProductTableView,
                            storageProduct,
                            storageFoodDialog
                    ));
                    updateProductButton.setOnAction(new UpdateStorageProductEvent(
                            storageProduct,
                            storageProductTableView,
                            storageProductController,
                            storageFoodDialog));
                }
            });
            return tableRow;
        });

        storageProductTableColumns.addAll(Arrays.asList(nameColumn, brandColumn, categoryColumn, expirationDateColumn,
                dateOfPurchaseColumn, quantityColumn, unitColumn, openedColumn));

        storageProductTableView.getColumns().addAll(storageProductTableColumns);
        storageProductTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        languages = new ArrayList<>(Arrays.asList("en", "pl"));
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5, 5, 5, 5));

        setNavToolbarView(root);

        setMainContentView(root);

        setSettingsToolBarView(root);

        setBottomOptionView(root);

        primaryStage.titleProperty().bind(I18N.createStringBinding("title.app"));
        primaryStage.setScene(new Scene(root, 800, 575));
        primaryStage.getScene().getStylesheets().add("styles/styles.css");
        primaryStage.getIcons().add(new Image("icon/FoodStorage.png"));
        primaryStage.show();

    }

    private void setSettingsToolBarView(BorderPane root) {
        ChoiceBox<String> langChoiceBox = new ChoiceBox<>();
        langChoiceBox.setItems(FXCollections.observableArrayList(languages));
        langChoiceBox.setValue(I18N.getDefaultLocale().toString());
        changeLocale(langChoiceBox);

        ChoiceBox<String> skinsChoiceBox = new ChoiceBox<>();

        skinsChoiceBox.setItems(FXCollections.observableArrayList(
                "Default", "Caspian", "Modena"
        ));

        skinsChoiceBox.getSelectionModel().select(0);
        changeSkin(skinsChoiceBox, root);

        setUserAgentStylesheet(STYLESHEET_MODENA);

        HBox settingsPane = new HBox(10);

        Label navLabelViewName = frontendController.getNavLabelViewName();

        settingsPane.setPadding(new Insets(5, 5, 5, 5));
        settingsPane.setPrefHeight(20);
        settingsPane.getChildren().add(navLabelViewName);
        settingsPane.getChildren().add(skinsChoiceBox);
        settingsPane.getChildren().add(langChoiceBox);
        settingsPane.setAlignment(Pos.CENTER_RIGHT);
        root.setTop(settingsPane);
    }

    private void changeSkin(ChoiceBox<String> skinChoiceBox, BorderPane root) {
        skinChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue.intValue()) {
                        case 0:
                            setUserAgentStylesheet("styles/flat.css");
                            break;
                        case 1:
                            setUserAgentStylesheet(STYLESHEET_CASPIAN);
                            break;
                        case 2:
                            setUserAgentStylesheet(STYLESHEET_MODENA);
                            break;
                    }
                }
        );

    }

    private void changeLocale(ChoiceBox<String> langChoiceBox) {
        langChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> switchLanguage(new Locale(languages.get(newValue.intValue())))
        );
    }

    private void switchLanguage(Locale locale) {
        I18N.setLocale(locale);
    }

    private void setNavToolbarView(BorderPane root) {
        menuView = new VBox(0);
        menuView.setPrefWidth(150);

        ToggleGroup toggleGroup = new ToggleGroup();

        ToggleButton foodStorageViewButton = frontendController.getFoodStorageViewButton();
        foodStorageViewButton.setPrefWidth(menuView.getPrefWidth());
        foodStorageViewButton.setPadding(new Insets(10, 10, 10, 10));
        foodStorageViewButton.setOnAction(
                event -> {
                    frontendController.changeNavLabelViewName("foodStorage.label");
                    setStorageFoodBottomOptionView(root);
                    setStorageFoodContentView(root);
                }
        );
        foodStorageViewButton.setToggleGroup(toggleGroup);

        ToggleButton shoppingListViewButton = frontendController.getShoppingListViewButton();
        shoppingListViewButton.setOnAction(
                event -> {
                    frontendController.changeNavLabelViewName("shoppingList.label");
                    setShoppingListBottomOptionView(root);
                    setShoppingListContentView(root);
                }
        );
        shoppingListViewButton.setPrefWidth(menuView.getPrefWidth());
        shoppingListViewButton.setPadding(new Insets(10, 10, 10, 10));
        shoppingListViewButton.setToggleGroup(toggleGroup);

        ToggleButton categoryListViewButton = frontendController.getCategoryListViewButton();
        categoryListViewButton.setOnAction(
                event -> {
                    frontendController.changeNavLabelViewName("categories.label");
                    setCategoriesBottomOptionView(root);
                    setCategoriesListContentView(root);
                }
        );
        categoryListViewButton.setToggleGroup(toggleGroup);
        categoryListViewButton.setPrefWidth(menuView.getPrefWidth());
        categoryListViewButton.setPadding(new Insets(10, 10, 10, 10));

        ToggleButton recipesListViewButton = frontendController.getRecipesListViewButton();
        recipesListViewButton.setOnAction(
                event -> {
                    frontendController.changeNavLabelViewName("recipesList.label");
                    setRecipesBottomOptionView(root);
                    setRecipesListView(root);
                }
        );
        recipesListViewButton.setToggleGroup(toggleGroup);
        recipesListViewButton.setPrefWidth(menuView.getPrefWidth());
        recipesListViewButton.setPadding(new Insets(10, 10, 10, 10));

        menuView.getChildren().addAll(foodStorageViewButton, shoppingListViewButton, categoryListViewButton,
                recipesListViewButton);
        root.setLeft(menuView);
    }

    private void setCategoriesListContentView(BorderPane root) {
        categoriesListView.setItems(FXCollections.observableArrayList(categoryController.listCategories()
                .stream()
                .map(AbstractCategory::getName)
                .collect(Collectors.toList())));

        root.setCenter(categoriesListView);
    }

    private void setRecipesListView(BorderPane root) {
        root.setCenter(recipesListView);
        recipesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                if (recipesListView.getSelectionModel().getSelectedItem() != null) {
                    Recipe recipe = null;
                    try {
                        recipe = recipesController.getRecipe(recipesListView.getSelectionModel().getSelectedItem().getId());
                    } catch (UnableToFindRecipesListException unableToFindRecipesListException) {
                        new Alert(Alert.AlertType.WARNING, "Unable to find recipe list");
                    }
                    (new RecipeDialog(recipe)).show();
                }
            }
        });
    }

    private void setMainContentView(BorderPane root) {
        setStorageFoodContentView(root);
    }

    private void setStorageFoodContentView(BorderPane root) {
        root.setCenter(storageProductTableView);
    }

    private void setShoppingListContentView(BorderPane root) {
        root.setCenter(shoppingListProductTableView);
    }

    private void setBottomOptionView(BorderPane root) {
        setStorageFoodBottomOptionView(root);
    }

    private void setStorageFoodBottomOptionView(BorderPane root) {
        (new StorageFoodBottomOptionView(frontendController.getAddProductButton(),
                frontendController,
                root,
                storageFoodDialog,
                storageProductTableView,
                storageProductController,
                categoryController,
                unitService)).composeOptionView();
    }

    private void setShoppingListBottomOptionView(BorderPane root) {
        (new ShoppingListBottomOptionView(frontendController.getAddToShoppingListButton(),
                frontendController,
                root,
                shoppingListDialog,
                shoppingListProductTableView,
                shoppingListController,
                categoryController,
                unitService)).composeOptionView();
    }

    private void setCategoriesBottomOptionView(BorderPane root) {
        (new CategoryBottomOptionView(frontendController.getAddCategoryViewButton(),
                frontendController,
                root,
                categoryDialog,
                categoryController,
                categoriesListView
        )).composeOptionView();
    }

    private void setRecipesBottomOptionView(BorderPane root) {
        Button searchForRecipesButton = frontendController.getSearchForRecipesButton();
        searchForRecipesButton.setPadding(new Insets(10, 10, 10, 10));


        TextField searchRecipeField = new TextField();//frontendController.getRecipeSearchParametersField();
        searchRecipeField.setPadding(new Insets(10, 10, 10, 10));
        searchRecipeField.setPrefWidth(570);
        searchRecipeField.setEditable(true);

        searchForRecipesButton.setOnAction(new SearchForRecipesEvent(
                recipesListView,
                recipesController,
                searchRecipeField
        ));

        HBox buttonPane = new HBox(1, searchRecipeField, searchForRecipesButton);

        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        buttonPane.setFillHeight(false);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(buttonPane);

    }
}