package com.eaproject.blog.services;

import com.eaproject.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    // create
    public CategoryDto createCategory(CategoryDto categoryDto);

    // update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // get all
    List<CategoryDto> getCategories();
}
