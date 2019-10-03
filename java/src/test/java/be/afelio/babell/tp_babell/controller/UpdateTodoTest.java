package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.dto.todo.UpdateTodoDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UpdateTodoTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws Exception {
		
		jdbcTemplate.update("INSERT INTO todo (id_todo, name, description, in_progress, done, id_project) VALUES (default, 'testTodo','test description', false, false, 2)");
		int id = jdbcTemplate.queryForObject("Select id_todo From todo Where name ='testTodo'", Integer.class); // aller chercher l'id todo
		
		try {
			RequestEntity<UpdateTodoDto>requestEntity
			= new RequestEntity<UpdateTodoDto>(updateTodoForTest(id), HttpMethod.PUT, URI.create("/todoproject"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkTodoForTestUpdate(id));
			
			
		}finally {
			jdbcTemplate.update("delete from todo  Where name = 'testTodo'");
			
		}
		
		
	}
	
	UpdateTodoDto updateTodoForTest(int id) {
		return new UpdateTodoDto(id,"testForUpdate", "test description", 30, false, false, 2);
		
	}
	
	boolean checkTodoForTestUpdate(int id) {
		boolean created = false;
		String updateName = "testForUpdate";
		String name = jdbcTemplate.queryForObject("Select name From todo Where id_todo = " + id, String.class);
		if(updateName == name) {
			created = true;
		}
		return created;
	}

}
