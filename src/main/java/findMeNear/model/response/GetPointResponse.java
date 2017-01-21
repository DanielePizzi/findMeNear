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

}
