package com.wat.foodmanager.restservice.recipes;

import com.wat.foodmanager.model.Exceptions.UnableToFindRecipesListException;
import com.wat.foodmanager.model.Exceptions.UnableToLoadRecipeException;
import com.wat.foodmanager.model.Recipe;
import com.wat.foodmanager.restapi.recipes.RecipesFood2ForkService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipesFood2ForkImp implements RecipesFood2ForkService {

    private Food2Fork food2Fork;

    private static final Logger logger = org.apache.log4j.Logger.getLogger(RecipesFood2ForkImp.class);

    public RecipesFood2ForkImp(Food2Fork food2Fork) {
        this.food2Fork = food2Fork;
    }

    @Override
    public Recipe searchForRecipe(String reipeId) throws UnableToFindRecipesListException {
        try {
            return map(food2Fork.getRecipe(reipeId));
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new UnableToFindRecipesListException();
        }
    }

    @Override
    public List<Recipe> getSearchRecipesResult(String parameter) throws UnableToLoadRecipeException {
        try {
            return Arrays.stream(food2Fork.search(parameter, 'r', 3).getRecipes())
                    .map(this::map)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UnableToLoadRecipeException();
        }
    }


    private Recipe map(RecipeFood2Fork recipeFood2Fork) {
        return new Recipe(recipeFood2Fork.getId(),
                recipeFood2Fork.getImageUrl(),
                recipeFood2Fork.getSourceUrl(),
                recipeFood2Fork.getF2fUrl(),
                recipeFood2Fork.getTitle(),
                recipeFood2Fork.getPublisher(),
                recipeFood2Fork.getPublisherUrl(),
                recipeFood2Fork.getSocialRank(),
                recipeFood2Fork.getPage(),
                recipeFood2Fork.getIngredients());
    }
}
