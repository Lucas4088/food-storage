package gui.controllers;

import com.foodmanager.guiport.CategoryFrontendService;
import com.wat.foodmanager.model.AbstractCategory;
import com.wat.foodmanager.model.Exceptions.ElementAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryController {

    private CategoryFrontendService categoryFrontendService;

    public CategoryController(CategoryFrontendService categoryFrontendService) {
        this.categoryFrontendService = categoryFrontendService;
    }


    public AbstractCategory getCategory(long id) {
        return categoryFrontendService.getCategory(id);
    }

    public AbstractCategory getCategoryByName(String name) {
        return categoryFrontendService.getCategoryByName(name);
    }

    public List<AbstractCategory> listCategories() {
        return categoryFrontendService.listCategories();
    }

    public void addCategory(String name) throws ElementAlreadyExistsException {
        categoryFrontendService.addNewCategory(name);
    }

    public AbstractCategory updateCategory(String name) {
        return null;
    }
}
