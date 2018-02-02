package com.foodmanager.guiport;

import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import com.wat.foodmanager.model.Recipe;

import java.util.List;

public interface RecipesFrontendService {
    Recipe findRecipeById(String id) throws UnableToFindRecipesListException;

    List<Recipe> listRecipesBySearchParameter(String parameter) throws UnableToLoadRecipeException;
}
