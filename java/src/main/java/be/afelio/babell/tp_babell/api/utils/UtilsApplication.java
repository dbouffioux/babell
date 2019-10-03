package be.afelio.babell.tp_babell.api.utils;


import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.TemplatePerson;
import be.afelio.babell.tp_babell.api.dto.person.UpdatePersonDto;
import be.afelio.babell.tp_babell.api.dto.project.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.todo.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import be.afelio.babell.tp_babell.persistence.exceptions.ProjectNotFoundException;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UtilsApplication {
    private final SecureRandom random = new SecureRandom("bonjourcommentvastupetitsel!".getBytes());
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
        return name != null && !name.isBlank()
                && start != null
                && end != null;
    }

    public  TodoEntity generateTodoEntityWithProjectEntity(CreateTodoDto createTodoDto, String projectName) {
            ProjectEntity projectEntity = projectRepository.findOneByNameIgnoreCase(projectName);
            return new TodoEntity(
                    createTodoDto.getName(),
                    createTodoDto.getDescription(),
                    0,
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

    public PersonEntity generatePersonEntity(TemplatePerson templatePerson) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5, random);
        return new PersonEntity(
                templatePerson.getFirstname(),
                templatePerson.getLastname(),
                templatePerson.getEmail(),
                bCryptPasswordEncoder.encode(templatePerson.getPassword()));
    }

    public PersonEntity generatePersonEntity(PersonEntity personEntity,TemplatePerson templatePerson) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        personEntity.setFirstname(templatePerson.getFirstname());
        personEntity.setLastname(templatePerson.getLastname());
        personEntity.setEmail(templatePerson.getEmail());
        personEntity.setPassword(bCryptPasswordEncoder.encode(templatePerson.getPassword()));
        return personEntity;
    }

    public boolean validatePersonCreateParameters(CreatePersonDto createPersonDto) {
        return validPersonParameters(createPersonDto);
    }

    public boolean validatePersonUpdateParameters(UpdatePersonDto updatePersonDto) {
        Integer id = updatePersonDto.getId();
        return validPersonParameters(updatePersonDto) && id!=null;
    }

    private boolean validPersonParameters(TemplatePerson templatePerson) {
        String firstname = templatePerson.getFirstname();
        String lastname = templatePerson.getLastname();
        String email = templatePerson.getEmail();
        String password = templatePerson.getPassword();
        return  firstname != null&& !firstname.isBlank()
               &&lastname != null && !lastname.isBlank()
                && email != null && !email.isBlank()
                && password != null && !password.isBlank();
    }
}
