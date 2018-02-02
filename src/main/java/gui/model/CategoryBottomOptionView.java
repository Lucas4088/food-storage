package gui.model;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.ElementAlreadyExistsException;
import gui.FrontendController;
import gui.controllers.CategoryController;
import gui.dialogs.CategoryDialog;
import gui.model.exceptions.InvalidInputException;
import gui.utils.InputValidator;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.util.stream.Collectors;

public class CategoryBottomOptionView extends BottomOptionView {

    private CategoryDialog categoryDialog;
    private CategoryController categoryController;
    private ListView categoriesListView;

    public CategoryBottomOptionView(Button customButton,
                                    FrontendController frontendController,
                                    BorderPane root,
                                    CategoryDialog categoryDialog,
                                    CategoryController categoryController,
                                    ListView categoriesListView) {
        super(customButton, frontendController, root);
        this.categoryDialog = categoryDialog;
        this.categoryController = categoryController;
        this.categoriesListView = categoriesListView;
    }

    @Override
    protected void setAction() {
        this.customButton.setOnAction(event -> openAddCategoryDialog());
    }

    private void openAddCategoryDialog() {
        Button addCategoryButton = frontendController.getAddCategoryDialogButton();
        categoryDialog = new CategoryDialog(new TextField(), addCategoryButton, Action.ADD);
        frontendController.setCategoryDialog(categoryDialog);

        addCategoryButton.setOnAction(
                event -> addCategory()
        );
        categoryDialog.getDialogPane().addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                addCategory();
            }
        });
        categoryDialog.show();
    }

    private void addCategory() {

        try {
            if (InputValidator.isEmpty(categoryDialog.getNameField().getText())) {
                throw new InvalidInputException();
            }

            categoryController.addCategory(categoryDialog.getNameField().getText());
            categoryDialog.close();
            categoriesListView.setItems(FXCollections.observableArrayList(categoryController.listCategories()
                    .stream()
                    .map(AbstractCategory::getName)
                    .collect(Collectors.toList())));
        } catch (InvalidInputException e) {
            (new Alert(Alert.AlertType.WARNING, "Invalid Input")).show();
        } catch (ElementAlreadyExistsException e) {
            new Alert(Alert.AlertType.WARNING, "Element Already Exists");
        }
    }
}
