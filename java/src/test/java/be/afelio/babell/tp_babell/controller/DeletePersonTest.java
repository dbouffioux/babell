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
import org.springframework.http.HttpHeaders;
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
public class DeletePersonTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;

	@Test
	public void test() throws Exception {
		
		jdbcTemplate.update("INSERT INTO person (id_person, firstname, lastname, email, password) VALUES (default, 'Toto','Titi', 'toto@mail.be', '1234')");
		try {
			String token = assertRest.getToken();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + token);
			
			RequestEntity<Void> requestEntity = new RequestEntity<Void>(headers, HttpMethod.DELETE,
					URI.create("/person/toto@mail.be" ));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkPersonForTestDeleted());
			
			
			
		}finally {
			jdbcTemplate.update("delete from person  Where firstname = 'Toto' And lastname = 'Titi'");
			
		}
	}
	
	boolean checkPersonForTestDeleted() {
		boolean deleted = false;
		try {
			jdbcTemplate.queryForObject("Select id_person from person Where firstname = 'Toto' AND lastname = 'Titi'", Integer.class);
		} catch (EmptyResultDataAccessException e) {
			deleted = true;
		}
		return deleted;
	}

}
