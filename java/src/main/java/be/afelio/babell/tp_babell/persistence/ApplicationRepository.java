package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        List<ProjectDto> projectDtos = null;
        for (ProjectEntity project: projectEntitiesList) {
            ProjectDto projectDto = new ProjectDto();
            
        }
        return projectDtos;
    }
}
