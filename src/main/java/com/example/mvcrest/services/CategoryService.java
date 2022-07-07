package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String categoryName);
}
