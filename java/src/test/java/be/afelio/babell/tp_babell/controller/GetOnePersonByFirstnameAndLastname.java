package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetOnePersonByFirstnameAndLastname {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws Exception {
		jdbcTemplate.update("INSERT INTO public.person(\n" +
				"\tid_person, firstname, lastname, email, password)\n" +
				"\tVALUES (default, 'Toto', 'Titi', 'toto@mail.be', 'test1234');");
	try{
		ResponseEntity<String> response = restTemplate.getForEntity("/person/Toto/Titi", String.class);
		assertEquals(200, response.getStatusCodeValue());

		String json = response.getBody();
		TypeReference<ResponseDto<PersonDto>> type = new TypeReference<ResponseDto<PersonDto>>() {};
		ResponseDto<PersonDto> responseDto = mapper.readValue(json, type);

		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());

		PersonDto expected = createTotoTitiForTest();
		PersonDto actual = responseDto.getPayload();
		assertEquals(expected, actual);
	}finally {
		jdbcTemplate.update("delete from person  Where firstname = 'Toto'");
		
		
	}
	}
	
	PersonDto createTotoTitiForTest() {
		return new PersonDto("Toto", "Titi", "toto@mail.be");
		
	}
	

	
}
