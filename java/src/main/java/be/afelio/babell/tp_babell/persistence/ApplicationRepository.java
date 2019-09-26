package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.dto.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedNameException;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedProjectException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRepository {

    @Autowired private ProjectRepository projectRepository;


    public List<ProjectDto> findAllProject() {
        List<ProjectDto> projectList = null;
        List<ProjectEntity> projectEntitiesList = projectRepository.findAll();
        return createProjectListDto(projectEntitiesList);
    }

    private List<ProjectDto> createProjectListDto(List<ProjectEntity> projectEntitiesList) {
        List<ProjectDto> projectDtosList = new ArrayList<>();
        for (ProjectEntity project: projectEntitiesList) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());

            projectDto.setProject_start(project.getProjectStart());
            projectDto.setProject_end(project.getProjectEnd());
            projectDtosList.add(projectDto);
        }
        return projectDtosList;
    }

    public void createProject(CreateProjectDto createProjectDto) {
            if (!validateCreateParameters(createProjectDto)) {
                throw new InvalidCreateParametersException();
            }
            if (projectRepository.findOneByNameIgnoreCase(createProjectDto.getName())!= null) {
                throw new DuplicatedProjectException();
            }
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setName(createProjectDto.getName());
            projectEntity.setProject_start(createProjectDto.getProjectStart());
            projectEntity.setProject_end(createProjectDto.getProjectEnd());
            projectRepository.save(projectEntity);
        }

    private boolean validateCreateParameters(CreateProjectDto createProjectDto) {
        String name = createProjectDto.getName();
        LocalDate start = createProjectDto.getProjectStart();
        LocalDate end = createProjectDto.getProjectEnd();
      return !name.isBlank() && name != null
              && start != null
              && end != null;
    }
}

