package findMeNear.services.impl;

import org.apache.log4j.Logger;

import findMeNear.persistent.dao.PointDAO;
import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.daoFactory.DAOFactory;
import findMeNear.persistent.entity.Point;
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


	@Override
	public void savePoint(String username, String nome, String citta, String stato, String lat, String lng, String tipo, String descrizione) {
		String method = "savePoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::  - parametri in INPUT - inizio elenco",CLASS,method));
		logger.debug(String.format("%s - %s::username[%s]",CLASS,method,username));
		logger.debug(String.format("%s - %s::nome[%s]",CLASS,method,nome));
		logger.debug(String.format("%s - %s::citta[%s]",CLASS,method,citta));
		logger.debug(String.format("%s - %s::stato[%s]",CLASS,method,stato));
		logger.debug(String.format("%s - %s::lat[%s]",CLASS,method,lat));
		logger.debug(String.format("%s - %s::lng[%s]",CLASS,method,lng));
		logger.debug(String.format("%s - %s::tipo[%s]",CLASS,method,tipo));
		logger.debug(String.format("%s - %s::descrizione[%s]",CLASS,method,descrizione));
		logger.debug(String.format("%s - %s::  - parametri in INPUT - fine elenco",CLASS,method));
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		
		Point point = new Point();
		
		point.setNome(nome);
		point.setCitta(citta);
		point.setLat(lat);
		point.setLng(lng);
		point.setStato(stato);
		point.setTipo(tipo);
		point.setDescrizione(descrizione);
		
		try{
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db",CLASS,method));
			pointDAO.addPoint(point);			
		}catch(Exception e){
			logger.debug(String.format("%s - %s::ERROR[%s]",CLASS,method,e));
		}
		logger.debug(String.format("%s - %s::punto di interesse registrato correttamente",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
	}
}
