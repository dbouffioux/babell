package be.afelio.babell.tp_babell.api.dto.todo;


public class UpdateTodoDto {

    private int id;
    private String name;
    private String description;
    private int estimation;
    private boolean inProgress;
    private boolean done;
    private Integer idProject;

    public UpdateTodoDto() {
    }

	public UpdateTodoDto(String name, String description, int estimation, boolean inProgress, boolean done,
			Integer idProject) {
		super();
		this.name = name;
		this.description = description;
		this.estimation = estimation;
		this.inProgress = inProgress;
		this.done = done;
		this.idProject = idProject;
	}

	@Override
	public String toString() {
		return "UpdateTodoDto [name=" + name + ", description=" + description + ", estimation=" + estimation
				+ ", inProgress=" + inProgress + ", done=" + done + ", idProject=" + idProject + "]";
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

	public int getEstimation() {
		return estimation;
	}

	public void setEstimation(int estimation) {
		this.estimation = estimation;
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

	public Integer getIdProject() {
		return idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (done ? 1231 : 1237);
		result = prime * result + estimation;
		result = prime * result + ((idProject == null) ? 0 : idProject.hashCode());
		result = prime * result + (inProgress ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UpdateTodoDto other = (UpdateTodoDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (done != other.done)
			return false;
		if (estimation != other.estimation)
			return false;
		if (idProject == null) {
			if (other.idProject != null)
				return false;
		} else if (!idProject.equals(other.idProject))
			return false;
		if (inProgress != other.inProgress)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    


}
