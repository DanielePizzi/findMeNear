package findMeNear.model.response;

import java.util.Map;

public class GetPointResponse extends StatusResponse {
	
	Map pointOfInterest;

	public Map getPointOfInterest() {
		return pointOfInterest;
	}

	public void setPointOfInterest(Map pointOfInterest) {
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
