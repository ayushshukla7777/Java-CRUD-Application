package com.example.SpringBootCrud.repository;

import com.example.SpringBootCrud.entity.Category;
import com.example.SpringBootCrud.entity.Post;
import com.example.SpringBootCrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
