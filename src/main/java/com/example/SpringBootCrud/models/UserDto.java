package com.example.SpringBootCrud.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    @NotEmpty(message = "First name is required")
    @Size(max = 8,message = " First name should be less then 8 character")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Size(max = 8,message = "Last name should be less then 8 character")
    private String lastName;
    @Email(message = "email is not valid")
    @NotEmpty(message = "email is required")
    private String email;
    @Size(min = 10,max = 10,message = "Number should be 10 Character")
    private String phoneNumber;
    private String about;
    @Size(max = 25,min = 8,message = "Password character length should be in range 8 and 25")
    @NotEmpty
    private String password;
}
