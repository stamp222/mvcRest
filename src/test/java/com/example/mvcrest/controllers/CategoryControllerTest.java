package com.example.mvcrest.controllers;

import com.example.mvcrest.api.v1.model.CategoryDTO;
import com.example.mvcrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void testListCategories() throws Exception {
        CategoryDTO cat1 = new CategoryDTO();
        cat1.setId(1L);
        cat1.setName("Jim");
        CategoryDTO cat2 = new CategoryDTO();
        cat2.setId(2L);
        cat2.setName("Bob");
        List<CategoryDTO> categories = Arrays.asList(cat1, cat2);
        when(categoryService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/api/v1/categories/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryList", hasSize(2)));
    }

    @Test
    void getByNameCategoryTest() throws Exception {
        CategoryDTO cat1 = new CategoryDTO();
        cat1.setId(1L);
        cat1.setName("Jim");
        when(categoryService.getCategoryByName(anyString())).thenReturn(cat1);
        mockMvc.perform(get("/api/v1/categories/Jim")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Jim")));

    }
}