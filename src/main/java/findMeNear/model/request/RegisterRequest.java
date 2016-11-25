package findMeNear.model.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


public class RegisterRequest {
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String password;
	@NotBlank
	@NotNull
	private String email;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
