package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.dto.*;
import be.afelio.babell.tp_babell.persistence.ApplicationRepository;
import be.afelio.babell.tp_babell.persistence.exceptions.DuplicatedTodoException;
import be.afelio.babell.tp_babell.persistence.exceptions.InvalidCreateParametersException;
import be.afelio.babell.tp_babell.persistence.exceptions.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/todoproject")
public class TodoController {


    @Autowired
    ApplicationRepository repository;

    @GetMapping(value = "{projectName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<List<TodoDto>>> findOne(
            @PathVariable("projectName") String projectName) {
        ResponseDto<List<TodoDto>> responseDto;
        try {
            List<TodoDto> todoDtoList = repository.findAllTodoByProjectName(projectName);
            if (todoDtoList == null) {
                responseDto = new ResponseDto<List<TodoDto>>(ResponseDtoStatus.FAILURE, "todo not found");
            } else {
                responseDto = new ResponseDto<List<TodoDto>>(ResponseDtoStatus.SUCCESS, todoDtoList.size() + " todos found");
                responseDto.setPayload(todoDtoList);
            }
        } catch (Exception e) {
            responseDto = new ResponseDto<List<TodoDto>>(ResponseDtoStatus.FAILURE, "unexpected exception");
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
        } catch (Exception e) {
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.FAILURE, "unexpected exception");
        }

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(value = "{projectName}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<Void>> updateTodo(
            @PathVariable("projectName") String projectName,
            @RequestBody UpdateTodoDto updateTodoDto) {
        ResponseDto<Void> responseDto = null;

        try {
            repository.updateTodo(updateTodoDto, projectName);
            responseDto = new ResponseDto<Void>(ResponseDtoStatus.SUCCESS, "todo updated");
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
