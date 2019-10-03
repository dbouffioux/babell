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
public class GetOnePersonByEmailTest {

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
	public void testForExistingEmailReturnCode() {

		assertRest.assertReturnCode("/person/delphine@mail.be", 200);

	}

	@Test
	public void testExistingEmailReturnStatusSucces() {

		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/person/delphine@mail.be", type);
	}

	@Test
	public void testExistingEmailPlayLoad() {
		assertRest.assertPlayLoad(createForTest(), "/person/delphine@mail.be", type);
	}

	@Test
	public void testForNotExistingEmail() throws Exception {

		assertRest.assertReturnCode("/person/titi@mail.be", 200);
		assertRest.assertDtoStatus(ResponseDtoStatus.FAILURE, "/person/titi@mail.be", type);

	}

	PersonDto createForTest() {
		return new PersonDto("Delphine", "Franquinet", "delphine@mail.be");

	}

}
