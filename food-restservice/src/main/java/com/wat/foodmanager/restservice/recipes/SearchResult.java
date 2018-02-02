package com.wat.foodmanager.restservice.recipes;

import com.owlike.genson.annotation.JsonProperty;

public class SearchResult {
    private int _count;
    private RecipeFood2Fork[] _recipes;

    public SearchResult(
            @JsonProperty("count") int count,
            @JsonProperty("recipes") RecipeFood2Fork[] recipes
    ) {
        _count = count;
        _recipes = recipes;
    }

    @JsonProperty("count")
    public int getCount() {
        return _count;
    }

    @JsonProperty("recipes")
    public RecipeFood2Fork[] getRecipes() {
        return _recipes;
    }
}
