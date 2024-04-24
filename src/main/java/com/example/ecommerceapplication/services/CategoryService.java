package com.example.ecommerceapplication.services;

import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.dto.CategoryResponse;
import com.example.ecommerceapplication.entities.Category;

public interface CategoryService {

    CategoryDTO createCategory(Category category);

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}
