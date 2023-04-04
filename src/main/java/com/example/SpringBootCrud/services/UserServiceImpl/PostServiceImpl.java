package com.example.SpringBootCrud.services.UserServiceImpl;
import com.example.SpringBootCrud.entity.Category;
import com.example.SpringBootCrud.entity.Post;
import com.example.SpringBootCrud.entity.User;
import com.example.SpringBootCrud.exceptions.ResourceNotFoundException;
import com.example.SpringBootCrud.models.PostDto;
import com.example.SpringBootCrud.models.PostResponse;
import com.example.SpringBootCrud.repository.CategoryRepo;
import com.example.SpringBootCrud.repository.PostRepo;
import com.example.SpringBootCrud.repository.UserRepo;
import com.example.SpringBootCrud.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User ","user id ",userId));
        Category category=this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ","category id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setCategory(category);
        post.setImage("default.png");
        post.setUser(user);
        post.setAddDate(new Date());
        Post createPost = this.postRepo.save(post);
        return modelMapper.map(createPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(Integer postId, PostDto postDto) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post "," post id ",postId));
        post.setContent(postDto.getContent());
        post.setPostTitle(postDto.getPostTitle());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post ","post id ",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post "," post id ",postId));
        return this.modelMapper.map(post,PostDto.class);
    }
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        List<Post> all = this.postRepo.findAll();
        Page<Post> postPage = this.postRepo.findAll(PageRequest.of(pageNumber,pageSize));
        List<Post> postList = postPage.getContent();
        List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setLastPage(postPage.isLast());
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setTotalPage(postPage.getTotalPages());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElement(postPage.getTotalElements());
        return postResponse;
    }
    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User ","user id ",userId));
        List<Post> postList = this.postRepo.findByUser(user);
        List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category ","category id ",categoryId));
        List<Post> postList = this.postRepo.findByCategory(category);
        List<PostDto> postDtoList = postList.stream().map(post -> modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}