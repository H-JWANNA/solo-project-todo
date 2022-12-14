package com.todobackend.soloproject.todo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.todobackend.soloproject.todo.dto.TodoDto;
import com.todobackend.soloproject.todo.entity.Todos;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
	Todos todoPostToTodo(TodoDto.Post requestBody);

	Todos todoPatchToTodo(TodoDto.Patch requestBody);

	TodoDto.Response todoToTodoResponse(Todos todo);

	List<TodoDto.Response> todosToTodoResponse(List<Todos> todos);
}
