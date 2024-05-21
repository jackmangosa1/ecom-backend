package com.auca.Shopy.service.admin.category;

import com.auca.Shopy.dto.CategoryDTO;
import com.auca.Shopy.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long categoryId);
    Category updateCategory(Long categoryId, CategoryDTO categoryDTO);
    List<Category> getAllCategories();

}
