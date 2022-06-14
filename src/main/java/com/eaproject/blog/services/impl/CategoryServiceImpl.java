package com.eaproject.blog.services.impl;

import com.eaproject.blog.entities.Category;
import com.eaproject.blog.exceptions.ResourceNotFoundException;
import com.eaproject.blog.payloads.CategoryDto;
import com.eaproject.blog.repositories.CategoryRepo;
import com.eaproject.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper; // to convert DTO to category and vice versa

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(cat);

        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category givenCategory = this.categoryRepo
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        givenCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        givenCategory.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = this.categoryRepo.save(givenCategory);

        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        return null;
    }
}
