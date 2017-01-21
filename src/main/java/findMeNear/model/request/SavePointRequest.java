package findMeNear.model.request;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class SavePointRequest{
	
	@NotEmpty
	@NotNull
	String username;

	Map pointOfInterest;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map getPointOfInterest() {
		return pointOfInterest;
	}
	public void setPointOfInterest(Map pointOfInterest) {
		this.pointOfInterest = pointOfInterest;
	}
	@Override
	public String toString() {
		return "SavePointRequest [username=" + username + ", pointOfInterest="
				+ pointOfInterest + "]";
	} 
}
