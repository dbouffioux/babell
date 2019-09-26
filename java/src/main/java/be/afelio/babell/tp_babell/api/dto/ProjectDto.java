package be.afelio.babell.tp_babell.api.dto;

import be.afelio.babell.tp_babell.api.utils.LocalDateDeserializer;
import be.afelio.babell.tp_babell.api.utils.LocalDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class ProjectDto {
	
	private int id;
	private String name;
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate projectStart;
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate projectEnd;
	
	public ProjectDto() {}

	public ProjectDto(int id, String name, LocalDate projectStart, LocalDate projectEnd) {
		super();
		this.id = id;
		this.name = name;
		this.projectStart = projectStart;
		this.projectEnd = projectEnd;
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

	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", name=" + name + ", projectStart=" + projectStart + ", projectEnd="
				+ projectEnd + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectEnd == null) ? 0 : projectEnd.hashCode());
		result = prime * result + ((projectStart == null) ? 0 : projectStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectDto other = (ProjectDto) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectEnd == null) {
			if (other.projectEnd != null)
				return false;
		} else if (!projectEnd.equals(other.projectEnd))
			return false;
		if (projectStart == null) {
			if (other.projectStart != null)
				return false;
		} else if (!projectStart.equals(other.projectStart))
			return false;
		return true;
	}

	
}
