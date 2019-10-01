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


import be.afelio.babell.tp_babell.api.dto.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDtoStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PostTodoTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		try {
			CreateTodoDto todoDto = createTodoForTest();
			RequestEntity<CreateTodoDto> requestEntity = new RequestEntity<CreateTodoDto>(todoDto, HttpMethod.POST,
					URI.create("/todoproject/Test"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();

			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {
			};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkTodoForTestCreated());

		} finally {
			jdbcTemplate.update("delete from todo  Where name = 'new for test'");

		}

	}
	
	CreateTodoDto createTodoForTest() {
		return new CreateTodoDto("new for test", "test description", 2);
		
	}
	
	boolean checkTodoForTestCreated() {
		boolean created = false;
		Integer id = jdbcTemplate.queryForObject("Select id_todo from todo Where name = 'new for test'", Integer.class);
				
				created = id != null;
		return created;
	}
	

}
