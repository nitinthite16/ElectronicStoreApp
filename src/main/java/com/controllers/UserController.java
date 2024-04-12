package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtos.UserDto;
import com.services.FileService;
import com.services.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private FileService fileService;
	@Value("${user.profile.image.path}")
	private String imageUploadPath;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUser = userServiceImpl.createUser(userDto);
		ResponseEntity<UserDto> newUserCreated = new ResponseEntity<>(createdUser, HttpStatus.CREATED);
		return newUserCreated;

	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
		UserDto updatedUser = userServiceImpl.updateUser(userDto, userId);
		ResponseEntity<UserDto> newUpdatedUser = new ResponseEntity<>(updatedUser, HttpStatus.OK);
		return newUpdatedUser;

	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable String userId) {
		userServiceImpl.deleteUser(userId);

	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> allUsers = userServiceImpl.getAllUsers();
		ResponseEntity<List<UserDto>> allUserResponse = new ResponseEntity<>(allUsers, HttpStatus.OK);
		return allUserResponse;

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
		UserDto getUserDto = userServiceImpl.getUserById(userId);
		ResponseEntity<UserDto> singleUserResponse = new ResponseEntity<>(getUserDto, HttpStatus.OK);
		return singleUserResponse;

	}

	@GetMapping("email/{email}")
	public ResponseEntity<UserDto> getByEmailId(@PathVariable String email) {
		UserDto getuserByEmailId = userServiceImpl.getUserByEmail(email);
		ResponseEntity<UserDto> getUserByEmailId = new ResponseEntity<UserDto>(getuserByEmailId, HttpStatus.OK);
		return getUserByEmailId;

	}

	/*
	 * @PostMapping("/image/{userId}") public ResponseEntity<ImageResponse>
	 * uploadUserImage(@RequestParam("userImage") MultipartFile image, @PathVariable
	 * String userId) throws IOException { String imageName =
	 * fileService.uploadFile(image, imageUploadPath); UserDto user =
	 * userServiceImpl.getUserById(userId); user.setImageName(imageName); UserDto
	 * userDto = userServiceImpl.updateUser(user, userId); ImageResponse
	 * imageResponse = ImageResponse.builder().imageName(imageName).success(true).
	 * message("image is uploaded successfully ").status(HttpStatus.CREATED).build()
	 * ; return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
	 * 
	 * }
	 */
}
