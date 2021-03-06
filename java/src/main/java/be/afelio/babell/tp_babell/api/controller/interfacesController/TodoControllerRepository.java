package be.afelio.babell.tp_babell.api.controller.interfacesController;


import be.afelio.babell.tp_babell.api.dto.project.ProjectWithTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.CreateTodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.api.dto.todo.UpdateTodoDto;

import java.util.List;

public interface TodoControllerRepository {
    ProjectWithTodoDto findAllTodoByProjectName(String projectName);

    TodoDto findOneTodoById(int todoId);

    void createTodo(CreateTodoDto createTodoDto, String projectName);

    void updateTodo(UpdateTodoDto updateTodoDto);

    void deleteDto(String projectName, String todoName);
}
