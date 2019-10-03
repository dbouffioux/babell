package be.afelio.babell.tp_babell.test_utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.jwt.model.JwtResponse;

@Component
public class AssertRest {

	@Autowired
	TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	private String token;

	/**
	 * pour s'authentifier
	 * 
	 * @return
	 */
	public String getToken() {

		if (token == null) { // lazyCreation pour ne le créer qu'une seule fois et on en le crée que si on en
								// a besoin.
			String btoa = Base64.getEncoder().encodeToString("delphine@mail.be:testenligne123".getBytes());
			RequestEntity<String> requestEntity = RequestEntity.post(URI.create("/login"))
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					// .body("{\"username\":\"delphine@mail.be\",\"password\":\"1234\"}");
					.body("btoa=" + btoa);
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
			assertEquals(200, response.getStatusCodeValue());
			String json = response.getBody();
			TypeReference<ResponseDto<JwtResponse>> type = new TypeReference<ResponseDto<JwtResponse>>() {
			};
			ResponseDto<JwtResponse> responseDto = null;
			responseDto = jsonToObject(json, type);
			token = responseDto.getPayload().getToken();
		}

		return token;

	}

	/**
	 * méthode qui permet de vérifier le code de retour d'une requete Http.
	 * 
	 * @param path => URL
	 * @param code => code attendu
	 */
	public void assertReturnCode(String path, int code) {

		ResponseEntity<String> response = getResponseEntity(path);
		// response => je recois la réponse du serveur a la requete
		assertEquals(code, response.getStatusCodeValue());
		// soit il ne fait rien, soit il souleve une exception si la réponse ne
		// correspond pas au code 200
	}

	/**
	 * envoie une requete et retourne la réponse sous forme de ResponseEntity
	 * 
	 * @param path => URL
	 * @return
	 */
	private ResponseEntity<String> getResponseEntity(String path) {
		String token = getToken();
		RequestEntity<Void> requestEntity = RequestEntity.get(URI.create(path))
				.header("Authorization", "Bearer " + token).build();

		return restTemplate.exchange(requestEntity, String.class);
	}
	public HttpHeaders getHeaders (){

		String token = getToken();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		return headers;
	}
	
	

	/**
	 * Envoie une requete et renvoie la réponse sous forme d'une réponse Dto de T
	 * 
	 * @param path => URL
	 * @param type => Le type utilisé par Json
	 * @return
	 */
	public <T> ResponseDto<T> getDto(String path, TypeReference<ResponseDto<T>> type) {

		ResponseEntity<String> response = getResponseEntity(path);
		String json = response.getBody(); // on recupere le body de la réponse qu'on met dans une string
//		TypeReference<ResponseDto<T>> type = new TypeReference<ResponseDto<T>>() {
//		};
		return jsonToObject(json, type);
	}

	/**
	 * Transforme un objet en JSon et gere les exceptions sous formes d'erreur de
	 * test
	 * 
	 * @param json => Json à transformer
	 * @param type => le type utilisé par Json
	 * @return
	 */
	private <T> ResponseDto<T> jsonToObject(String json, TypeReference<ResponseDto<T>> type) {
		ResponseDto<T> responseDto = null;

		try {
			responseDto = mapper.readValue(json, type);
			System.out.println(responseDto);
		} catch (JsonParseException e) {
			fail("JsonParseException " + e.getMessage());
			// fail faire rater le test (details erreur)
		} catch (JsonMappingException e) {
			fail("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			fail("IOException " + e.getMessage());
		}
		return responseDto;
	}

	/**
	 * test si un statut d'une requete est valide
	 * 
	 * @param status => le statut a tester
	 * @param path   => URL
	 */
	public<T> void assertDtoStatus(ResponseDtoStatus status, String path, TypeReference<ResponseDto<T>> type) {

		assertEquals(status, this.getDto(path, type).getStatus());
	}

	/**
	 * test si un playload est valide
	 * 
	 * @param playLoad => le résultat attendu
	 * @param path     => URL
	 * @param type     => le type utilisé pour déserialiser la réponse
	 */
	public <T> void assertPlayLoad(T playLoad, String path, TypeReference<ResponseDto<T>> type) {

		ResponseDto<T> responseDto = this.getDto(path, type);
		assertEquals(playLoad, responseDto.getPayload());
	}


	
}
