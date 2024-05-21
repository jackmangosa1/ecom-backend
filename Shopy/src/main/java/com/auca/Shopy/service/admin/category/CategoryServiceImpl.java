package com.auca.Shopy.service.admin.category;

import com.auca.Shopy.dto.CategoryDTO;
import com.auca.Shopy.model.Category;
import com.auca.Shopy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService{
    private final CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {

        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " does not exist.");
        }
    }

    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " does not exist."));


        category.setName(categoryDTO.getName());



        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
