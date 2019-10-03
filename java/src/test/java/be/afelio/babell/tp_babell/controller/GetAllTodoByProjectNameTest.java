package be.afelio.babell.tp_babell.controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllTodoByProjectNameTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;
	TypeReference<ResponseDto<List<TodoDto>>> type = new TypeReference<ResponseDto<List<TodoDto>>>() {};
	
	@Test
	public void testGetAllTodoByProjectNameTestReturnCode () {
		assertRest.assertReturnCode("/todoproject/Test", 200);
	}
	
	@Test
	public void testGetAllTodoByProjectNameTestReturnStatusSuccess() {
		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/todoproject/Test",type);
	}
	
	@Test 
	public void testGetAllTodoByProjectNameTestPlayLoad() {
		ResponseDto<List<TodoDto>> responseDto = assertRest.getDto("/todoproject/Test", type);
		assertEquals(2, responseDto.getPayload().size());
		System.out.println(responseDto.getPayload());
		assertTrue( responseDto.getPayload().contains(createTestTodo()));
	}
	
	
	private TodoDto createTestTodo() {
		return new TodoDto(23, "test", "test description", false, false); 
	}

}
