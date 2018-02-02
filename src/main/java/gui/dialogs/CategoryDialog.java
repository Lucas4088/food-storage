package gui.dialogs;

import gui.model.Action;
import gui.utils.I18N;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class CategoryDialog extends Dialog<Pair<String, String>> {
    private TextField nameField;
    private Button addCategoryButton;
    private Action action;

    public CategoryDialog(TextField nameField, Button addCategoryButton, Action action) {
        this.nameField = nameField;
        this.addCategoryButton = addCategoryButton;
        this.action = action;

        if (action == Action.PREVIEW)
            nameField.setEditable(false);

        initializeView();
    }

    private void initializeView() {
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(6);
        grid.setPadding(new Insets(20, 20, 1, 20));
        getDialogPane().setContent(grid);

        Label category = I18N.labelForKey("category.product");
        TextField categoryField = nameField;

        grid.add(category, 0, 0);
        grid.add(categoryField, 1, 0, 2, 1);

        grid.add(addCategoryButton, 2, 1);
        titleProperty().bind(I18N.createStringBinding("addCategoryDialog.title"));
    }

    public TextField getNameField() {
        return nameField;
    }

}
