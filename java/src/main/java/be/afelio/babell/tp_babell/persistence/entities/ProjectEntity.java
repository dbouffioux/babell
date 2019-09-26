package be.afelio.babell.tp_babell.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "ProjectInterface")
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue()
    @Column(name = "id_project")
    private Integer id;
    private String name;
    private LocalDate project_start;
    private LocalDate project_end;


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

    public LocalDate getProject_start() {
        return project_start;
    }

    public void setProject_start(LocalDate project_start) {
        this.project_start = project_start;
    }

    public LocalDate getProject_end() {
        return project_end;
    }

    public void setProject_end(LocalDate project_end) {
        this.project_end = project_end;
    }
}
