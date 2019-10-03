package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.controller.interfacesController.PersonControllerRepository;
import be.afelio.babell.tp_babell.api.controller.interfacesController.ProjectControllerRepository;
import be.afelio.babell.tp_babell.api.controller.interfacesController.TodoControllerRepository;
import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.person.UpdatePersonDto;
import be.afelio.babell.tp_babell.api.dto.project.CreateProjectDto;
import be.afelio.babell.tp_babell.api.dto.project.ProjectDto;
import be.afelio.babell.tp_babell.api.dto.todo.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.UpdateTodoDto;
import be.afelio.babell.tp_babell.api.utils.UtilsApplication;
import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import be.afelio.babell.tp_babell.persistence.exceptions.*;
import be.afelio.babell.tp_babell.persistence.repositories.PersonRepository;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import be.afelio.babell.tp_babell.persistence.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRepository implements
        ProjectControllerRepository,
        TodoControllerRepository,
        PersonControllerRepository {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UtilsApplication utilsApplication;


    public List<ProjectDto> findAllProject() {
        List<ProjectEntity> projectEntitiesList = projectRepository.findAll();
        return utilsApplication.createProjectListDto(projectEntitiesList);
    }


    public void createProject(CreateProjectDto createProjectDto) {
        if (!utilsApplication.validateProjectCreateParameters(createProjectDto)) {
            throw new InvalidCreateParametersException();
        }
        if (projectRepository.findOneByNameIgnoreCase(createProjectDto.getName()) != null) {
            throw new DuplicatedProjectException();
        }
        projectRepository.save(utilsApplication.generateProjectEntity(createProjectDto));
    }

    public void createTodo(CreateTodoDto createTodoDto, String projectName) {
        if (!utilsApplication.validateTodoCreateParameters(createTodoDto)) {
            throw new InvalidCreateParametersException();
        }
        if (todoRepository.findOneByNameIgnoreCase(createTodoDto.getName()) != null) {
            throw new DuplicatedTodoException();
        }
        todoRepository.save(utilsApplication.generateTodoEntityWithProjectProjectEntity(createTodoDto, projectName));
    }

    public void updateTodo(UpdateTodoDto updateTodoDto) {
        TodoEntity todoEntity = todoRepository.findOneById(updateTodoDto.getId());
        if (todoEntity == null) {
            throw new TodoNotFoundException();
        }
        if (todoEntity.getName().equals(updateTodoDto.getName())) {
            todoEntity.setName(updateTodoDto.getName());
        }
        if (todoEntity.getDescription().equals(updateTodoDto.getDescription())) {
            todoEntity.setDescription(updateTodoDto.getDescription());
        }
        if (todoEntity.isInProgress() != updateTodoDto.isInProgress()) {
            todoEntity.setInProgress(updateTodoDto.isInProgress());
        }
        if (todoEntity.isDone() != updateTodoDto.isDone()) {
            todoEntity.setDone(updateTodoDto.isDone());
        }
        if (todoEntity.getEstimation() == null) {
            todoEntity.setEstimation(updateTodoDto.getEstimation());
        }
        if (todoEntity.getProject().getId() != updateTodoDto.getIdProject()) {
            todoEntity.setProject(projectRepository.findOneById(updateTodoDto.getIdProject()));
        }
        todoRepository.save(todoEntity);
    }

    public List<TodoDto> findAllTodoByProjectName(String projectName) {
        return utilsApplication.createTodoDtoList(todoRepository.findAllByProjectName(projectName));
    }

    public void deleteDto(String projectName, String todoName) {
        TodoEntity todoEntity = todoRepository.findOneByNameIgnoreCase(todoName);
        if (todoEntity == null) {
            throw new TodoNotFoundException();
        }
        todoRepository.delete(todoEntity);

    }

    public TodoDto findOneTodoById(int todoId) {
        return TodoDto.from(todoRepository.findOneById(todoId));
    }


    public void createPerson(CreatePersonDto createPersonDto) {
        if (!utilsApplication.validatePersonCreateParameters(createPersonDto)) {
            throw new InvalidCreateParametersException();
        }
        if (personRepository.findOneByEmail(createPersonDto.getEmail()) != null) {
            throw new DuplicatedEmailException();
        }
        personRepository.save(utilsApplication.generatePersonEntity(createPersonDto));
    }

    @Override
    public PersonDto findOnePersonByEmail(String email) {
        return PersonDto.from(personRepository.findOneByEmail(email));
    }

    @Override
    public void updatePersonDto(UpdatePersonDto updatePersonDto) {
        PersonEntity personEntity = personRepository.findOneById(updatePersonDto.getId());
        if (!utilsApplication.validatePersonUpdateParameters(updatePersonDto)) {
            throw new InvalidUpdateParametersException();
        }if(personEntity == null) {
            throw new PersonNotFoundException();
        }if(!personEntity.getEmail().equals(updatePersonDto.getEmail())){
            if(personRepository.findOneByEmail(updatePersonDto.getEmail())!= null){
                throw new DuplicatedEmailException();
            }
        }
        else if(personEntity!= null){
            personRepository.save(utilsApplication.generatePersonEntity(personEntity,updatePersonDto));
        }

    }

    @Override
    public void deletePerson(String email) {
        PersonEntity personEntity = personRepository.findOneByEmail(email);
        if (personEntity == null) {
            throw new PersonNotFoundException();
        }
        personRepository.delete(personEntity);

    }

    @Override
    public PersonDto findOneByFirstnameAndLastname(String firstname, String lastname) {
       PersonEntity personEntity = personRepository.findOneByFirstnameIgnoreCaseAndLastnameIgnoreCase(firstname, lastname);
      if(personEntity == null){
          throw new PersonNotFoundException();
      }
        return PersonDto.from(personEntity);
    }


}

