package com.example.SpringBootCrud.services.UserServiceImpl;

import com.example.SpringBootCrud.entity.Category;
import com.example.SpringBootCrud.exceptions.ResourceNotFoundException;
import com.example.SpringBootCrud.models.CategoryDto;
import com.example.SpringBootCrud.models.UserDto;
import com.example.SpringBootCrud.repository.CategoryRepo;
import com.example.SpringBootCrud.services.CategoryService;
import com.example.SpringBootCrud.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category createdCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(createdCategory,CategoryDto.class);
    }
    @Override
    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ","category id ",categoryId));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        Category updatedCategory = this.categoryRepo.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }
    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ","category id ",categoryId));
        this.categoryRepo.delete(category);
    }
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ","category id ",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }
}
