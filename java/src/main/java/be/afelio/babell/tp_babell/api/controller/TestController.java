package be.afelio.babell.tp_babell.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	// GET http://localhost:8080/babell/test
	@GetMapping(value="test", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> tester() {
		return ResponseEntity.ok("connection réussie !");
	}
}
