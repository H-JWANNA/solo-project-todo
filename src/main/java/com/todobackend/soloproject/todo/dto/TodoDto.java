package com.todobackend.soloproject.todo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

  @Getter
  @AllArgsConstructor
  public static class Post {

    private String title;
    private Long todoOrder;
    private Boolean completed;
  }

  @Getter
  @AllArgsConstructor
  public static class Patch {
    @NotBlank
    private Long id;
    private String title;
    private Long todoOrder;
    private Boolean completed;

    public void setId(long id) {
      this.id = id;
    }
  }

  @Getter
  @AllArgsConstructor
  public static class Response {
    private Long id;
    private String title;
    private Long todoOrder;
    private boolean completed;
  }
}
