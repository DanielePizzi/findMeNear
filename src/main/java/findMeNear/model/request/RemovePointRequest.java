package findMeNear.model.request;

public class RemovePointRequest {
	
	int idPoint;

	public int getIdPoint() {
		return idPoint;
	}

	public void setIdPoint(int idPoint) {
		this.idPoint = idPoint;
	}

	@Override
	public String toString() {
		return "RemovePointRequest [idPoint=" + idPoint + ", getIdPoint()="
				+ getIdPoint() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
