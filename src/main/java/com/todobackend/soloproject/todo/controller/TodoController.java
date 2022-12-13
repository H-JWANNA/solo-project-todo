package com.todobackend.soloproject.todo.controller;

import com.todobackend.soloproject.todo.dto.TodoDto;
import com.todobackend.soloproject.todo.entity.Todos;
import com.todobackend.soloproject.todo.mapper.TodoMapper;
import com.todobackend.soloproject.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/todos")
@Validated
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
    Todos todo = mapper.todoPostToTodo(requestBody);
    Todos createdTodo = todoService.createTodo(todo);
    TodoDto.Response response = mapper.todoToTodoResponse(createdTodo);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity getTodos() {
    List<Todos> todos = todoService.findTodos();
    List<TodoDto.Response> responses = mapper.todosToTodoResponse(todos);

    return new ResponseEntity<>(responses, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
    Todos todo = todoService.findTodo(id);
    TodoDto.Response response = mapper.todoToTodoResponse(todo);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
                                  @Valid @RequestBody TodoDto.Patch requestBody) {
    requestBody.setId(id);

    Todos todo = mapper.todoPatchToTodo(requestBody);
    Todos updatedTodo = todoService.updateTodo(todo);
    TodoDto.Response response = mapper.todoToTodoResponse(updatedTodo);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity deleteTodos() {
    todoService.deleteTodos();
    log.info("Delete All");

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteTodo(@PathVariable("id") @Positive long id) {
    todoService.deleteTodo(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
