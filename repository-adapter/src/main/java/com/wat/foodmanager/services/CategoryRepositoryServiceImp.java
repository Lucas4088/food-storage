package com.wat.foodmanager.services;


import com.wat.foodmanager.Mapper;
import com.wat.foodmanager.model.Category;
import com.wat.foodmanager.repoport.CategoryRepositoryService;
import com.wat.foodmanager.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryRepositoryServiceImp implements CategoryRepositoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.addCategory(Mapper.map(category));
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.listCategories().stream().map(Mapper::map).collect(Collectors.toList());
    }
}
