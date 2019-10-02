package be.afelio.babell.tp_babell.api.controller;


import be.afelio.babell.tp_babell.api.controller.interfacesController.ProjectControllerRepository;
import be.afelio.babell.tp_babell.api.dto.project.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedProjectException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    ProjectControllerRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<ProjectDto>>> findAll() {
        ResponseDto<List<ProjectDto>> responseDto = null;
        try {
            List<ProjectDto> listProjectDto = repository.findAllProject();
            if (listProjectDto != null) {
                responseDto = new ResponseDto<List<ProjectDto>>(ResponseDtoStatus.SUCCESS, listProjectDto.size() + " projects found");
                responseDto.setPayload(listProjectDto);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<List<ProjectDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> createCustomer(@RequestBody CreateProjectDto createProjectDto) {
        ResponseDto<Void> dto = null;

        try {
            repository.createProject(createProjectDto);
            dto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "project created");
        } catch (InvalidCreateParametersException e) {
            dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "invalid create parameters");
        } catch (DuplicatedProjectException e) {
            dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated project");
        } catch (Exception e) {
            dto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(dto);
    }
}
