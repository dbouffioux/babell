package be.afelio.babell.tp_babell.api.dto;

import be.afelio.babell.tp_babell.api.utils.LocalDateDeserializer;
import be.afelio.babell.tp_babell.api.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.Objects;

public class CreateProjectDto {
    private String name;
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate projectStart;
    @JsonSerialize(using=LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate projectEnd;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProjectDto that = (CreateProjectDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(projectStart, that.projectStart) &&
                Objects.equals(projectEnd, that.projectEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, projectStart, projectEnd);
    }
}
