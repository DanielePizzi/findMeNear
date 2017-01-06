package findMeNear.model.response;

public class RegisterResponse extends StatusResponse {
	private String token_sessione;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken_sessione() {
		return token_sessione;
	}
	public void setToken_sessione(String token_sessione) {
		this.token_sessione = token_sessione;
	}
	@Override
	public String toString() {
		return "RegisterResponse [esito=" + esito + ", token_sessione="
				+ token_sessione + ", nome=" + nome + "]";
	}
	
	
}
