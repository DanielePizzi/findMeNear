package findMeNear.services.impl;

import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.daoFactory.DAOFactory;
import findMeNear.persistent.entity.User;
import findMeNear.services.IServices;

public class ServicesImpl implements IServices{
	
	public void registrazione(String name, String email, String password){
		DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = new User();
		user.setNome(name);
		user.setEmail(email);
		user.setPassword(password);
		userDAO.addUser(user);
	}
	
	
	public User login(String email){
		DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		return userDAO.getUser(email);
	}


	public boolean isEmailexist(String email) {
		DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		if(userDAO.getUser(email)!=null){
			return true;
		}
		return false;
	}
}
