package com.example.SpringBootCrud.controllers;

import com.example.SpringBootCrud.models.ApiResponse;
import com.example.SpringBootCrud.models.CategoryDto;
import com.example.SpringBootCrud.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    @PutMapping("/updatedCategory/{categoryId}")
    public ResponseEntity<CategoryDto> userUpdated(@PathVariable(name = "categoryId") Integer categoryId,
                                                   @Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryId,categoryDto);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "categoryId") Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Deleted success",true),HttpStatus.OK);
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDto>> getAllUser(){
        List<CategoryDto> categoryDtos = this.categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }
    @GetMapping("/getByCategoryId/{categoryId}")
    public ResponseEntity<CategoryDto> getUserById(@PathVariable(name = "categoryId") Integer categoryId){
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
}