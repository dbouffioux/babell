package be.afelio.babell.tp_babell.api.dto.project;

import be.afelio.babell.tp_babell.api.dto.todo.TodoDto;
import be.afelio.babell.tp_babell.api.utils.LocalDateDeserializer;
import be.afelio.babell.tp_babell.api.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProjectWithTodoDto {
    private int id;
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate projectStart;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate projectEnd;
    private List<TodoDto> todoDtoList;

    public ProjectWithTodoDto() {
    }

    public ProjectWithTodoDto(int id, String name, LocalDate projectStart, LocalDate projectEnd, List<TodoDto> todoDtoList) {
        this.id = id;
        this.name = name;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.todoDtoList = todoDtoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(LocalDate projectStart) {
        this.projectStart = projectStart;
    }

    public LocalDate getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(LocalDate projectEnd) {
        this.projectEnd = projectEnd;
    }

    public List<TodoDto> getTodoDtoList() {
        return todoDtoList;
    }

    public void setTodoDtoList(List<TodoDto> todoDtoList) {
        this.todoDtoList = todoDtoList;
    }

    @Override
    public String toString() {
        return "ProjectWithTodoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectStart=" + projectStart +
                ", projectEnd=" + projectEnd +
                ", todoDtoList=" + todoDtoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectWithTodoDto that = (ProjectWithTodoDto) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(projectStart, that.projectStart) &&
                Objects.equals(projectEnd, that.projectEnd) &&
                Objects.equals(todoDtoList, that.todoDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projectStart, projectEnd, todoDtoList);
    }
}
