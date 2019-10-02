package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllPersonTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/persons", String.class);
		assertEquals(200, response.getStatusCodeValue());
		String json = response.getBody();
		
		System.out.println(json);
		TypeReference<ResponseDto<List<PersonDto>>> type = new TypeReference<ResponseDto<List<PersonDto>>>() {};
		ResponseDto<List<PersonDto>> responseDto = mapper.readValue(json,  type);
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		List<PersonDto> actual = responseDto.getPayload();
		assertNotNull(responseDto);
		
		PersonDto expected = createTestPerson();
		assertTrue(actual.contains(expected));
	}
	
	PersonDto createTestPerson() {
		
		PersonDto person = new PersonDto("Delphine", "Franquinet", "delphine@mail.be");
		return person;
	}

	

}
