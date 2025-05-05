package com.kyler.user_manage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kyler.user_manage.entity.User;
import com.kyler.user_manage.repository.UserRepository;
import com.kyler.user_manage.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock // fake
	private UserRepository userRepository;

	@InjectMocks // Add UserRepository(fake) to UserService(real)
	private UserServiceImpl userService;

	@Test
	public void createUser_shouldReturnUser_WhenEmailValid() {
		// arrange: chuẩn bị
		User inputUser = new User(null, "Kyler", "hailamtranvan@gmail.com");
		User outputUser = new User(1L, "Kyler", "hailamtranvan@gmail.com");
		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);
		when(this.userRepository.save(any())).thenReturn(outputUser);

		// act: hành động
		User resultUser = this.userService.createUser(inputUser);

		// assert: so sánh
		assertEquals(1L, resultUser.getId());
	}

	@Test
	public void createUser_shouldThrowException_WhenEmailInvalid() {
		// arrange: chuẩn bị
		User inputUser = new User(null, "Kyler", "hailamtranvan@gmail.com");
		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

		// act: hành động
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			this.userService.createUser(inputUser);
		});

		// assert: so sánh
		assertEquals("Email already exists", exception.getMessage());
	}

	@Test
	public void getAllUsers_shouldReturnAllUsers() {
		// arrange: chuẩn bị
		List<User> outputUsers = new ArrayList<User>();
		outputUsers.add(new User(1L, "Kyler", "hailamtranvan@gmail.com"));
		outputUsers.add(new User(2L, "Harry", "harry@gmail.com"));
		when(this.userRepository.findAll()).thenReturn(outputUsers);

		// act: hành động
		List<User> resultUsers = this.userService.getAllUsers();

		// assert: so sánh
		assertEquals(2, resultUsers.size());
		assertEquals("Kyler", resultUsers.get(0).getName());
		assertEquals("Harry", resultUsers.get(1).getName());
		assertEquals("hailamtranvan@gmail.com", resultUsers.get(0).getEmail());
		assertEquals("harry@gmail.com", resultUsers.get(1).getEmail());
	}

//	@Test
//	public void getUserById_shouldReturnOptionalUser() {
//		// arrange: chuẩn bị
//		Long inputId = 1L;
//		Optional<User> outputOptionalUser = Optional.of(new User(1L, "Kyler", "hailamtranvan@gmail.com"));
//		when(this.userRepository.findById(inputId)).thenReturn(outputOptionalUser);
//
//		// act: hành động
//		Optional<User> resultOptionalUser = this.userService.getUserById(inputId);
//
//		// assert: so sánh
//		assertEquals(true, resultOptionalUser.isPresent());
//	}
//
//	@Test
//	public void deleteUser_shouldReturnVoid_WhenUserExists() {
//		// arrange: chuẩn bị
//		Long inputId = 1L;
//		when(this.userRepository.existsById(inputId)).thenReturn(true);
//
//		// act: hành động
//		this.userService.deleteUser(inputId);
//
//		// assert: so sánh
//		verify(this.userRepository).deleteById(inputId);
//	}
//
//	@Test
//	public void deleteUser_shouldThrowException_WhenUserNotExists() {
//		// arrange: chuẩn bị
//		Long inputId = 1L;
//
//		// act: hành động
//		Exception exception = assertThrows(NoSuchElementException.class, () -> {
//			this.userService.deleteUser(inputId);
//		});
//
//		// assert: so sánh
//		assertEquals("User not found", exception.getMessage());
//	}
//
//	@Test
//	public void updateUser_shouldReturnUser_WhenUserExists() {
//		// arrange: chuẩn bị
//		Long inputId = 1L;
//		User inputUser = new User(1L, "old name", "old@gmail.com");
//		User outputUser = new User(1L, "new name", "new@gmail.com");
//		when(this.userRepository.findById(inputId)).thenReturn(Optional.of(inputUser));
//		when(this.userRepository.save(any())).thenReturn(outputUser);
//
//		// act: hành động
//		User resultUser = this.userService.updateUser(inputId, inputUser);
//
//		// assert: so sánh
//		assertEquals("new name", resultUser.getName());
//		assertEquals("new@gmail.com", resultUser.getEmail());
//	}
//
//	@Test
//	public void updateUser_shouldThrowException_WhenUserNotExists() {
//		// arrange: chuẩn bị
//		Long inputId = 1L;
//		when(this.userRepository.findById(inputId)).thenReturn(Optional.empty());
//
//		// act: hành động
//		Exception exception = assertThrows(NoSuchElementException.class, () -> {
//			this.userService.updateUser(inputId, any());
//		});
//
//		// assert: so sánh
//		assertEquals("User not found", exception.getMessage());
//	}

}
