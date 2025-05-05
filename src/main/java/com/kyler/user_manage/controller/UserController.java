package com.kyler.user_manage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyler.user_manage.entity.ApiResponse;
import com.kyler.user_manage.entity.User;
import com.kyler.user_manage.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/users")
	public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody User user) {
		User created = userService.createUser(user);
		ApiResponse<User> res = new ApiResponse<User>(HttpStatus.CREATED, "create user", created, null);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
		ApiResponse<List<User>> res = new ApiResponse<List<User>>(HttpStatus.OK, "get all user",
				userService.getAllUsers(), null);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
		return userService.getUserById(id).map(user -> {
			ApiResponse<User> res = new ApiResponse<User>(HttpStatus.CREATED, "get user by id", user, null);
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}).orElseGet(() -> {
			ApiResponse<User> errorRes = new ApiResponse<>(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng", null,
					"NOT_FOUND");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorRes);
		});
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
		User updated = userService.updateUser(id, user);
		ApiResponse<User> res = new ApiResponse<User>(HttpStatus.OK, "update user", updated, null);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		ApiResponse<User> res = new ApiResponse<User>(HttpStatus.OK, "delete user", null, null);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}