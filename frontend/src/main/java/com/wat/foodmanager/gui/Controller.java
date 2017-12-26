package com.wat.foodmanager.gui;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ResourceBundle;

public class Controller {
    private ResourceBundle bundle;
    private ChoiceBox<String> choiceBox;

    private Button addProductButton;
    private Button foodStorageViewButton;
    private Button shoppingListViewButton;
    private Button storeProductButton;
    private Button addToShoppingListButton;
    private Button addProductShoppingListDialog;
    private ListView storageListView;
    private ListView shoppingListListView;

    private Label navLabelViewName;

    public Controller() {
        choiceBox = new ChoiceBox<>();

        addProductButton = I18N.buttonForKey("addProduct.button");
        foodStorageViewButton = I18N.buttonForKey("foodStorage.button");
        shoppingListViewButton = I18N.buttonForKey("shoppingList.button");
        storeProductButton = I18N.buttonForKey("storeProduct.button");
        addToShoppingListButton = I18N.buttonForKey("addToShoppingList.button");
        addProductShoppingListDialog = I18N.buttonForKey("addProductShoppingListDialog.button");
        storageListView = new ListView();
        shoppingListListView = new ListView();

        navLabelViewName = I18N.labelForKey("foodStorage.label");
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public Button getAddProductButton() {
        return addProductButton;
    }

    public ListView getStorageListView() {
        return storageListView;
    }

    public Button getFoodStorageViewButton() {
        return foodStorageViewButton;
    }

    public Button getShoppingListViewButton() {
        return shoppingListViewButton;
    }

    public Label getNavLabelViewName() {
        return navLabelViewName;
    }

    public Button getStoreProductButton() {
        return storeProductButton;
    }

    public Button getAddToShoppingListButton() {
        return addToShoppingListButton;
    }

    public ListView getShoppingListListView() {
        return shoppingListListView;
    }

    public Button getAddProductShoppingListDialog() {
        return addProductShoppingListDialog;
    }

    public void changeNavLabelViewName(String key) {
        navLabelViewName.setCache(false);
        navLabelViewName = I18N.labelForKey(key);
    }
}
