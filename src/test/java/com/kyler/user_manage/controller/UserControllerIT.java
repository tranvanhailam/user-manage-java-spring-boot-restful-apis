package com.kyler.user_manage.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyler.user_manage.IntegrationTest;
import com.kyler.user_manage.entity.ApiResponse;
import com.kyler.user_manage.entity.User;
import com.kyler.user_manage.repository.UserRepository;

//@IntegrationTest
//@SpringBootTest
//@ActiveProfiles("test")
//@AutoConfigureMockMvc
//@Transactional // import org.springframework.transaction.annotation.Transactional;
//public class UserControllerIT {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@BeforeEach
//	public void init() {
//		this.userRepository.deleteAll();
//	}
//
//	@Test
//	public void createUser_shouldReturnUser_whenValid() throws Exception {
//		// arrange: chuẩn bị
//		User inputUser = new User(null, "kyler", "kyler@gmail.com");
//
//		// act: hành động
//		String resultString = this.mockMvc
//				.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsBytes(inputUser)))
//				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
//		User outputUser = objectMapper.readValue(resultString, User.class);
//
//		// assert: so sánh
//		assertEquals(inputUser.getName(), outputUser.getName());
//		assertEquals(inputUser.getEmail(), outputUser.getEmail());
//	}
//
//	@Test
//	public void getAllUsers_shouldReturnUsers() throws Exception {
//		// arrange: chuẩn bị
//		User user1 = new User(null, "name1", "name1@gmail.com");
//		User user2 = new User(null, "name2", "name2@gmail.com");
//		List<User> users = List.of(user1, user2);
//		this.userRepository.saveAll(users);
//
//		// act: hành động
//		String resultString = this.mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn().getResponse()
//				.getContentAsString();
//
//		List<User> resultUsers = this.objectMapper.readValue(resultString, new TypeReference<List<User>>() {
//		});
//		// TypeReference<T> để giữ lại kiểu đầy đủ (List<User>)
//
//		// assert: so sánh
//		assertEquals(2, resultUsers.size());
//		assertEquals("name1@gmail.com", resultUsers.get(0).getEmail());
//	}
//
//	@Test
//	public void getUserById_shouldReturnUser_whenIdFound() throws Exception {
//		// arrange: chuẩn bị
//		User user1 = new User(null, "name1", "name1@gmail.com");
//		User inputUser = this.userRepository.saveAndFlush(user1);// lưu và trả về user đã lưu
//
//		// act: hành động
//		String resultString = this.mockMvc.perform(get("/users/{id}", inputUser.getId())).andExpect(status().isOk())
//				.andReturn().getResponse().getContentAsString();
//		User outputUser = this.objectMapper.readValue(resultString, User.class);
//
//		// assert: so sánh
//		assertEquals("name1", outputUser.getName());
//		assertEquals("name1@gmail.com", outputUser.getEmail());
//	}
//
//	@Test
//	public void getUserById_shouldReturnUser_whenIdNotFound() throws Exception {
//		// arrange: chuẩn bị
//
//		// act: hành động
//		this.mockMvc.perform(get("/users/{id}", 0)).andExpect(status().isNotFound());
//
//		// assert: so sánh
//	}
//
//	@Test
//	public void updateUser_shouldReturnUser_whenValid() throws Exception {
//		// arrange: chuẩn bị
//		User user = new User(null, "nameOld", "nameOld@gmail.com");
//		User inputUser = this.userRepository.saveAndFlush(user);// lưu và trả về user đã lưu
//		User userUpdate = new User(inputUser.getId(), "nameNew", "nameNew@gmail.com");
//
//		// act: hành động
//		String resultString = this.mockMvc
//				.perform(put("/users/{id}", inputUser.getId()).contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsBytes(userUpdate)))
//				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//		User outputUser = objectMapper.readValue(resultString, User.class);
//
//		// assert: so sánh
//		assertEquals("nameNew", outputUser.getName());
//		assertEquals("nameNew@gmail.com", outputUser.getEmail());
//	}
//
//	@Test
//	public void deleteUser_shouldReturnVoid_whenUserExists() throws Exception {
//		// arrange: chuẩn bị
//		User user = new User(null, "name", "name@gmail.com");
//		User inputUser = this.userRepository.saveAndFlush(user);// lưu và trả về user đã lưu
//
//		// act: hành động
//		this.mockMvc.perform(delete("/users/{id}", inputUser.getId()).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNoContent());
//
//		// assert: so sánh
//		long numberUser = this.userRepository.count();
//		assertEquals(0, numberUser);
//	}
//
//}

@IntegrationTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void init() {
		this.userRepository.deleteAll();
	}

	@Test
	public void createUser_shouldReturnUser_whenValid() throws Exception {
		// arrange
		User inputUser = new User(null, "hoidanit IT", "hoidanit.create@gmail.com");

		// action
		String resultStr = mockMvc
				.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(inputUser)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		// assert
		ApiResponse<User> response = objectMapper.readValue(resultStr, new TypeReference<ApiResponse<User>>() {
		});

		assertEquals("success", response.getStatus(), "Status phải là 'success'");
		assertNotNull(response.getMessage(), "Message không được null");
		assertNotNull(response.getData(), "Data không được null");
		assertTrue(response.getData() instanceof User,
				"Data phải là User, nhưng nhận được: " + response.getData().getClass().getSimpleName());
		assertEquals(inputUser.getName(), response.getData().getName(), "Tên user không khớp");
		assertEquals(inputUser.getEmail(), response.getData().getEmail(), "Email user không khớp");
		assertNull(response.getErrorCode(), "ErrorCode phải là null khi thành công");
		assertNotNull(response.getTimestamp(), "Timestamp không được null");
	}

	@Test
	public void getAllUsers() throws Exception {
		// arrange
		User user1 = new User(null, "name1", "hoidanit@gmail.com");
		User user2 = new User(null, "name2", "test@gmail.com");
		List<User> data = List.of(user1, user2);
		this.userRepository.saveAll(data);

		// action
		String resultStr = mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		// assert
		ApiResponse<List<User>> response = objectMapper.readValue(resultStr,
				new TypeReference<ApiResponse<List<User>>>() {
				});

		assertEquals("success", response.getStatus(), "Status phải là 'success'");
		assertNotNull(response.getMessage(), "Message không được null");
		assertNotNull(response.getData(), "Data không được null");
		assertTrue(response.getData() instanceof List,
				"Data phải là List, nhưng nhận được: " + response.getData().getClass().getSimpleName());
		assertEquals(2, response.getData().size(), "Số lượng user không đúng");
		assertTrue(response.getData().get(0) instanceof User, "Phần tử trong List phải là User");
		assertEquals("hoidanit@gmail.com", response.getData().get(0).getEmail(), "Email user đầu tiên không khớp");
		assertNull(response.getErrorCode(), "ErrorCode phải là null khi thành công");
		assertNotNull(response.getTimestamp(), "Timestamp không được null");
	}

	@Test
	public void getUserById() throws Exception {
		// arrange
		User user = new User(null, "name-get-by-id", "hoidanit@gmail.com");
		User userInput = this.userRepository.saveAndFlush(user);

		// action
		String resultStr = mockMvc.perform(get("/users/{id}", userInput.getId())).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();

		// assert
		ApiResponse<User> response = objectMapper.readValue(resultStr, new TypeReference<ApiResponse<User>>() {
		});

		assertEquals("success", response.getStatus(), "Status phải là 'success'");
		assertNotNull(response.getMessage(), "Message không được null");
		assertNotNull(response.getData(), "Data không được null");
		assertTrue(response.getData() instanceof User,
				"Data phải là User, nhưng nhận được: " + response.getData().getClass().getSimpleName());
		assertEquals("name-get-by-id", response.getData().getName(), "Tên user không khớp");
		assertNull(response.getErrorCode(), "ErrorCode phải là null khi thành công");
		assertNotNull(response.getTimestamp(), "Timestamp không được null");
	}

	@Test
	public void getUserById_shouldReturnError_whenIdNotFound() throws Exception {
		// arrange
		long nonExistentId = 0L;

		// action
		String resultStr = mockMvc.perform(get("/users/{id}", nonExistentId)).andExpect(status().isNotFound())
				.andReturn().getResponse().getContentAsString();

		// assert
		ApiResponse<Object> response = objectMapper.readValue(resultStr, new TypeReference<ApiResponse<Object>>() {
		});

		assertEquals("error", response.getStatus(), "Status phải là 'error'");
		assertNotNull(response.getMessage(), "Message không được null");
		assertNull(response.getData(), "Data phải là null khi lỗi");
		assertEquals("NOT_FOUND", response.getErrorCode(), "ErrorCode không đúng");
		assertNotNull(response.getTimestamp(), "Timestamp không được null");
	}

	@Test
	public void updateUser() throws Exception {
		// arrange
		User user = new User(null, "old-name", "old@gmail.com");
		User userInput = this.userRepository.saveAndFlush(user);
		User updateUser = new User(userInput.getId(), "new-name", "new@gmail.com");

		// action
		String resultStr = mockMvc
				.perform(put("/users/{id}", userInput.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsBytes(updateUser)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// assert
		ApiResponse<User> response = objectMapper.readValue(resultStr, new TypeReference<ApiResponse<User>>() {
		});

		assertEquals("success", response.getStatus(), "Status phải là 'success'");
		assertNotNull(response.getMessage(), "Message không được null");
		assertNotNull(response.getData(), "Data không được null");
		assertTrue(response.getData() instanceof User,
				"Data phải là User, nhưng nhận được: " + response.getData().getClass().getSimpleName());
		assertEquals("new-name", response.getData().getName(), "Tên user không khớp");
		assertEquals("new@gmail.com", response.getData().getEmail(), "Email user không khớp");
		assertNull(response.getErrorCode(), "ErrorCode phải là null khi thành công");
		assertNotNull(response.getTimestamp(), "Timestamp không được null");
	}

	@Test
	public void deleteUser() throws Exception {
		// arrange
		User user = new User(null, "delete-name", "delete@gmail.com");
		User userInput = this.userRepository.saveAndFlush(user);

		// action
		String resultStr = mockMvc
				.perform(delete("/users/{id}", userInput.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// assert
		if (!resultStr.isEmpty()) { // Nếu API trả về body
			ApiResponse<Object> response = objectMapper.readValue(resultStr, new TypeReference<ApiResponse<Object>>() {
			});
			assertEquals("success", response.getStatus(), "Status phải là 'success'");
			assertNotNull(response.getMessage(), "Message không được null");
			assertNull(response.getData(), "Data phải là null khi xóa");
			assertNull(response.getErrorCode(), "ErrorCode phải là null khi thành công");
			assertNotNull(response.getTimestamp(), "Timestamp không được null");
		}
		long countDB = this.userRepository.count();
		assertEquals(0, countDB, "Số lượng user trong DB phải là 0 sau khi xóa");
	}
}
