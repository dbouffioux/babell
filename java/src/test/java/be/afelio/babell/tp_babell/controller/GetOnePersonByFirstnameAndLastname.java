package be.afelio.babell.tp_babell.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.test_utils.AssertRest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetOnePersonByFirstnameAndLastname {

	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;
	TypeReference<ResponseDto<PersonDto>> type = new TypeReference<ResponseDto<PersonDto>>() {
	};

	@Test
	public void testForExistingReturnCode() {

		assertRest.assertReturnCode("/person/Delphine/Franquinet", 200);

	}

	@Test
	public void testExistingReturnStatusSucces() {

		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/person/Delphine/Franquinet", type);
	}

	@Test
	public void testExistingPlayLoad() {
		assertRest.assertPlayLoad(createForTest(), "/person/Delphine/Franquinet", type);
	}
	
	@Test
	public void testNotExistingFirstnameAndLastName() {
		assertRest.assertReturnCode("/person/Toto/tit", 200);
		assertRest.assertDtoStatus(ResponseDtoStatus.FAILURE, "/person/Toto/tit", type);
	}
	
	@Test
	public void GetOnePersonByFirstnameAndLastnameTestMessageOk() {
		assertRest.assertDtoMessage(" person found", "/person/Delphine/Franquinet", type);
		
	}
	
	@Test
	public void GetOnePersonByFirstnameAndLastnameTestMessageNOk() {
		assertRest.assertDtoMessage("unexpected exception", "/person/Toto/tit", type);
		
	}

	PersonDto createForTest() {
		return new PersonDto("Delphine", "Franquinet", "delphine@mail.be");

	}

}
