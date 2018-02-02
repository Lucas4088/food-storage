package gui.dialogs;

import com.wat.foodmanager.model.Recipe;
import gui.utils.I18N;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Arrays;


public class RecipeDialog extends Dialog<Pair<String, String>> {

    private TextField title;
    private ImageView image;
    private TextArea ingredients;
    private Recipe recipe;

    public RecipeDialog(Recipe recipe) {
        this.recipe = recipe;
        this.title = new TextField(recipe.getTitle());
        this.image = new ImageView(recipe.getImageUrl());
        this.ingredients = new TextArea();

        this.ingredients.setWrapText(true);
        Arrays.asList(recipe.getIngredients()).forEach(text ->
                this.ingredients.appendText(text.concat("\n"))
        );
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

        title.setEditable(false);
        ingredients.setEditable(false);

        image.setFitHeight(200);
        image.setFitWidth(200);
        grid.add(title, 0, 0);
        grid.add(image, 0, 1);
        grid.add(ingredients, 0, 2);
        titleProperty().bind(I18N.createStringBinding("recipeDialog.title"));
    }

    public void validateInput() {

    }
}