package com.todobackend.soloproject.todo.repository;

import com.todobackend.soloproject.todo.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todos, Long> {
}
