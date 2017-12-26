package com.wat.foodmanager.dao;

import com.wat.foodmanager.entities.CategoryEntity;

import java.util.List;

public interface CategoryDao {
    void addCategory(CategoryEntity category);
    List<CategoryEntity> listCategories();

}
