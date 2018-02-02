package gui;

import gui.dialogs.CategoryDialog;
import gui.dialogs.ShoppingListDialog;
import gui.dialogs.StorageFoodDialog;
import gui.utils.I18N;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import org.springframework.stereotype.Component;

@Component
public class FrontendController {

    private Button addProductButton;
    private ToggleButton foodStorageViewButton;
    private ToggleButton shoppingListViewButton;
    private ToggleButton categoryListViewButton;
    private ToggleButton recipesListViewButton;

    private Button storeProductButton;
    private Button deleteProductButton;
    private Button updateProductButton;
    private Button buyStorageProductButton;

    private Button deleteShoppingProductButton;
    private Button updateShoppingProductButton;
    private Button buyShoppingProductButton;

    private Button addToShoppingListButton;
    private Button addProductToShoppingListDialog;
    private Button addCategoryViewButton;
    private Button addCategoryDialogButton;

    private Button searchForRecipesButton;

    private CategoryDialog categoryDialog;
    private ShoppingListDialog shoppingListDialog;
    private StorageFoodDialog storageFoodDialog;

    private TextField recipeSearchParametersField;

    private Label navLabelViewName;


    public FrontendController() {
        addProductButton = I18N.buttonForKey("addProduct.button");
        foodStorageViewButton = I18N.toggleButtonForKey("foodStorage.button");
        shoppingListViewButton = I18N.toggleButtonForKey("shoppingList.button");
        categoryListViewButton = I18N.toggleButtonForKey("categoryList.button");
        recipesListViewButton = I18N.toggleButtonForKey("recipesList.button");

        storeProductButton = I18N.buttonForKey("storeProduct.button");
        updateProductButton = I18N.buttonForKey("updateProduct.button");
        deleteProductButton = I18N.buttonForKey("deleteProduct.button");
        buyStorageProductButton = I18N.buttonForKey("buyStorageProduct.button");
        searchForRecipesButton = I18N.buttonForKey("searchRecipe.button");
        recipeSearchParametersField = I18N.textFieldForKey("recipeSearch.field");

        updateShoppingProductButton = I18N.buttonForKey("updateProduct.button");
        deleteShoppingProductButton = I18N.buttonForKey("deleteProduct.button");
        buyShoppingProductButton = I18N.buttonForKey("buy.button");

        addToShoppingListButton = I18N.buttonForKey("addToShoppingList.button");
        addProductToShoppingListDialog = I18N.buttonForKey("addProductShoppingListDialog.button");
        addCategoryViewButton = I18N.buttonForKey("addCategoryView.button");
        addCategoryDialogButton = I18N.buttonForKey("addCategoryDialog.button");

        navLabelViewName = I18N.labelForKey("foodStorage.label");
    }

    public Button getSearchForRecipesButton() {
        return searchForRecipesButton;
    }

    public ToggleButton getRecipesListViewButton() {
        return recipesListViewButton;
    }

    public void setRecipesListViewButton(ToggleButton recipesListViewButton) {
        this.recipesListViewButton = recipesListViewButton;
    }

    public CategoryDialog getCategoryDialog() {
        return categoryDialog;
    }

    public void setCategoryDialog(CategoryDialog categoryDialog) {
        this.categoryDialog = categoryDialog;
    }

    public Button getBuyShoppingProductButton() {
        return buyShoppingProductButton;
    }

    public ShoppingListDialog getShoppingListDialog() {
        return shoppingListDialog;
    }

    public void setShoppingListDialog(ShoppingListDialog shoppingListDialog) {
        this.shoppingListDialog = shoppingListDialog;
    }

    public StorageFoodDialog getStorageFoodDialog() {
        return storageFoodDialog;
    }

    public void setStorageFoodDialog(StorageFoodDialog storageFoodDialog) {
        this.storageFoodDialog = storageFoodDialog;
    }

    public TextField getRecipeSearchParametersField() {
        return recipeSearchParametersField;
    }

    public Button getBuyStorageProductButton() {
        return buyStorageProductButton;
    }

    public Button getDeleteShoppingProductButton() {
        return deleteShoppingProductButton;
    }

    public Button getUpdateShoppingProductButton() {
        return updateShoppingProductButton;
    }

    public Button getAddProductButton() {
        return addProductButton;
    }


    public ToggleButton getFoodStorageViewButton() {
        return foodStorageViewButton;
    }

    public ToggleButton getShoppingListViewButton() {
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


    public Button getAddProductToShoppingListDialog() {
        return addProductToShoppingListDialog;
    }

    public ToggleButton getCategoryListViewButton() {
        return categoryListViewButton;
    }

    public Button getAddCategoryViewButton() {
        return addCategoryViewButton;
    }

    public Button getAddCategoryDialogButton() {
        return addCategoryDialogButton;
    }

    public Button getDeleteProductButton() {
        return deleteProductButton;
    }

    public Button getUpdateProductButton() {
        return updateProductButton;
    }

    public void changeNavLabelViewName(String key) {
        navLabelViewName.setCache(false);
        navLabelViewName = I18N.labelForKey(key);
    }

}
