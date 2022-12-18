package com.todobackend.soloproject.slice;

import static com.todobackend.soloproject.utils.ApiDocumentUtils.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.todobackend.soloproject.todo.controller.TodoController;
import com.todobackend.soloproject.todo.dto.TodoDto;
import com.todobackend.soloproject.todo.entity.Todos;
import com.todobackend.soloproject.todo.mapper.TodoMapper;
import com.todobackend.soloproject.todo.service.TodoService;

@WebMvcTest(TodoController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Gson gson;

	@MockBean
	private TodoService todoService;

	@MockBean
	private TodoMapper mapper;

	@Test
	public void postTodoTest() throws Exception {
		// given : Test 용 Request Body 생성
		TodoDto.Post post = new TodoDto.Post("title1", 1L, false);
		TodoDto.Response response = new TodoDto.Response(1L, "title1", 1L, false);

		given(mapper.todoPostToTodo(any(TodoDto.Post.class))).willReturn(new Todos());
		given(todoService.createTodo(any(Todos.class))).willReturn(new Todos());
		given(mapper.todoToTodoResponse(any(Todos.class))).willReturn(response);

		String content = gson.toJson(post);

		// when : MockMvc 객체로 테스트 대상 Controller 호출
		ResultActions actions =
			mockMvc.perform(post("/")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content));

		// then : Controller 메서드에서 응답으로 수신한 HTTP Status 및 Response Body를 통한 검증
		actions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value(post.getTitle()))
			.andExpect(jsonPath("$.todoOrder").value(post.getTodoOrder()))
			.andExpect(jsonPath("$.completed").value(post.getCompleted()))
			.andDo(document(
					"post-todo",
					getRequestPreProcessor(),
					getResponsePreProcessor(),
					requestFields(
						fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
						fieldWithPath("todoOrder").type(JsonFieldType.NUMBER).description("번호"),
						fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("완료 여부")
					),
					responseFields(
						fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
						fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
						fieldWithPath("todoOrder").type(JsonFieldType.NUMBER).description("번호"),
						fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("완료 여부")
					)
				)
			);
	}

	@Test
	public void getTodosTest() throws Exception {
		// given
		Todos todo1 = new Todos("title1", null, false);
		Todos todo2 = new Todos("title2", null, false);
		List<Todos> todos = List.of(todo1, todo2);

		TodoDto.Response response1 = new TodoDto.Response(1L, "title1", null, false);
		TodoDto.Response response2 = new TodoDto.Response(2L, "title2", null, false);
		List<TodoDto.Response> responses = List.of(response1, response2);

		given(todoService.findTodos()).willReturn(todos);
		given(mapper.todosToTodoResponse(anyList())).willReturn(responses);

		// when
		ResultActions actions =
			mockMvc.perform(
				get("/")
					.accept(MediaType.APPLICATION_JSON)
			);

		// then
		actions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()").value(responses.size()))
			.andDo(
				document("get-todos",
					getResponsePreProcessor(),
					responseFields(
						fieldWithPath("id").type(JsonFieldType.NUMBER),
						fieldWithPath("title").type(JsonFieldType.STRING).description("타이틀"),
						fieldWithPath("todoOrder").type(JsonFieldType.NUMBER).description("번호"),
						fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("완료 여부")
					)
				)
			);
	}
}
