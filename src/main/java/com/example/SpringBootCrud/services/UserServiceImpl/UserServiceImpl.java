package com.example.SpringBootCrud.services.UserServiceImpl;

import com.example.SpringBootCrud.entity.User;
import com.example.SpringBootCrud.exceptions.ResourceNotFoundException;
import com.example.SpringBootCrud.models.UserDto;
import com.example.SpringBootCrud.repository.UserRepo;
import com.example.SpringBootCrud.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        User createdUser = this.userRepo.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(Integer userId,UserDto userDto) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User ","user id ",userId));
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        User updatedUser = this.userRepo.save(user);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
        this.userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = this.userRepo.findAll();
        List<UserDto> userDtoList = userList.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
        return modelMapper.map(user,UserDto.class);
    }
}
