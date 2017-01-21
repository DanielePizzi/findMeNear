package findMeNear.model.response;

import java.util.List;
import java.util.Map;

public class GetPointResponse extends StatusResponse {
	
	
	List<Map> pointOfInterest;

	public List getPointOfInterest() {
		return pointOfInterest;
	}

	public void setPointOfInterest(List pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}

	@Override
	public String toString() {
		return "GetPointResponse [pointOfInterest=" + pointOfInterest
				+ ", esito=" + esito + ", descrizione=" + descrizione
				+ ", getPointOfInterest()=" + getPointOfInterest()
				+ ", isEsito()=" + isEsito() + ", getDescrizione()="
				+ getDescrizione() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	

}
