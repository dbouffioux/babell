package be.afelio.babell.tp_babell.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Project")
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Integer id;
    private String name;
    @Column(name = "projectStart")
    private LocalDate projectStart;
    @Column(name = "projectEnd")
    private LocalDate projectEnd;

    public ProjectEntity() {
    }

    public ProjectEntity(String name, LocalDate projectStart, LocalDate projectEnd) {
        this.name = name;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
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
}
