package com.kyler.user_manage.controller.errors;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kyler.user_manage.entity.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse<?>> handleNotFound(NoSuchElementException ex) {
		ApiResponse<?> result = new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage(), null, "NOT_FOUND");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
		String errors = String.join("; ", errorList);

		ApiResponse<Object> response = new ApiResponse<>(HttpStatus.BAD_REQUEST, errors, null, "BAD_REQUEST");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleAllException(Exception ex) {
		ApiResponse<?> result = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null,
				"INTERNAL_SERVER_ERROR");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
	}

}
