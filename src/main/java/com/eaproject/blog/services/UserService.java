package com.eaproject.blog.services;

import com.eaproject.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    // we don't want to give our entity directly to the service so we create payload of user
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
