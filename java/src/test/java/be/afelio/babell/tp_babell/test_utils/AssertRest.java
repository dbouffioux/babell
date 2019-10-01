package be.afelio.babell.tp_babell.test_utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.babell.tp_babell.api.dto.PersonDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.dto.TodoDto;

@Component
public class AssertRest {
	
	@Autowired
	TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	public void assertReturnCode (String path, int code) {
		
		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);
		//response => je recois la réponse du serveur a la requete
		assertEquals(code, response.getStatusCodeValue());
		// soit il ne fait rien, soit il souleve une exception si la réponse ne correspond pas au code 200
	}
	
	
	public <T> ResponseDto<T> getDto (String path/*, TypeReference<ResponseDto<T>> type*/) {
		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class); // getForEntity => envoi une requetet au serveur avec la fin de l'url person/... => c'est une requete get
		
		String json = response.getBody(); // on recupere le body de la réponse qu'on met dans une string
		TypeReference<ResponseDto<T>> type = new TypeReference<ResponseDto<T>>() {};
		ResponseDto<T> responseDto = null;
		//System.out.println(json);
		// transformer des objets dans une format texte (json)
		// on va transfomer le json recu en objet de type responseDto....
		
		try {
			responseDto = mapper.readValue(json, type);
		} catch (JsonParseException e) {
			fail("JsonParseException "+ e.getMessage());
			// fail faire rater le test (details erreur)
		} catch (JsonMappingException e) {
			fail("JsonMappingException "+ e.getMessage());
		} catch (IOException e) {
			fail("IOException "+ e.getMessage());
		}
		return responseDto;
	}


	public void assertDtoStatus(ResponseDtoStatus status, String path) {
		assertEquals(status, this.getDto(path).getStatus());
	}


	public <T> void assertPlayLoad(T playLoad, String path) {
		ResponseDto<T> responseDto = this.getDto(path);
		assertEquals(playLoad, responseDto);
	}

}
