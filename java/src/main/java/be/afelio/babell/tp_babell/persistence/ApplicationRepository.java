package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.dto.*;
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
public class ApplicationRepository {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private TodoRepository todoRepository;
    @Autowired private PersonRepository personRepository;


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

            projectDto.setProjectStart(project.getProjectStart());
            projectDto.setProjectEnd(project.getProjectEnd());
            projectDtosList.add(projectDto);
        }
        return projectDtosList;
    }

    public void createProject(CreateProjectDto createProjectDto) {
            if (!validateProjectCreateParameters(createProjectDto)) {
                throw new InvalidCreateParametersException();
            }
            if (projectRepository.findOneByNameIgnoreCase(createProjectDto.getName())!= null) {
                throw new DuplicatedProjectException();
            }
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setName(createProjectDto.getName());
            projectEntity.setProjectStart(createProjectDto.getProjectStart());
            projectEntity.setProjectEnd(createProjectDto.getProjectEnd());
            projectRepository.save(projectEntity);
        }

    private boolean validateProjectCreateParameters(CreateProjectDto createProjectDto) {
        String name = createProjectDto.getName();
        LocalDate start = createProjectDto.getProjectStart();
        LocalDate end = createProjectDto.getProjectEnd();
      return !name.isBlank() && name != null
              && start != null
              && end != null;
    }

    public void createTodo(CreateTodoDto createTodoDto, String projectName) {
        if(!validateTodoCreateParameters(createTodoDto)){
            throw new InvalidCreateParametersException();
        }
        if(todoRepository.findOneByNameIgnoreCase(createTodoDto.getName())!=null){
            throw new DuplicatedTodoException();
        }
        ProjectEntity projectEntity = projectRepository.findOneByNameIgnoreCase(projectName);
        TodoEntity todoEntity = new TodoEntity(
                createTodoDto.getName(),
                createTodoDto.getDescription(),
                false,
                false,
                projectEntity);
        todoRepository.save(todoEntity);
    }

    private boolean validateTodoCreateParameters(CreateTodoDto createTodoDto) {
        String name = createTodoDto.getName();
        String description = createTodoDto.getDescription();
        return name!=null && !name.isBlank()
                && description != null && !description.isBlank();
    }

    public void updateTodo(UpdateTodoDto updateTodoDto, String projectName) {
        TodoEntity todoEntity = todoRepository.findOneById(updateTodoDto.getId());
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
        }
        todoRepository.save(todoEntity);
    }

    public List<TodoDto> findAllTodoByProjectName(String projectName) {
        List<TodoDto> todoListDto = null;
        return todoListDto;
    }

    public void deleteDto(String projectName, String todoName) {
        TodoEntity todoEntity = todoRepository.findOneByNameIgnoreCase(todoName);
        if (todoEntity == null) {
            throw new TodoNotFoundException();
        }
        todoRepository.delete(todoEntity);

    }

    public TodoDto findOneTodoById(int todoId) {
        return createTodoDto(todoRepository.findOneById(todoId));
    }

    private TodoDto createTodoDto(TodoEntity todoEntity) {
        TodoDto todoDto = new TodoDto(
                todoEntity.getId(),
                todoEntity.getName(),
                todoEntity.getDescription(),
                todoEntity.isInProgress(),
                todoEntity.isDone());

        return todoDto;
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

