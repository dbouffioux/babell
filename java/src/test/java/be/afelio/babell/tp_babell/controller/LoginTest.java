package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.util.Base64;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.jwt.model.JwtResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginTest {

	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testLoginOk() throws Exception {

		String btoa = Base64.getEncoder().encodeToString("delphine@mail.be:testenligne123".getBytes());

		RequestEntity<String> requestEntity = RequestEntity.post(URI.create("/login"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).body("btoa=" + btoa);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());

		String json = response.getBody();

		TypeReference<ResponseDto<JwtResponse>> type = new TypeReference<ResponseDto<JwtResponse>>() {
		};
		ResponseDto<JwtResponse> responseDto = mapper.readValue(json, type);
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		assertNotNull(responseDto.getPayload().getToken());

	}
	
	@Test
	public void testLoginNOkWithWrongPassword() throws Exception {

		String btoa = Base64.getEncoder().encodeToString("delphine@mail.be:1234".getBytes());

		RequestEntity<String> requestEntity = RequestEntity.post(URI.create("/login"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).body("btoa=" + btoa);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());

		String json = response.getBody();

		TypeReference<ResponseDto<JwtResponse>> type = new TypeReference<ResponseDto<JwtResponse>>() {
		};
		ResponseDto<JwtResponse> responseDto = mapper.readValue(json, type);
		assertEquals(ResponseDtoStatus.FAILURE, responseDto.getStatus());
		assertEquals("failure to connect", responseDto.getMessage());

	}
	@Test
	public void testLoginNOkWithWrongEmail() throws Exception {

		String btoa = Base64.getEncoder().encodeToString("toto@mail.be:1234".getBytes());

		RequestEntity<String> requestEntity = RequestEntity.post(URI.create("/login"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).body("btoa=" + btoa);
		ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());

		String json = response.getBody();

		TypeReference<ResponseDto<JwtResponse>> type = new TypeReference<ResponseDto<JwtResponse>>() {
		};
		ResponseDto<JwtResponse> responseDto = mapper.readValue(json, type);
		assertEquals(ResponseDtoStatus.FAILURE, responseDto.getStatus());
		assertEquals("person not found", responseDto.getMessage());

	}

	
}
