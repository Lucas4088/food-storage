package com.wat.foodmanager;

import com.foodmanager.guiport.CategoryFrontendService;
import com.wat.foodmanager.jpa.repoport.CategoryRepositoryService;
import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Category;
import com.wat.foodmanager.model.Exceptions.ElementAlreadyExistsException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceCore implements CategoryFrontendService {

    private static final Logger logger = Logger.getLogger(CategoryServiceCore.class);

    private CategoryRepositoryService categoryRepositoryService;

    public CategoryServiceCore(CategoryRepositoryService categoryRepositoryService) {
        this.categoryRepositoryService = categoryRepositoryService;

    }

    @Override
    public AbstractCategory getCategory(long id) {
        return categoryRepositoryService.getCategory(id).join();
    }

    @Override
    public List<AbstractCategory> listCategories() {
        return categoryRepositoryService.listCategories().join();
    }

    @Override
    public void addNewCategory(String name) throws ElementAlreadyExistsException {
        if (categoryRepositoryService.getCategoryByName(name).join().toString() != "NULL CATEGORY")
            throw new ElementAlreadyExistsException();

        categoryRepositoryService.addCategory(new Category(name));
    }

    @Override
    public AbstractCategory getCategoryByName(String name) {
        return categoryRepositoryService.getCategoryByName(name).join();
    }

    @Override
    public void deleteCategory(String name) {

    }

    @Override
    public Category updateCategory(String name) {
        return null;
    }
}

