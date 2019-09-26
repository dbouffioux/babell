package be.afelio.babell.tp_babell.api.dto;

import java.sql.Date;

public class ProjectDto {
	
	private int id;
	private String name;
	private Date project_start;
	private Date project_end;
	
	public ProjectDto() {}

	public ProjectDto(int id, String name, Date project_start, Date project_end) {
		super();
		this.id = id;
		this.name = name;
		this.project_start = project_start;
		this.project_end = project_end;
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

	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", name=" + name + ", project_start=" + project_start + ", project_end="
				+ project_end + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project_end == null) ? 0 : project_end.hashCode());
		result = prime * result + ((project_start == null) ? 0 : project_start.hashCode());
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
		if (project_end == null) {
			if (other.project_end != null)
				return false;
		} else if (!project_end.equals(other.project_end))
			return false;
		if (project_start == null) {
			if (other.project_start != null)
				return false;
		} else if (!project_start.equals(other.project_start))
			return false;
		return true;
	}
	
	
	
    
}
