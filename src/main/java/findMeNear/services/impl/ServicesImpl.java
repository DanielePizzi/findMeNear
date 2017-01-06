package findMeNear.services.impl;

import org.apache.log4j.Logger;

import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.daoFactory.DAOFactory;
import findMeNear.persistent.entity.User;
import findMeNear.services.IServices;

public class ServicesImpl implements IServices{
	
	private static final String CLASS = "ServicesImpl";
	
	Logger logger = Logger.getLogger("FINDMENEAR");
	
	DAOFactory mysqlDAOfactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
	
	public void registrazione(String name, String email, String password){
		String method = "registrazione";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = new User();
		user.setNome(name);
		user.setEmail(email);
		user.setPassword(password);
		userDAO.addUser(user);
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
	}
	
	
	public User login(String email){
		String method = "login";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		User user = userDAO.getUser(email);
		
		logger.debug(String.format("%s - %s:: user[%s]",CLASS,method,user.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return user;
	}


	public boolean isUserExist(String email, String name) {
		String method = "isUserExist";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = userDAO.getUser(email);
		
		if(user != null && !name.equalsIgnoreCase(user.getNome())){
			
			logger.debug(String.format("%s - %s:: l'utente esiste [%s]",CLASS,method,user.toString()));
			logger.debug(String.format("%s - %s:: return true",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return true;
		}
		
		logger.debug(String.format("%s - %s:: l'utente non esiste [%s]",CLASS,method,user.toString()));
		logger.debug(String.format("%s - %s:: return false",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return false;
		
	}
}
