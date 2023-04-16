package com.shahian.msproduct.service;

import com.shahian.msproduct.model.Category;
import com.shahian.msproduct.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    public Category update(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Category not found with id: " + id));
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        categoryRepository.save(category);
        return category;
    }
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
    public List<Category> getCategoryByWarehouseId(Long warehouseId) {
        return categoryRepository.findByWarehouseId(warehouseId);
    }
}
