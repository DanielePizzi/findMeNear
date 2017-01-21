package findMeNear.model.response;

import java.util.List;
import java.util.Map;

public class GetPointResponse extends StatusResponse {
	
	
	List<Map> pointOfInterest;
	
	String distanza;

	public List<Map> getPointOfInterest() {
		return pointOfInterest;
	}

	public void setPointOfInterest(List<Map> pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}
	
	public String getDistanza() {
		return distanza;
	}

	public void setDistanza(String distanza) {
		this.distanza = distanza;
	}

	@Override
	public String toString() {
		return "GetPointResponse [pointOfInterest=" + pointOfInterest
				+ ", distanza=" + distanza + ", esito=" + esito
				+ ", descrizione=" + descrizione + ", getPointOfInterest()="
				+ getPointOfInterest() + ", getDistanza()=" + getDistanza()
				+ ", isEsito()=" + isEsito() + ", getDescrizione()="
				+ getDescrizione() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	

}
