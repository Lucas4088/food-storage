package com.wat.foodmanager;

import com.wat.foodmanager.model.Category;
import com.wat.foodmanager.repoport.CategoryRepositoryService;
import org.springframework.stereotype.Component;

@Component
public class CategoryService {

    public void add() {
        CategoryRepositoryService categoryRepositoryService = null;

        categoryRepositoryService.addCategory(new Category("shit"));
    }
}
