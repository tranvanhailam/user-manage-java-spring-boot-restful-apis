package com.kyler.user_manage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyler.user_manage.entity.Todo;
import com.kyler.user_manage.entity.User;
import com.kyler.user_manage.service.TodoService;

@RestController
public class TodoController {

	private final TodoService todoService;

	@Autowired
	private ObjectMapper objectMapper;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/")
	public ResponseEntity<String> index() throws Exception {

		// obj => json
		User user = new User(null, "Kyler", "kyler@gmaiul.com");
		String json = objectMapper.writeValueAsString(user);
		return ResponseEntity.ok().body(json);
	}

	@GetMapping("/kyler")
	public ResponseEntity<Todo> kyler() {
		Todo todo = new Todo("Kyler");
		return ResponseEntity.ok().body(todo);
	}

	// get all
	@GetMapping("/todos")
	public ResponseEntity<List<Todo>> getTodos() {
		List<Todo> todos = this.todoService.handleGetTodos();
		return ResponseEntity.status(200).body(todos);
	}

	// get by Id
	@GetMapping("/todo/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable long id) {
		Optional<Todo> todoOptional = this.todoService.handleGetTodo(id);
		if (todoOptional.isPresent()) {
			return ResponseEntity.ok().body(todoOptional.get());
		}
		return ResponseEntity.status(404).body(null);
	}

	// create
	@PostMapping("/todos")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todoInput) {
		Todo newTodo = this.todoService.handleCreateTodo(todoInput);
		return newTodo != null ? ResponseEntity.status(HttpStatus.CREATED).body(newTodo)
				: ResponseEntity.status(500).body(newTodo);
	}

	// update
	@PutMapping("/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo todoInput) {
		Todo todoUpdated = this.todoService.handleUpdateTodo(id, todoInput);
		return todoUpdated != null ? ResponseEntity.status(HttpStatus.CREATED).body(todoUpdated)
				: ResponseEntity.status(500).body(todoUpdated);
	}

	// delete
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") long id) {
		String result = this.todoService.handleDeleteTodo(id);
		return result == "Success" ? ResponseEntity.status(200).body("Delete success")
				: ResponseEntity.status(500).body("Delete fail");
	}

}
