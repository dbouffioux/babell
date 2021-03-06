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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.person.UpdatePersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.test_utils.AssertRest;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UpdatePersonTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	AssertRest assertRest;
	ObjectMapper mapper = new ObjectMapper();	

	@Test
	public void test() throws Exception {
		
		jdbcTemplate.update("INSERT INTO person (id_person, firstname, lastname, email, password) VALUES (default, 'Toto','Titi', 'toto@mail.be', '1234')");
		int id = jdbcTemplate.queryForObject("Select id_person From person Where firstname ='Toto' And lastname ='Titi'", Integer.class);
		
		try {
			String token = assertRest.getToken();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + token);
			RequestEntity<UpdatePersonDto>requestEntity
			=  new RequestEntity<UpdatePersonDto>(updatePersonForTest(id), headers, HttpMethod.PUT, URI.create("/user"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkPersonForTestUpdate(id));
			
			
		}finally {
			jdbcTemplate.update("delete from person  Where id_person = " + id);
			
		}
	}
	
	UpdatePersonDto updatePersonForTest(int id) {
		
		UpdatePersonDto person = new UpdatePersonDto("Toto", "Toutou", "toto@mail.be", "789654");
		person.setId(id);
		return person;
		
	}
	
	boolean checkPersonForTestUpdate(int id) {
	
		String updateLastname = "Toutou";
		String name = jdbcTemplate.queryForObject("Select lastname from person where id_person =  " + id, String.class);

		return updateLastname.equals(name);
	}
}
