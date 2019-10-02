package be.afelio.babell.tp_babell.api.controller;


import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedEmailException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PersonController {


    @Autowired
    PersonControllerRepository repository;

    @GetMapping(value = "user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<PersonDto>> findOne(
            @PathVariable("email") String email) {
        ResponseDto<PersonDto> responseDto;
        try {
            PersonDto personDto = repository.findOneTodoByEmail(email);
            if (personDto == null) {
                responseDto = new ResponseDto<PersonDto>(ResponseDtoStatus.FAILURE, "person not found");
            } else {
                responseDto = new ResponseDto<PersonDto>(ResponseDtoStatus.SUCCESS, " person found");
                responseDto.setPayload(personDto);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<PersonDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }


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
