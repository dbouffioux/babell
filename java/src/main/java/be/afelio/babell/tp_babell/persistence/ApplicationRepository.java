package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.controller.ProjectControllerRepository;
import be.afelio.babell.tp_babell.api.controller.TodoControllerRepository;
import be.afelio.babell.tp_babell.api.dto.*;
import be.afelio.babell.tp_babell.api.utils.UtilsApplication;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import be.afelio.babell.tp_babell.persistence.exceptions.*;
import be.afelio.babell.tp_babell.persistence.repositories.PersonRepository;
import be.afelio.babell.tp_babell.persistence.repositories.ProjectRepository;
import be.afelio.babell.tp_babell.persistence.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRepository implements ProjectControllerRepository, TodoControllerRepository {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private TodoRepository todoRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private UtilsApplication utilsApplication;


    public List<ProjectDto> findAllProject() {
        List<ProjectEntity> projectEntitiesList = projectRepository.findAll();
        return utilsApplication.createProjectListDto(projectEntitiesList);
    }



    public void createProject(CreateProjectDto createProjectDto) {
            if (!utilsApplication.validateProjectCreateParameters(createProjectDto)) {
                throw new InvalidCreateParametersException();
            }
            if (projectRepository.findOneByNameIgnoreCase(createProjectDto.getName())!= null) {
                throw new DuplicatedProjectException();
            }
        projectRepository.save(utilsApplication.generateProjectEntity(createProjectDto));
        }



    public void createTodo(CreateTodoDto createTodoDto, String projectName) {
        if(!validateTodoCreateParameters(createTodoDto)){
            throw new InvalidCreateParametersException();
        }
        if(todoRepository.findOneByNameIgnoreCase(createTodoDto.getName())!=null){
            throw new DuplicatedTodoException();
        }
        todoRepository.save(utilsApplication.generateProjectEntity(createTodoDto, projectName));
    }

    private boolean validateTodoCreateParameters(CreateTodoDto createTodoDto) {
        String name = createTodoDto.getName();
        String description = createTodoDto.getDescription();
        return name!=null && !name.isBlank()
                && description != null && !description.isBlank();
    }

    public void updateTodo(UpdateTodoDto updateTodoDto) {
        TodoEntity todoEntity = todoRepository.findOneByNameIgnoreCase(updateTodoDto.getName());
        if(todoEntity ==null){
            throw new TodoNotFoundException();
        }if(todoEntity.getName().equals(updateTodoDto.getName())){
            todoEntity.setName(updateTodoDto.getName());
        }if(todoEntity.getDescription().equals(updateTodoDto.getDescription())){
            todoEntity.setDescription(updateTodoDto.getDescription());
        }if(todoEntity.isInProgress()!= updateTodoDto.isInProgress()){
            todoEntity.setInProgress(updateTodoDto.isInProgress());
        }if(todoEntity.isDone()!= updateTodoDto.isDone()){
            todoEntity.setDone(updateTodoDto.isDone());
        }if(todoEntity.getEstimation()== null){
            todoEntity.setEstimation(updateTodoDto.getEstimation());
        }if(todoEntity.getProject().getId() != updateTodoDto.getIdProject()) {
        	todoEntity.setProject(projectRepository.findOneById(updateTodoDto.getIdProject()));
        }
        todoRepository.save(todoEntity);
    }

    public List<TodoDto> findAllTodoByProjectName(String projectName) {
        List<TodoDto> todoListDto = null;
        List<TodoEntity> todoEntityList = todoRepository.findAllByProjectName(projectName);
        return createTodoDtoList(todoEntityList);
    }

    private List<TodoDto> createTodoDtoList(List<TodoEntity> todoEntityList) {
        List<TodoDto> todoDtoList = new ArrayList<>();
        for (TodoEntity todoEntity: todoEntityList) {
        todoDtoList.add(TodoDto.from(todoEntity));
        }
        return todoDtoList;
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
        if(!validatePersonCreateParameters(createPersonDto)){
            throw new InvalidCreateParametersException();
        }
        if(personRepository.findOneByEmail(createPersonDto.getEmail())!= null){
            throw new DuplicatedEmailException();
        }


    }

    private boolean validatePersonCreateParameters(CreatePersonDto createPersonDto) {
        return  false;
    }
}

