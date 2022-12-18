package com.todobackend.soloproject.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private Long todoOrder;

	private Boolean completed;

	public Todos(String title, Long todoOrder, Boolean completed) {
		this.title = title;
		this.todoOrder = todoOrder;
		this.completed = completed;
	}
}
