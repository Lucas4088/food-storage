package com.foodmanager.guiport;


import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.ElementAlreadyExistsException;

import java.util.List;

public interface CategoryFrontendService {
    AbstractCategory getCategory(long id);

    List<AbstractCategory> listCategories();

    void addNewCategory(String name) throws ElementAlreadyExistsException;

    AbstractCategory getCategoryByName(String name);

    void deleteCategory(String name);

    AbstractCategory updateCategory(String name);
}
