package com.todobackend.soloproject.todo.service;

import com.todobackend.soloproject.todo.entity.Todos;
import com.todobackend.soloproject.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todos createTodo (Todos todo) {
    return todoRepository.save(todo);
  }

  public Todos updateTodo (Todos todo) {
    Todos findTodo = findTodo(todo.getId());

    Optional.ofNullable(todo.getTitle())
        .ifPresent(findTodo::setTitle);
    Optional.ofNullable(todo.getTodoOrder())
        .ifPresent(findTodo::setTodoOrder);
    Optional.ofNullable(todo.getCompleted())
        .ifPresent(findTodo::setCompleted);


    return todoRepository.save(findTodo);
  }

  public Todos findTodo (long id) {
    Optional<Todos> optionalTodo = todoRepository.findById(id);
    Todos findTodo = optionalTodo.orElseThrow(RuntimeException::new);

    return findTodo;
  }

  public List<Todos> findTodos() {
    return todoRepository.findAll();
  }

  public void deleteTodo (long id) {
    todoRepository.deleteById(id);
  }

  public void deleteTodos() {
    todoRepository.deleteAll();
  }
}
