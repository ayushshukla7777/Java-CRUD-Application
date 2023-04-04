package com.example.SpringBootCrud.repository;

import com.example.SpringBootCrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
