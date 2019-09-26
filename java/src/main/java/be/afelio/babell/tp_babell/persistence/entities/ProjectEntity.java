package be.afelio.babell.tp_babell.persistence.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Project")
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue()
    @Column(name = "id_project")
    private Integer id;
    private String name;
    private Date project_start;
    private Date project_end;


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

    public Date getProject_start() {
        return project_start;
    }

    public void setProject_start(Date project_start) {
        this.project_start = project_start;
    }

    public Date getProject_end() {
        return project_end;
    }

    public void setProject_end(Date project_end) {
        this.project_end = project_end;
    }
}
