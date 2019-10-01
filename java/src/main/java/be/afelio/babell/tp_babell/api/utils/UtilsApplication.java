package be.afelio.babell.tp_babell.api.utils;


import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.project.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.todo.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
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
    @Autowired
    ProjectRepository projectRepository;

    public List<ProjectDto> createProjectListDto(List<ProjectEntity> projectEntitiesList) {
        List<ProjectDto> projectDtosList = new ArrayList<>();
        for (ProjectEntity project : projectEntitiesList) {
            projectDtosList.add(ProjectDto.from(project));
        }
        return projectDtosList;
    }

    public ProjectEntity generateProjectEntity(CreateProjectDto createProjectDto) {
       return new ProjectEntity(
        createProjectDto.getName(),
        createProjectDto.getProjectStart(),
        createProjectDto.getProjectEnd());
    }

    public boolean validateProjectCreateParameters(CreateProjectDto createProjectDto) {
        String name = createProjectDto.getName();
        LocalDate start = createProjectDto.getProjectStart();
        LocalDate end = createProjectDto.getProjectEnd();
        return !name.isBlank() && name != null
                && start != null
                && end != null;
    }

    public TodoEntity generateProjectEntity(CreateTodoDto createTodoDto, String projectName) {

        ProjectEntity projectEntity = projectRepository.findOneByNameIgnoreCase(projectName);
        return new TodoEntity(
                createTodoDto.getName(),
                createTodoDto.getDescription(),
                false,
                false,
                projectEntity);
    }

    public boolean validateTodoCreateParameters(CreateTodoDto createTodoDto) {
        String name = createTodoDto.getName();
        String description = createTodoDto.getDescription();
        return name != null && !name.isBlank()
                && description != null && !description.isBlank();
    }

    public List<TodoDto> createTodoDtoList(List<TodoEntity> todoEntityList) {
        List<TodoDto> todoDtoList = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntityList) {
            todoDtoList.add(TodoDto.from(todoEntity));
        }
        return todoDtoList;
    }

    public PersonEntity generatePersonEntity(CreatePersonDto createPersonDto) {
        return new PersonEntity(
                createPersonDto.getFirstname(),
                createPersonDto.getLastname(),
                createPersonDto.getEmail(),
                createPersonDto.getPassword());
    }

    public PersonDto createPersonDto(PersonEntity personEntity) {
        return new PersonDto(
                personEntity.getFirstname(),
                personEntity.getLastname(),
                personEntity.getEmail());
    }
}
