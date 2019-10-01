package be.afelio.babell.tp_babell.api.controller;


import be.afelio.babell.tp_babell.api.dto.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.ProjectDto;

import java.util.List;

public interface ProjectControllerRepository {

    List<ProjectDto> findAllProject();

    void createProject(CreateProjectDto createProjectDto);
}
