package com.wat.foodmanager;

import com.wat.foodmanager.entities.CategoryEntity;
import com.wat.foodmanager.model.Category;

public class Mapper {

    public static CategoryEntity map(Category category) {
        return new CategoryEntity(category.getName());
    }

    public static Category map(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getName());
    }
}
