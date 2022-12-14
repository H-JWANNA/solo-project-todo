package com.todobackend.soloproject.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
