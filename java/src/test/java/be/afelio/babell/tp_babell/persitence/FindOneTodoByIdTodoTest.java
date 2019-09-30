package be.afelio.babell.tp_babell.persitence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.babell.tp_babell.api.dto.TodoDto;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;



@RunWith(SpringRunner.class)
@SpringBootTest()
public class FindOneTodoByIdTodoTest {
	
@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		TodoDto expected = createTodoTest();
		TodoDto actual = repository.findOneTodoById(2);
		assertEquals(expected, actual);
	}
	
	TodoDto createTodoTest() {
		return new TodoDto(2, "test", "test description", false, false);
	}

}
