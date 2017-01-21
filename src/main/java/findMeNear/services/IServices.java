package findMeNear.services;

import findMeNear.model.response.GetPointResponse;
import findMeNear.persistent.entity.User;

public interface IServices {
	public void registrazione(String name, String email, String password);
	public User login(String email);
	public boolean isUserExist(String email, String name);
	public void savePoint(String username, String nome, String citta, String stato, String lat, String lng, String tipo, String descrizione);
	public GetPointResponse getPoint(String username, String categoria, double lotitudine, double langitudine);
}
