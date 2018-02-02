package gui.model.events;

import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import gui.controllers.RecipesController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SearchForRecipesEvent implements EventHandler<ActionEvent> {

    private ListView recipesListView;
    private RecipesController recipesController;
    private TextField searchRecipeField;

    public SearchForRecipesEvent(ListView recipesListView, RecipesController recipesController, TextField searchRecipeField) {
        this.recipesListView = recipesListView;
        this.recipesController = recipesController;
        this.searchRecipeField = searchRecipeField;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            recipesListView.setItems(FXCollections.observableArrayList(
                    new ArrayList<>(recipesController.searchRecipes(searchRecipeField.getText()))));
        } catch (UnableToLoadRecipeException unableToLoadRecipe) {
            new Alert(Alert.AlertType.WARNING, "Unable to load recipe");
        }
    }
}
