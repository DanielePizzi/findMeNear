package findMeNear.model.response;

public class LoginResponse {
	private boolean esito;
	private String token_sessione;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
