package com.example.SpringBootCrud.services;

import com.example.SpringBootCrud.models.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Integer userId,UserDto userDto);
    void deleteUser(Integer userId);
    List<UserDto> getAllUser();
    UserDto getUserById(Integer userId);
}
