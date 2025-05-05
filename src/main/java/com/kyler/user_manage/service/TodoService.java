package com.kyler.user_manage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyler.user_manage.entity.Todo;
import com.kyler.user_manage.repository.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public Todo handleCreateTodo(Todo todo) {
		return this.todoRepository.save(todo);
	}

	public List<Todo> handleGetTodos() {
		return this.todoRepository.findAll();
	}

	public Optional<Todo> handleGetTodo(long id) {
		return this.todoRepository.findById(id);
	}

	public Todo handleUpdateTodo(long id, Todo todo) {
		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			Todo todoUpdated = todoOptional.get();
			todoUpdated.setUserName(todo.getUserName());
			this.todoRepository.save(todoUpdated);
			return todoUpdated;
		}
		return null;
	}

	public String handleDeleteTodo(long id) {
		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			Todo todo = todoOptional.get();
			this.todoRepository.delete(todo);
			return "Success";
		}
		return "Fail";
	}
}
