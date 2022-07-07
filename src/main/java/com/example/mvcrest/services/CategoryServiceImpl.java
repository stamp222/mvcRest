package com.example.mvcrest.services;

import com.example.mvcrest.api.v1.mapper.CategoryMapper;
import com.example.mvcrest.api.v1.model.CategoryDTO;
import com.example.mvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String categoryName) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(categoryName));
    }
}
