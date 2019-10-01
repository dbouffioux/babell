package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws Exception {
		try {
			
			RequestEntity<String> requestEntity = RequestEntity
				     .post(URI.create("/login"))
				     .contentType(MediaType.APPLICATION_JSON)
				     .body(createLoginForTest());
			
			
			//new RequestEntity<String>(createLoginForTest(), HttpMethod.POST,					URI.create("/login"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
//			String json = response.getBody();
//
//			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {
//			};
//			ResponseDto<Void> responseDto = mapper.readValue(json, type);
//			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
//			//assertTrue(checkTodoForTestCreated());

		} finally {
//			jdbcTemplate.update("delete from todo  Where name = 'new for test'");

		}

	}
	String createLoginForTest() {
		
		return "{\"username\":\"delphine@mail.be\",\"password\":\"1234\"}";
	}
}
