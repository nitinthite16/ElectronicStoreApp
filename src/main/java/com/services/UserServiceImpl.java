package com.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtos.UserDto;
import com.entities.User;
import com.exceptions.ResourceNotFoundException;
import com.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
  
		userDto.setPassword(userDto.getPassword());
		User user = dtoToEntity(userDto);
		User savedUser = repo.save(user);   
		UserDto newDto = entityToDto(savedUser);
		return newDto;

	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());

		if (!userDto.getPassword().equalsIgnoreCase(user.getPassword())) {
			user.setPassword(userDto.getPassword());
		}
		User updatedUser = repo.save(user);
		UserDto updatedDto = entityToDto(updatedUser);

		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		User deletedUser = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		repo.delete(deletedUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repo.findAll();
		List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

		return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		UserDto userDto = entityToDto(user);
		return userDto;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = repo.findByEmailId(email);
		UserDto userDto = entityToDto(user);
		return userDto;
	}

//dto to entity
	User dtoToEntity(UserDto userDto) {
		User user=mapper.map(userDto, User.class);
		return user;
		
	}
//entity to dto
	UserDto entityToDto(User user) {
		UserDto userDto=mapper.map(user, UserDto.class);
		return userDto;
		
	}	

}
