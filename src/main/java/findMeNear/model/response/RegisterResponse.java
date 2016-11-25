package findMeNear.model.response;

public class RegisterResponse {
	private boolean esito;
	private String token_sessione;
	
	public boolean isEsito() {
		return esito;
	}
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public String getToken_sessione() {
		return token_sessione;
	}
	public void setToken_sessione(String token_sessione) {
		this.token_sessione = token_sessione;
	}
}
