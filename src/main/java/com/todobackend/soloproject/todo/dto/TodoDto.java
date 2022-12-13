package com.todobackend.soloproject.todo.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

  @Getter
  public static class Post {
    @NotBlank
    private String title;

    @NotBlank
    private int todoOrder;

    private boolean completed;
  }

  @Getter
  public static class Patch {
    @NotBlank
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private int todoOrder;

    private boolean completed;

    public void setId(long id) {
      this.id = id;
    }
  }

  @Getter
  public static class Response {
    private long id;
    private String title;
    private int todoOrder;
    private boolean completed;
  }
}
