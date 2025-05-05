package com.kyler.user_manage.service;

import java.util.List;
import java.util.Optional;

import com.kyler.user_manage.entity.User;

public interface UserService {

	User createUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserById(Long id);

	public User updateUser(Long id, User updatedUser);

	void deleteUser(Long id);
}
