package com.project.maribeauty.services;

import com.project.maribeauty.dto.UserDto;
import com.project.maribeauty.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
