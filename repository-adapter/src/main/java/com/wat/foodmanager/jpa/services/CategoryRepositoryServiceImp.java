package com.wat.foodmanager.jpa.services;

import com.wat.foodmanager.jpa.repoport.CategoryRepositoryService;
import com.wat.foodmanager.jpa.repositories.CategoryRepository;
import com.wat.foodmanager.jpa.utils.Mapper;
import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Category;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class CategoryRepositoryServiceImp implements CategoryRepositoryService {

    private CategoryRepository categoryRepository;

    public CategoryRepositoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Async
    public void addCategory(Category category) {
        categoryRepository.save(Mapper.map(category));
    }

    @Override
    @Async
    public CompletableFuture<List<AbstractCategory>> listCategories() {
        return CompletableFuture.completedFuture(categoryRepository.findAll().stream()
                .map(Mapper::map)
                .collect(Collectors.toList()));
    }

    @Override
    @Async
    public CompletableFuture<AbstractCategory> getCategory(long id) {
        return CompletableFuture.completedFuture(Mapper.map(categoryRepository.getOne(id)));
    }

    @Override
    @Async("specificTaskExecutor")
    public CompletableFuture<AbstractCategory> getCategoryByName(String name) {
        return CompletableFuture.completedFuture(Mapper.map(categoryRepository.findByCategoryName(name)));
    }

    @Override
    @Async
    public void editCategory(Category category) {

    }

    @Override
    @Async
    public void addAllCategories(List<Category> categories) {
        categories.forEach(category -> categoryRepository.save(Mapper.map(category)));
    }
}
