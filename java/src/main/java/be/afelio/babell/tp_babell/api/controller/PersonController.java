package be.afelio.babell.tp_babell.api.controller;



import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedEmailException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    ApplicationRepository repository;

    @PostMapping(value = "/subscription", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> createPerson(
            @RequestBody CreatePersonDto createPersonDto) {
        ResponseDto<Void> responseDto;

        try {
            repository.createPerson(createPersonDto);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "person created");
        } catch (InvalidCreateParametersException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "invalid create parameters");
        } catch (DuplicatedEmailException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated person");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }
}
