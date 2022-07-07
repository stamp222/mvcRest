package com.example.mvcrest.api.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {
    List<CategoryDTO> categoryList = new ArrayList<>();
}
