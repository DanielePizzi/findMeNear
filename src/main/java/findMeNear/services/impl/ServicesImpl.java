package findMeNear.services.impl;

import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.daoFactory.DAOFactory;
import findMeNear.persistent.entity.User;
import findMeNear.services.IServices;

public class ServicesImpl implements IServices{
	
	DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	
	public void registrazione(String name, String email, String password){
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = new User();
		user.setNome(name);
		user.setEmail(email);
		user.setPassword(password);
		userDAO.addUser(user);
	}
	
	
	public User login(String email){
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		return userDAO.getUser(email);
	}


	public boolean isUserExist(String email, String name) {
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		User user = userDAO.getUser(email);
		if(user != null && !name.equalsIgnoreCase(user.getNome())){
			return true;
		}
		return false;
	}
}
