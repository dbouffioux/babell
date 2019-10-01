package be.afelio.babell.tp_babell.api.dto.todo;

import be.afelio.babell.tp_babell.persistence.entities.TodoEntity;

import java.util.Objects;

public class TodoDto {

    private Integer id;
    private String name;
    private String description;
    private boolean inProgress;
    private boolean done;

    public TodoDto() {
    }

    public TodoDto(Integer id, String name, String description, boolean inProgress, boolean done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inProgress = inProgress;
        this.done = done;
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

    public static TodoDto from(TodoEntity todoEntity) {
        final TodoDto todoDto = new TodoDto(
                todoEntity.getId(),
                todoEntity.getName(),
                todoEntity.getDescription(),
                todoEntity.isInProgress(),
                todoEntity.isDone());
        return todoDto;
    }
    
    

//    @Override
//    public String toString() {
//        return "TodoDto{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", inProgress=" + inProgress +
//                ", done=" + done +
//                '}';
//    }

    @Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", description=" + description + ", inProgress=" + inProgress
				+ ", done=" + done + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoDto todoDto = (TodoDto) o;
        return inProgress == todoDto.inProgress &&
                done == todoDto.done &&
                Objects.equals(id, todoDto.id) &&
                Objects.equals(name, todoDto.name) &&
                Objects.equals(description, todoDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, inProgress, done);
    }
}
