package be.afelio.babell.tp_babell.api.controller;


import be.afelio.babell.tp_babell.api.controller.interfacesController.TodoControllerRepository;
import be.afelio.babell.tp_babell.api.dto.project.ProjectWithTodoDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDto;
import be.afelio.babell.tp_babell.api.dto.response.ResponseDtoStatus;
import be.afelio.babell.tp_babell.api.dto.todo.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.UpdateTodoDto;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedTodoException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import be.afelio.babell.tp_babell.persistence.exceptions.ProjectNotFoundException;
import be.afelio.babell.tp_babell.persistence.exceptions.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping(value = "/todoproject")
public class TodoController {


    @Autowired
    TodoControllerRepository repository;

    @GetMapping(value = "{projectName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<ProjectWithTodoDto>> findOne(
            @PathVariable("projectName") String projectName) {
        ResponseDto<ProjectWithTodoDto> responseDto;
        try {
            ProjectWithTodoDto projectWithTodoDto = repository.findAllTodoByProjectName(projectName);
            if (projectWithTodoDto == null) {
                responseDto = new ResponseDto<ProjectWithTodoDto>(ResponseDtoStatus.FAILURE, "todo not found");
            } else {
                responseDto = new ResponseDto<ProjectWithTodoDto>(ResponseDtoStatus.SUCCESS, projectWithTodoDto.getTodoDtoList().size() + " todos found");
                responseDto.setPayload(projectWithTodoDto);
            }
        } catch (ProjectNotFoundException e) {
            responseDto = new ResponseDto<ProjectWithTodoDto>(ResponseDtoStatus.FAILURE, "project not found");
            e.printStackTrace();
        } catch (Exception e) {
            responseDto = new ResponseDto<ProjectWithTodoDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(value = "{projectName}/{todoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<TodoDto>> findOne(
            @PathVariable("projectName") String projectName,
            @PathVariable("todoId") int todoId) {
        ResponseDto<TodoDto> responseDto;
        try {
            TodoDto todoDto = repository.findOneTodoById(todoId);
            if (todoDto == null) {
                responseDto = new ResponseDto<TodoDto>(ResponseDtoStatus.FAILURE, "todo not found");
            } else {
                responseDto = new ResponseDto<TodoDto>(ResponseDtoStatus.SUCCESS, " todo found");
                responseDto.setPayload(todoDto);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<TodoDto>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(value = "{projectName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> createTodo(
            @RequestBody CreateTodoDto createTodoDto,
            @PathVariable("projectName") String projectName) {
        ResponseDto<Void> responseDto;

        try {
            repository.createTodo(createTodoDto, projectName);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "todo created");
        } catch (InvalidCreateParametersException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "invalid create parameters");
        } catch (DuplicatedTodoException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "duplicated todo");
        } catch (ProjectNotFoundException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "project not found");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> updateTodo(
            @RequestBody UpdateTodoDto updateTodoDto) {
        ResponseDto<Void> responseDto = null;
        try {
            repository.updateTodo(updateTodoDto);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "todo updated");
        } catch (TodoNotFoundException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "todo not found");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
            e.printStackTrace();
        }

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping(value = "{projectName}/{todoName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> deleteTodo(
            @PathVariable("projectName") String projectName,
            @PathVariable("todoName") String todoName) {
        ResponseDto<Void> responseDto;

        try {
            repository.deleteDto(projectName, todoName);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "todo deleted");
        } catch (TodoNotFoundException e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "todo not found");
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }
}
