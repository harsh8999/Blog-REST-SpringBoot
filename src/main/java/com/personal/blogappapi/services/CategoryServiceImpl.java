package com.personal.blogappapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.blogappapi.entities.Category;
import com.personal.blogappapi.exceptions.ResourceNotFoundException;
import com.personal.blogappapi.payloads.CategoryDto;
import com.personal.blogappapi.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category createdCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(createdCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                                                .orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        
        Category updatedCat = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        
        Category category = this.categoryRepo.findById(categoryId)
                                                .orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                                                .orElseThrow(() -> new ResourceNotFoundException("Categoty", "Category Id", categoryId));
        
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        return categories.stream()
                            .map(category-> this.modelMapper.map(category, CategoryDto.class))
                            .collect(Collectors.toList());
    }
}
