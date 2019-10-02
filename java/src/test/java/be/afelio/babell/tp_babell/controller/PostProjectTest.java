package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;

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

import be.afelio.babell.tp_babell.api.dto.project.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;


;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PostProjectTest {
	
	@Autowired TestRestTemplate restTemplate;
	@Autowired JdbcTemplate jdbcTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		try {
			CreateProjectDto projectDto = createProjectForTest();
			RequestEntity<CreateProjectDto> requestEntity
			= new RequestEntity<CreateProjectDto>(projectDto, HttpMethod.POST, URI.create("/projects"));
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();
			
			TypeReference<ResponseDto<Void>> type = new TypeReference<ResponseDto<Void>>() {};
			ResponseDto<Void> responseDto = mapper.readValue(json, type);
			assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
			assertTrue(checkProjectForTestCreated());
			
			
		}finally {
			jdbcTemplate.update("delete from project  Where name = 'Project for test'");
			
		}
	}
	
	CreateProjectDto createProjectForTest() {
		
		LocalDate start = LocalDate.of(2019, Month.SEPTEMBER, 27);
		LocalDate end = LocalDate.of(2019, Month.OCTOBER, 5);
		CreateProjectDto project = new CreateProjectDto("Project for test",start,end);
		return project;
	}
	
	boolean checkProjectForTestCreated() {
		boolean created = false;
		Integer id = jdbcTemplate.queryForObject("Select id_project from project Where name = 'Project for test'", Integer.class);
				
				created = id != null;
		return created;
	}

}
