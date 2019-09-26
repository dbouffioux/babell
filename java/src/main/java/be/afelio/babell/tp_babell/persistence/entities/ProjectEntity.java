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
    @Column(name = "project_start")
    private LocalDate projectStart;
    @Column(name = "project_end")
    private LocalDate projectEnd;


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
