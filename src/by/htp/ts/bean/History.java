package by.htp.ts.bean;

import java.io.Serializable;

public class History implements Serializable {

	private static final long serialVersionUID = 7406977169691162909L;

	private int number;
	private String provisionalDiagnosis;
	private String currentDiagnosis;
	private String finalDiagnosis;
	private String status;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}

	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}

	public String getCurrentDiagnosis() {
		return currentDiagnosis;
	}

	public void setCurrentDiagnosis(String currentDiagnosis) {
		this.currentDiagnosis = currentDiagnosis;
	}

	public String getFinalDiagnosis() {
		return finalDiagnosis;
	}

	public void setFinalDiagnosis(String finalDiagnosis) {
		this.finalDiagnosis = finalDiagnosis;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentDiagnosis == null) ? 0 : currentDiagnosis.hashCode());
		result = prime * result + ((finalDiagnosis == null) ? 0 : finalDiagnosis.hashCode());
		result = prime * result + number;
		result = prime * result + ((provisionalDiagnosis == null) ? 0 : provisionalDiagnosis.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		History other = (History) obj;
		if (currentDiagnosis == null) {
			if (other.currentDiagnosis != null)
				return false;
		} else if (!currentDiagnosis.equals(other.currentDiagnosis))
			return false;
		if (finalDiagnosis == null) {
			if (other.finalDiagnosis != null)
				return false;
		} else if (!finalDiagnosis.equals(other.finalDiagnosis))
			return false;
		if (number != other.number)
			return false;
		if (provisionalDiagnosis == null) {
			if (other.provisionalDiagnosis != null)
				return false;
		} else if (!provisionalDiagnosis.equals(other.provisionalDiagnosis))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "History [number=" + number + ", provisionalDiagnosis=" + provisionalDiagnosis + ", currentDiagnosis="
				+ currentDiagnosis + ", finalDiagnosis=" + finalDiagnosis + ", status=" + status + "]";
	}

}
