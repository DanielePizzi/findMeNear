package findMeNear.model.response;

public class StatusResponse {
	boolean esito;
	String descrizione;
	
	public boolean isEsito() {
		return esito;
	}
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "StatusResponse [esito=" + esito + ", descrizione="
				+ descrizione + "]";
	}
	
}
