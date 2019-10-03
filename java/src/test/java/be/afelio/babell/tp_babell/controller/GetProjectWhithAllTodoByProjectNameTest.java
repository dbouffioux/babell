package be.afelio.babell.tp_babell.controller;



import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import be.afelio.babell.tp_babell.api.dto.project.ProjectWithTodoDto;
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
public class GetProjectWhithAllTodoByProjectNameTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;
	TypeReference<ResponseDto<ProjectWithTodoDto>> type = new TypeReference<ResponseDto<ProjectWithTodoDto>>() {};
	
	@Test
	public void GetProjectWhithAllTodoByProjectNameTestReturnCode () {
		assertRest.assertReturnCode("/todoproject/Test", 200);
	}
	
	@Test
	public void GetProjectWhithAllTodoByProjectNameTestReturnStatusSuccess() {
		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/todoproject/Test",type);
	}
	
	@Test 
	public void GetProjectWhithAllTodoByProjectNameTestPlayLoad() {
		ResponseDto<ProjectWithTodoDto> responseDto = assertRest.getDto("/todoproject/Test", type);
		//assertEquals(2, responseDto.getPayload().getTodoDtoList().size());
		System.out.println(responseDto.getPayload());
		assertTrue( responseDto.getPayload().equals(createTestTodo()));
	}
	
	@Test
	public void GetProjectWhithAllTodoByProjectNameTestMessage() {
		assertRest.assertDtoMessage("2 todos found", "/todoproject/Test", type);
	}
	
	
	private ProjectWithTodoDto createTestTodo() {
		TodoDto todo1 = new TodoDto();
		TodoDto todo2 = new TodoDto();
		todo1.setId(1);
		todo1.setName("testForUpdate3");
		todo1.setDescription("test description");
		todo1.setInProgress(false);
		todo1.setDone(false);
		todo2.setId(2);
		todo2.setName("test");
		todo2.setDescription("test description");
		todo2.setInProgress(false);
		todo2.setDone(false);
		
		List<TodoDto> todoDtoList = new ArrayList<TodoDto>();
		todoDtoList.add(todo1);
		todoDtoList.add(todo2);
		
		ProjectWithTodoDto projet = new ProjectWithTodoDto();
		projet.setId(2);
		projet.setName("Test");
		projet.setProjectStart(LocalDate.of(2019, Month.OCTOBER, 26));
		projet.setProjectEnd(LocalDate.of(2019, Month.NOVEMBER, 6));
		projet.setTodoDtoList(todoDtoList);
		
		return projet; 
	}

}
