package be.afelio.babell.tp_babell.persistence;

import be.afelio.babell.tp_babell.api.dto.*;
import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;
import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedProjectException;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedTodoException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import be.afelio.babell.tp_babell.persistence.exceptions.TodoNotFoundException;
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
        System.out.println("*************************************************************");
        System.out.println("----------------------"+todoRepository.findOneByNameIgnoreCase(createTodoDto.getName()));
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

    public void updateTodo(UpdateTodoDto updateTodoDto) {
    }

    public List<TodoDto> findAllTodoByProjectName(String projectName) {
        List<TodoDto> todoListDto = null;
        return todoListDto;
    }

    public void deleteDto(String projectName, int idTodo) {
        TodoEntity todoEntity = todoRepository.findOneById(idTodo);
        if (todoEntity == null) {
            throw new TodoNotFoundException();
        }
        todoRepository.delete(todoEntity);

    }
}

