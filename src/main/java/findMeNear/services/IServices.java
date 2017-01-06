package findMeNear.services;

import findMeNear.persistent.entity.User;

public interface IServices {
	public void registrazione(String name, String email, String password);
	public User login(String email);
	public boolean isUserExist(String email, String name);
	public void savePoint(String username, String nome, String citta, String stato, String lat, String lng, String tipo, String descrizione);
}
