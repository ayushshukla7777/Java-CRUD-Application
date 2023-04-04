package com.example.SpringBootCrud.controllers;

import com.example.SpringBootCrud.models.ApiResponse;
import com.example.SpringBootCrud.models.UserDto;
import com.example.SpringBootCrud.repository.UserRepo;
import com.example.SpringBootCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PutMapping("/updatedUser/{userId}")
    public ResponseEntity<UserDto> userUpdated(@PathVariable(name = "userId") Integer userId,@Valid @RequestBody UserDto userDto){
        UserDto updatedUser = this.userService.updateUser(userId,userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("Deleted success",true),HttpStatus.OK);
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtoList = this.userService.getAllUser();
        return new ResponseEntity<>(userDtoList,HttpStatus.OK);
    }
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Integer userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
