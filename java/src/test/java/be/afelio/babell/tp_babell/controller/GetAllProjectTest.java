package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.test_utils.AssertRest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetAllProjectTest {

	@Autowired
	TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AssertRest assertRest;
	TypeReference<ResponseDto<List<ProjectDto>>> type = new TypeReference<ResponseDto<List<ProjectDto>>>() {
	};

	@Test
	public void testGetAllProjectReturnCode() {
		assertRest.assertReturnCode("/projects", 200);
	}

	@Test
	public void testGetAllProjectReturnStatusSuccess() {
		assertRest.assertDtoStatus(ResponseDtoStatus.SUCCESS, "/projects", type);

	}

	@Test
	public void testGetAllProjectPlayLoad() {
		ResponseDto<List<ProjectDto>> responseDto = assertRest.getDto("/projects", type);
		assertTrue(responseDto.getPayload().contains(createTestProject()));
	}
	
	@Test 
	public void testGetAllProjectMessage() {
		assertRest.assertDtoMessage("5 projects found", "/projects", type);
	}
	
	

	ProjectDto createTestProject() {
		LocalDate start = LocalDate.of(2019, Month.SEPTEMBER, 26);
		LocalDate end = LocalDate.of(2019, Month.OCTOBER, 2);

		ProjectDto project = new ProjectDto(1, "Babell", start, end);
		return project;
	}

}
