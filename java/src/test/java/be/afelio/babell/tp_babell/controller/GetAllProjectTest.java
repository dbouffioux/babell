package be.afelio.babell.tp_babell.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDtoStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllProjectTest {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/todo", String.class);
		assertEquals(200, response.getStatusCodeValue());
		String json = response.getBody();
		
		TypeReference<ResponseDto<List<ProjectDto>>> type = new TypeReference<ResponseDto<List<ProjectDto>>>() {};
		ResponseDto<List<ProjectDto>> responseDto = mapper.readValue(json,  type);
		assertEquals(ResponseDtoStatus.SUCCESS, responseDto.getStatus());
		List<ProjectDto> actual = responseDto.getPayload();
		assertNotNull(responseDto);
		assertEquals(1, actual.size());
	}
	
	/*ProjectDto createTestProject() {
		ProjectDto project = new ProjectDto();
		return project
	}*/

}
