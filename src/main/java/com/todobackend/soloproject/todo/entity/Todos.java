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
  private long id;

//  @Column
  private String title;

//  @Column
  private int todo_order;

//  @Column
  private boolean completed;
}
