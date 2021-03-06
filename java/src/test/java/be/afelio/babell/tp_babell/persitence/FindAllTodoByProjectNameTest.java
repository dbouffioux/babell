package be.afelio.babell.tp_babell.persitence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import be.afelio.babell.tp_babell.api.dto.project.ProjectWithTodoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindAllTodoByProjectNameTest {
	
@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		TodoDto expected = createTodoTest();
		ProjectWithTodoDto actual = repository.findAllTodoByProjectName("Test");
		assertNotNull(actual);
		assertTrue(actual.getTodoDtoList().contains(expected));
		
		
	}
	
	TodoDto createTodoTest() {
		return new TodoDto(2, "test", "test description", false, false);
	}

}
