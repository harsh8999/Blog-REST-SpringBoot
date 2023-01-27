package com.personal.blogappapi.services;

import java.util.List;

import com.personal.blogappapi.payloads.UserDto;

public interface UserService {
    
    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getUsers();
    void deleteUser(Integer userId);
}
