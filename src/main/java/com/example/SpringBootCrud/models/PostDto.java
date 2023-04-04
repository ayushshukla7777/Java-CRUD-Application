package com.example.SpringBootCrud.models;

import com.example.SpringBootCrud.entity.Category;
import com.example.SpringBootCrud.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    @NotEmpty(message = "Post title is required")
    private String postTitle;
    @NotEmpty(message = "Content is required ")
    @Size(max = 10000,min = 10,message = "Content min 10 Character max 10000")
    private String content;
    private String image;
    private Date addDate;
    private Category category;
    private User user;
}
