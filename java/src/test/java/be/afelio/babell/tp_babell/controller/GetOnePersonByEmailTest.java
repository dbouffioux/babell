package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

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

import be.afelio.babell.tp_babell.api.dto.PersonDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDtoStatus;
import be.afelio.babell.tp_babell.test_utils.AssertRest;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetOnePersonByEmailTest {
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;

	@Test
	public void testForExistingEmailReturnCode() {

		assertRest.assertReturnCode("/person/toto@mail.be", 200);
		
		/*PersonDto expected = createTotoTitiForTest();
		PersonDto actual = responseDto.getPayload();
		assertEquals(expected, actual);*/	
		
	}
	
	@Test
	public void testExistingEmailReturnStatusSucces() {
		
		//ResponseDto<PersonDto> dto = assertRest.getDto("/person/toto@mail.be", new TypeReference<ResponseDto<PersonDto>>() {});
		//assertEquals(ResponseDtoStatus.SUCCESS, dto.getStatus()); // on vérifie que le status dans responseDto est bien success

	}
	
	@Test
	public void testForNotExistingEmail() throws Exception{
		ResponseEntity<String> response = restTemplate.getForEntity("/person/titi@mail.be", String.class);
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		TypeReference<ResponseDto<PersonDto>> type = new TypeReference<ResponseDto<PersonDto>>() {};
		ResponseDto<PersonDto> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.FAILURE, responseDto.getStatus());
		
		// assertNull(responseDto.getPayload()); il vérifie que le payload est null
	}
	
	
	
	PersonDto createTotoTitiForTest() {
		return new PersonDto("Toto", "Titi", "toto@mail.be");
		
	}
	

	// ne pas oublier le message
	// un seul assert par méthode
	
}
