package be.afelio.babell.tp_babell.api.utils;

import be.afelio.babell.tp_babell.api.dto.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.ProjectDto;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UtilsApplication {
    @Autowired ProjectRepository projectRepository;

    public List<ProjectDto> createProjectListDto(List<ProjectEntity> projectEntitiesList) {
        List<ProjectDto> projectDtosList = new ArrayList<>();
        for (ProjectEntity project: projectEntitiesList) {
            projectDtosList.add(ProjectDto.from(project));
        }
        return projectDtosList;
    }
    public ProjectEntity generateProjectEntity(CreateProjectDto createProjectDto){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(createProjectDto.getName());
        projectEntity.setProjectStart(createProjectDto.getProjectStart());
        projectEntity.setProjectEnd(createProjectDto.getProjectEnd());
        return projectEntity;
    }
    public boolean validateProjectCreateParameters(CreateProjectDto createProjectDto) {
        String name = createProjectDto.getName();
        LocalDate start = createProjectDto.getProjectStart();
        LocalDate end = createProjectDto.getProjectEnd();
        return !name.isBlank() && name != null
                && start != null
                && end != null;
    }
    public TodoEntity generateProjectEntity(CreateTodoDto createTodoDto, String projectName){

        ProjectEntity projectEntity = projectRepository.findOneByNameIgnoreCase(projectName);
        TodoEntity todoEntity = new TodoEntity(
                createTodoDto.getName(),
                createTodoDto.getDescription(),
                false,
                false,
                projectEntity);
        return todoEntity;
    }

}
