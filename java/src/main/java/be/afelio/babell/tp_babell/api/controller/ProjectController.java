package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
public class ProjectController {

    @Autowired
    ApplicationRepository repository;

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
}
