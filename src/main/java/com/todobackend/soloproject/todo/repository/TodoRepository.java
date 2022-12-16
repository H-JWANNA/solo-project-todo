package com.todobackend.soloproject.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todobackend.soloproject.todo.entity.Todos;

public interface TodoRepository extends JpaRepository<Todos, Long> {
}
