package gui.controllers;

import com.foodmanager.guiport.RecipesFrontendService;
import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import com.wat.foodmanager.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipesController {
    private RecipesFrontendService recipesFrontendService;

    public RecipesController(RecipesFrontendService recipesFrontendService) {
        this.recipesFrontendService = recipesFrontendService;
    }

    public Recipe getRecipe(String id) throws UnableToFindRecipesListException {
        return recipesFrontendService.findRecipeById(id);
    }

    public List<Recipe> searchRecipes(String parameter) throws UnableToLoadRecipeException {
        return recipesFrontendService.listRecipesBySearchParameter(parameter);
    }
}
