package com.example.SpringBootCrud.controllers;

import com.example.SpringBootCrud.models.ApiResponse;
import com.example.SpringBootCrud.models.PostDto;
import com.example.SpringBootCrud.models.PostResponse;
import com.example.SpringBootCrud.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/createPost/userId/{userId}/categoryId/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                           @PathVariable(name = "userId")Integer userId,
                                           @PathVariable(name = "categoryId") Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable(name = "postId")Integer postId){
        PostDto updatedPost = this.postService.updatePost(postId,postDto);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "postId") Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Success deleted post",true),HttpStatus.OK);
    }
    @GetMapping("/getPostById/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "postId") Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/getAllPost")
    public  ResponseEntity<PostResponse> getAllPost(
            @RequestParam(name = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = "10",required = false) Integer pageSize
    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/getAllPostByUserId/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable(name = "userId") Integer userId){
        List<PostDto> postDtoList = this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/getAllPostByCategoryId/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable(name = "categoryId") Integer categoryId){
        List<PostDto> postDtoList = this.postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}