package com.shahian.msproduct.controller;

import com.shahian.msproduct.model.Category;
import com.shahian.msproduct.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/v1/categories")
    public List<Category> getAllCategories(@RequestParam(required = false) Long warehouseId) {
        if (warehouseId != null) {
            return categoryService.getCategoryByWarehouseId(warehouseId);
        } else {
            return categoryService.findAll();
        }
    }
    @GetMapping("/v1/category")
    public Optional<Category> getAllCategory(@RequestParam  Long id) {
            return categoryService.findById(id);
    }

    @PostMapping("/v1/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }
    @PutMapping("/v1/category")
    public Category updateCategory(@RequestParam Long id,@RequestBody Category category) {
        return categoryService.update(id,category);
    }
    @DeleteMapping("/v1/category/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}