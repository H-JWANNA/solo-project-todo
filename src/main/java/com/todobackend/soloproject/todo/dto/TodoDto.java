package com.todobackend.soloproject.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class TodoDto {

	@Getter
	@AllArgsConstructor
	public static class Post {

		private String title;
		private Long todoOrder;
		private Boolean completed;
	}

	@Getter
	public static class Patch {

		private final String title;
		private final Long todoOrder;
		private final Boolean completed;

		public Patch(String title, Long todoOrder, Boolean completed) {
			this.title = title;
			this.todoOrder = todoOrder;
			this.completed = completed;
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
