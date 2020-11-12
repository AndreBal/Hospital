package by.htp.ts.bean;

import java.io.Serializable;
import java.util.Date;

public class Treatment implements Serializable {

	private static final long serialVersionUID = 4469943323917118300L;

	private int id;
	private String type;
	private boolean completed;
	private String description;
	private Date performedDate;
	private User performer;
	private User appointer;
	
	public Treatment(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPerformedDate() {
		return performedDate;
	}

	public void setPerformedDate(Date performedDate) {
		this.performedDate = performedDate;
	}

	public User getPerformer() {
		return performer;
	}

	public void setPerformer(User performer) {
		this.performer = performer;
	}

	public User getAppointer() {
		return appointer;
	}

	public void setAppointer(User appointer) {
		this.appointer = appointer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appointer == null) ? 0 : appointer.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((performer == null) ? 0 : performer.hashCode());
		result = prime * result + id;
		result = prime * result + ((performedDate == null) ? 0 : performedDate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Treatment other = (Treatment) obj;
		if (appointer == null) {
			if (other.appointer != null)
				return false;
		} else if (!appointer.equals(other.appointer))
			return false;
		if (completed != other.completed)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (id != other.id)
			return false;
		if (performedDate == null) {
			if (other.performedDate != null)
				return false;
		} else if (!performedDate.equals(other.performedDate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", type=" + type + ", completed=" + completed + ", description=" + description
				+ ", performedDate=" + performedDate + ", executor=" + performer + ", appointer=" + appointer + "]";
	}
	
}
