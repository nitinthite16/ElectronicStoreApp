package com.services;

import java.util.List;

import com.dtos.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, String userId);

	void deleteUser(String userId);

	List<UserDto> getAllUsers();

	UserDto getUserById(String userId);

	UserDto getUserByEmail(String email);
}
