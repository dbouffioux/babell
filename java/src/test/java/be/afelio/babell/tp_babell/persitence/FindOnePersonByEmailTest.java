/*package be.afelio.babell.tp_babell.persitence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.afelio.babell.tp_babell.api.dto.PersonDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDto;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class FindOnePersonByEmailTest {
	
@Autowired ApplicationRepository repository;
	
	@Test
	public void test() {
		PersonDto expected = createTotoTitiForTest();
		PersonDto actual = repository.findOneByEmail("toto@mail.be");
	}

	PersonDto createTotoTitiForTest() {
		return new PersonDto("Toto", "Titi", "toto@mail.be");
		
	}
}
*/