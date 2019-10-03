package be.afelio.babell.tp_babell.api.controller;


import be.afelio.babell.tp_babell.api.controller.interfacesController.PersonControllerRepository;
import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.person.UpdatePersonDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.jwt.model.JwtResponse;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedEmailException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import be.afelio.babell.tp_babell.persistence.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PersonController {


    @Autowired
    PersonControllerRepository repository;
    @Autowired
    JwtAuthenticationController jwtAuthenticationController;

    @GetMapping(value = "person/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<PersonDto>> findOne(
            @PathVariable("email") String email) {
        ResponseDto<PersonDto> responseDto;
        try {
            PersonDto personDto = repository.findOnePersonByEmail(email);
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
@GetMapping(value = "person/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<PersonDto>> findOneByFirstnameAndLastname(
            @PathVariable("firstname") String firstname,
            @PathVariable("lastname") String lastname) {
        ResponseDto<PersonDto> responseDto;
        try {
            PersonDto personDto = repository.findOneByFirstnameAndLastname(firstname, lastname);
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
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated email");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }
    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<JwtResponse>> updatePerson(
            HttpServletRequest request,
            @RequestBody UpdatePersonDto updatePersonDto) {

        ResponseDto<JwtResponse> responseDto = new ResponseDto<>();

        try {
            repository.updatePersonDto(updatePersonDto);
            responseDto = jwtAuthenticationController.getTokenWithJwtResponse(request, updatePersonDto.getEmail(), updatePersonDto.getPassword());
            responseDto.setMessage("person updated");
        } catch (InvalidCreateParametersException e) {
            responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "invalid update parameters");
        } catch (PersonNotFoundException e) {
            responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "person not found");
        } catch (DuplicatedEmailException e) {
            responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "duplicated email");
        } catch (Exception e) {
            responseDto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping(value = "/person/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> deleteTodo(
            @PathVariable("email") String email) {
        ResponseDto<Void> responseDto;

        try {
            repository.deletePerson(email);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "person deleted");
        } catch (PersonNotFoundException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "person not found");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }
}
