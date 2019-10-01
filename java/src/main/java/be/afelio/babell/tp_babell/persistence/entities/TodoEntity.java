package be.afelio.babell.tp_babell.persistence.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Todo")
@Table(name = "todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_todo")
    private Integer id;
    private String name;
    private Integer estimation;
    private String description;
    @Column(name = "in_progress")
    private boolean inProgress;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectEntity project;
    /*
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="todo_person",
            joinColumns=@JoinColumn(name="id_todo"),
            inverseJoinColumns =@JoinColumn(name="id_person"))
    private List<PersonEntity> listPerson;
    */


    public TodoEntity() {
    }

    public TodoEntity(String name, String description, boolean inProgress, boolean done, ProjectEntity project) {
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

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoEntity that = (TodoEntity) o;
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
