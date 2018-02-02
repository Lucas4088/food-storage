package com.wat.foodmanager.restapi.recipes;

import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import com.wat.foodmanager.model.Recipe;

import java.util.List;

public interface RecipesFood2ForkService {
    Recipe searchForRecipe(String reipeId) throws UnableToFindRecipesListException;

    List<Recipe> getSearchRecipesResult(String parameter) throws UnableToLoadRecipeException;

}
