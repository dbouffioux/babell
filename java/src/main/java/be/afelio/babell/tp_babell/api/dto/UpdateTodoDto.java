package be.afelio.babell.tp_babell.api.dto;

import be.afelio.babell.tp_babell.persistence.entities.ProjectEntity;

import java.util.Objects;

public class UpdateTodoDto {

    private Integer id;
    private String name;
    private String description;
    private boolean inProgress;
    private boolean done;
    private ProjectEntity project;

    public UpdateTodoDto() {
    }

    public UpdateTodoDto(String name, String description, boolean inProgress, boolean done, ProjectEntity project) {
        this.name = name;
        this.description = description;
        this.inProgress = inProgress;
        this.done = done;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateTodoDto that = (UpdateTodoDto) o;
        return inProgress == that.inProgress &&
                done == that.done &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, inProgress, done, project);
    }
}
