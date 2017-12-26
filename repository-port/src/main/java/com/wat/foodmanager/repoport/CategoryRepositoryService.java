package com.wat.foodmanager.repoport;

import com.wat.foodmanager.model.Category;

import java.util.List;

public interface CategoryRepositoryService {

    void addCategory(Category category);
    List<Category> listCategories();
}
