package com.wat.foodmanager.jpa.repoport;

import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Category;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CategoryRepositoryService {

    void addCategory(Category category);

    CompletableFuture<List<AbstractCategory>> listCategories();

    CompletableFuture<AbstractCategory> getCategory(long id);

    CompletableFuture<AbstractCategory> getCategoryByName(String name);

    void editCategory(Category category);

    void addAllCategories(List<Category> categories);
}
