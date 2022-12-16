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

	//  @Column(nullable = false)
	private String title;

	//  @Column(name = "todo_order")
	private Long todoOrder;

	//  @Column(nullable = false)
	private Boolean completed;
}
