package com.wat.foodmanager;

import com.foodmanager.guiport.RecipesFrontendService;
import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import com.wat.foodmanager.model.Recipe;
import com.wat.foodmanager.restapi.recipes.RecipesFood2ForkService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeServiceCore implements RecipesFrontendService {

    private RecipesFood2ForkService recipesFood2ForkService;
    private static final Logger logger = Logger.getLogger(RecipeServiceCore.class);

    public RecipeServiceCore(RecipesFood2ForkService recipesFood2ForkService) {
        this.recipesFood2ForkService = recipesFood2ForkService;
    }

    @Override
    public Recipe findRecipeById(String id) throws UnableToFindRecipesListException {
        try {
            return recipesFood2ForkService.searchForRecipe(id);
        } catch (UnableToFindRecipesListException unableToFindRecipesListException) {
            logger.warn("Unable to find Recipes List");
            throw new UnableToFindRecipesListException();
        }
    }

    @Override
    public List<Recipe> listRecipesBySearchParameter(String parameter) throws UnableToLoadRecipeException {
        try {
            return recipesFood2ForkService.getSearchRecipesResult(parameter);
        } catch (UnableToLoadRecipeException unableToLoadRecipe) {
            logger.warn("Unable to load Recipe");
            throw new UnableToLoadRecipeException();
        }
    }
}