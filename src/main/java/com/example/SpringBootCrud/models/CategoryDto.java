package com.example.SpringBootCrud.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "category title is required")
    private String categoryTitle;
    @NotEmpty(message = "category description is required")
    private String categoryDescription;
}
