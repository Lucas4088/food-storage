package com.wat.foodmanager.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class Main extends Application {

    private List<String> languages;

    private Controller controller;
    private VBox menuView;

    private Scene storageFoodScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        controller = new Controller();
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
        primaryStage.show();
    }

    private void setSettingsToolBarView(BorderPane root) {
        ChoiceBox<String> langChoiceBox = controller.getChoiceBox();
        langChoiceBox.setItems(FXCollections.observableArrayList(languages));
        langChoiceBox.setValue(I18N.getDefaultLocale().toString());
        changeLocale(langChoiceBox);

        HBox settingsPane = new HBox(10);

        Label navLabelViewName = controller.getNavLabelViewName();

        settingsPane.setPadding(new Insets(5, 5, 5, 5));
        settingsPane.setPrefHeight(20);
        settingsPane.getChildren().add(navLabelViewName);
        settingsPane.getChildren().add(langChoiceBox);
        settingsPane.setAlignment(Pos.CENTER_RIGHT);
        root.setTop(settingsPane);
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

        Button foodStorageViewButton = controller.getFoodStorageViewButton();
        foodStorageViewButton.setPrefWidth(menuView.getPrefWidth());
        foodStorageViewButton.setPadding(new Insets(10, 10, 10, 10));
        foodStorageViewButton.setOnAction(
                event -> {
                    controller.changeNavLabelViewName("foodStorage.label");
                    setStorageFoodBottomOptionView(root);
                    setStorageFoodContentView(root);
                }
        );

        Button shoppingListViewButton = controller.getShoppingListViewButton();
        shoppingListViewButton.setOnAction(
                event -> {
                    controller.changeNavLabelViewName("shoppingList.label");
                    setShoppingListBottomOptionView(root);
                    setShoppingListContentView(root);
                }
        );
        shoppingListViewButton.setPrefWidth(menuView.getPrefWidth());
        shoppingListViewButton.setPadding(new Insets(10, 10, 10, 10));

        menuView.getChildren().addAll(foodStorageViewButton, shoppingListViewButton);
        root.setLeft(menuView);
    }

    private void setMainContentView(BorderPane root) {
        setStorageFoodContentView(root);
    }

    private void setStorageFoodContentView(BorderPane root) {
        ListView storageListView = controller.getStorageListView();
        root.setCenter(storageListView);
    }

    private void setShoppingListContentView(BorderPane root) {
        ListView shoppingListListView = controller.getShoppingListListView();
        root.setCenter(shoppingListListView);
    }

    private void setBottomOptionView(BorderPane root) {
        setStorageFoodBottomOptionView(root);
    }

    private void setStorageFoodBottomOptionView(BorderPane root) {

        Button addProductButton = controller.getAddProductButton();
        addProductButton.setPadding(new Insets(10, 10, 10, 10));
        addProductButton.setOnAction(
                event -> openAddProductDialog()
        );

        HBox buttonPane = new HBox(1, addProductButton);
        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        buttonPane.setFillHeight(false);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(buttonPane);
    }

    private void setShoppingListBottomOptionView(BorderPane root) {

        Button addShoppingListButton = controller.getAddToShoppingListButton();
        addShoppingListButton.setPadding(new Insets(10, 10, 10, 10));
        addShoppingListButton.setOnAction(
                event -> openAddProductShoppingListDialog()
        );

        HBox buttonPane = new HBox(1, addShoppingListButton);
        buttonPane.setPadding(new Insets(10, 10, 10, 10));

        buttonPane.setFillHeight(false);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(buttonPane);
    }

    private void openAddProductDialog() {
        Dialog<Pair<String, String>> openAddProductDialog = new Dialog<>();

        openAddProductDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = openAddProductDialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(3);
        grid.setPadding(new Insets(20, 20, 1, 20));
        openAddProductDialog.getDialogPane().setContent(grid);

        Label name = I18N.labelForKey("name.product");
        TextField nameField = new TextField();
        nameField.setPromptText(I18N.get("name.product"));
        Label brand = I18N.labelForKey("brand.product");
        TextField brandField = new TextField();
        brandField.setPromptText(I18N.get("brand.product"));
        Label category = I18N.labelForKey("category.product");
        TextField categoryField = new TextField();
        categoryField.setPromptText(I18N.get("category.product"));
        Label expirationDate = I18N.labelForKey("expirationDate.product");
        TextField expirationDateField = new TextField();
        expirationDateField.setPromptText(I18N.get("expirationDate.product"));
        Label quantity = I18N.labelForKey("quantity.product");
        TextField quantityField = new TextField();
        quantityField.setPromptText(I18N.get("quantity.product"));
        TextField unit = new TextField();
        unit.setPromptText(I18N.get("unit.product"));

        Button storeProductButton = controller.getStoreProductButton();
        grid.addColumn(0,
                name,
                brand,
                category,
                expirationDate,
                quantity);

        grid.add(nameField,1,0,2,1);
        grid.add(brandField,1,1,2,1);
        grid.add(categoryField,1,2,2,1);
        grid.add(expirationDateField,1,3,2,1);
        grid.add(quantityField,1,4,1,1);
        grid.add(unit,2,4,1,1);
        grid.setPadding(new Insets(10,10,10,10));

        storeProductButton.setPadding(new Insets(10,10,10,10));
        GridPane.setMargin(storeProductButton,new Insets(15,5,0,5));
        grid.add(storeProductButton,2,5,1,1);
        openAddProductDialog.show();
        openAddProductDialog.titleProperty().bind(I18N.createStringBinding("addProductDialog.title"));
    }

    private void openAddProductShoppingListDialog() {
        Dialog<Pair<String, String>> openAddProductShoppingListDialog = new Dialog<>();

        openAddProductShoppingListDialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = openAddProductShoppingListDialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(3);
        grid.setPadding(new Insets(20, 20, 1, 20));
        openAddProductShoppingListDialog.getDialogPane().setContent(grid);

        Label name = I18N.labelForKey("name.product");
        TextField nameField = new TextField();
        nameField.setPromptText(I18N.get("name.product"));
        Label category = I18N.labelForKey("category.product");
        TextField categoryField = new TextField();
        categoryField.setPromptText(I18N.get("category.product"));
        Label quantity = I18N.labelForKey("quantity.product");
        TextField quantityField = new TextField();
        quantityField.setPromptText(I18N.get("quantity.product"));
        TextField unit = new TextField();
        unit.setPromptText(I18N.get("unit.product"));

        Button storeProductButton = controller.getStoreProductButton();
        grid.addColumn(0,
                name,
                category,
                quantity);

        grid.add(nameField,1,0,2,1);
        grid.add(categoryField,1,1,2,1);
        grid.add(quantityField,1,2,1,1);
        grid.add(unit,2,2,1,1);
        grid.setPadding(new Insets(10,10,10,10));

        storeProductButton.setPadding(new Insets(10,10,10,10));
        GridPane.setMargin(storeProductButton,new Insets(15,5,0,5));
        grid.add(storeProductButton,2,3,1,1);
        openAddProductShoppingListDialog.show();
        openAddProductShoppingListDialog.titleProperty().bind(I18N.createStringBinding("addProductShoppingListDialog.title"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}