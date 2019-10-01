package be.afelio.babell.tp_babell.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.test_utils.AssertRest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetOneTodoByIdTodoTest {

	@Autowired
	TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;

	@Test
	public void testTodoById23ReturnCode() {

		assertRest.assertReturnCode("/todoproject/test/23", 200);

	}

	@Test
	public void testTodoById23ReturnStatusSucces() {
		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/todoproject/test/23");
	}

	@Test
	public void testTodoById23PlayLoad() {
		assertRest.assertPlayLoad(createTodoTest(), "/todoproject/test/23", new TypeReference<ResponseDto<TodoDto>>() {
		});
	}

	private TodoDto createTodoTest() {
		return new TodoDto(23, "test", "test description", false, false);

	}

}
