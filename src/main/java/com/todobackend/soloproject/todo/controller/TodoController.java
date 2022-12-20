package com.todobackend.soloproject.todo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todobackend.soloproject.todo.dto.TodoDto;
import com.todobackend.soloproject.todo.entity.Todos;
import com.todobackend.soloproject.todo.mapper.TodoMapper;
import com.todobackend.soloproject.todo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
public class TodoController {
	private final TodoService todoService;
	private final TodoMapper mapper;

	public TodoController(TodoService todoService, TodoMapper mapper) {
		this.todoService = todoService;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody) {
		log.info("postTodo -> title: {}, order: {}, completed{}",
			requestBody.getTitle(), requestBody.getTodoOrder(), requestBody.getCompleted());

		Todos todo = mapper.todoPostToTodo(requestBody);
		Todos createdTodo = todoService.createTodo(todo);
		TodoDto.Response response = mapper.todoToTodoResponse(createdTodo);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
		@Valid @RequestBody TodoDto.Patch requestBody) {

		Todos todo = mapper.todoPatchToTodo(requestBody);
		todo.setId(id);
		Todos updatedTodo = todoService.updateTodo(todo);
		TodoDto.Response response = mapper.todoToTodoResponse(updatedTodo);

		log.info("patchTodo");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity getTodos() {
		List<Todos> todos = todoService.findTodos();
		List<TodoDto.Response> responses = mapper.todosToTodoResponse(todos);

		log.info("getTodos");

		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
		Todos todo = todoService.findTodo(id);
		TodoDto.Response response = mapper.todoToTodoResponse(todo);

		log.info("getTodo");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity deleteTodos() {
		log.info("deleteTodos");
		todoService.deleteTodos();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteTodo(@PathVariable("id") @Positive long id) {
		log.info("deleteTodo");
		todoService.deleteTodo(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
