package findMeNear.model.request;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class GetPointRequest {
	
	@NotBlank
	@NotEmpty
	String username;
	@NotBlank
	@NotEmpty
	String categoria;
	@NotBlank
	@NotEmpty
	String latitudine;
	@NotBlank
	@NotEmpty
	String longitudine;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	
}
