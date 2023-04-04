package com.example.SpringBootCrud.services;

import com.example.SpringBootCrud.models.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
    void deleteCategory(Integer categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Integer categoryId);
}
