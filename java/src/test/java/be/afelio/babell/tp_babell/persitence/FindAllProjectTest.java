package be.afelio.babell.tp_babell.persitence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindAllProjectTest {
	
	@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		List<ProjectDto> expected = repository.findAllProject();
		assertNotNull(expected);
		
		ProjectDto projet = createTestProject();
		assertTrue(expected.contains(projet));
		
	}
	
	ProjectDto createTestProject() {
		LocalDate start = LocalDate.of(2019, Month.SEPTEMBER, 26);
		LocalDate end = LocalDate.of(2019, Month.OCTOBER, 2);
		
		ProjectDto project = new ProjectDto(1, "Babell", start, end);
		return project;
	}
	
	

}
