package com.example.mvcrest.api.v1.mapper;

import com.example.mvcrest.api.v1.model.CategoryDTO;
import com.example.mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Joe");

        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("Joe", categoryDTO.getName());
    }
}