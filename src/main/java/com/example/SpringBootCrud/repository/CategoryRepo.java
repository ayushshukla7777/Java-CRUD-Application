package com.example.SpringBootCrud.repository;

import com.example.SpringBootCrud.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
