package findMeNear.persistent.dao;

import findMeNear.persistent.entity.User;

public interface UserDAO {
	public void addUser(User user);
	public User getUser(String email);
}
