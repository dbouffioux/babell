package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.dto.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.TodoDto;
import be.afelio.babell.tp_babell.api.dto.UpdateTodoDto;

import java.util.List;

public interface TodoControllerRepository {
    List<TodoDto> findAllTodoByProjectName(String projectName);

    TodoDto findOneTodoById(int todoId);

    void createTodo(CreateTodoDto createTodoDto, String projectName);

    void updateTodo(UpdateTodoDto updateTodoDto);

    void deleteDto(String projectName, String todoName);
}
