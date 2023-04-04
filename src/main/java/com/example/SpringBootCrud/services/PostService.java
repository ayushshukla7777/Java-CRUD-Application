package com.example.SpringBootCrud.services;

import com.example.SpringBootCrud.entity.User;
import com.example.SpringBootCrud.models.PostDto;
import com.example.SpringBootCrud.models.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(Integer postId,PostDto postDto);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);
    List<PostDto> getAllPostByUser(Integer userId);
    List<PostDto> getAllPostByCategory(Integer categoryId);
}
