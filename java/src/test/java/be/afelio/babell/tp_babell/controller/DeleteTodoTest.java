package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import be.afelio.babell.tp_babell.test_utils.AssertRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DeleteTodoTest {

	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;

	@Test
	public void test() throws Exception {

		jdbcTemplate.update("INSERT INTO todo (id_todo, name, description, in_progress, done, id_project) VALUES (default, 'testTodo','test description', false, false, 2)");
		try {

			RequestEntity<Void> requestEntity = new RequestEntity<Void>(assertRest.getHeaders(),HttpMethod.DELETE,
					URI.create("/todoproject/Test/testTodo" ));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkTodoForTestDeleted());
		}finally {
			jdbcTemplate.update("delete from todo  Where name = 'testTodo'");
			
		}
	}
	
	boolean checkTodoForTestDeleted() {
		boolean deleted = false;
		try {
			jdbcTemplate.queryForObject("Select id_todo from todo Where name = 'testTodo'", Integer.class);
		} catch (EmptyResultDataAccessException e) {
			deleted = true;
		}
		return deleted;
	}

}
