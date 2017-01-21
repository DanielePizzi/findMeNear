package findMeNear.services.impl;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.fabric.Response;

import findMeNear.model.response.GetPointResponse;
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
		
		logger.debug(String.format("%s - %s:: l'utente non esiste",CLASS,method));
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
		
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		
		User user = userDAO.getUserName(username);
		
		if(user!=null){
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db per l'utente[%s]",CLASS,method,user.toString()));
		}else{
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db per l'utente[null]",CLASS,method));
		}
		Point point = new Point();
		
		point.setNome(nome);
		point.setCitta(citta);
		point.setLat(lat);
		point.setLng(lng);
		point.setStato(stato);
		point.setTipo(tipo);
		point.setDescrizione(descrizione);
		point.setUser(user);
		
		
		
		try{
			logger.debug(String.format("%s - %s::inserimento punto di interesse nel db",CLASS,method));
			pointDAO.addPoint(point);			
		}catch(Exception e){
			logger.debug(String.format("%s - %s::ERROR[%s]",CLASS,method,e));
		}
		logger.debug(String.format("%s - %s::punto di interesse registrato correttamente",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
	}


	@Override
	public GetPointResponse getPoint(String username, String categoria, double latitudine, double longitudine) {
		String method = "getPoint";
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
		GetPointResponse pointResponse = new GetPointResponse();
		PointDAO pointDAO = mysqlDAOfactory.getPointDAO();
		UserDAO userDAO = mysqlDAOfactory.getUserDAO();
		HashMap<String,Object> pointLocation = null;
		HashMap<String,Object> geometry = null;
		HashMap<String,Object> location = null;
		
		User user = userDAO.getUserName(username);
		
		if(user == null){
			logger.debug(String.format("%s-%s:: user null",CLASS,method));
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("UTENTE NON ESISTENTE");
			return pointResponse;
		}
		
		Point point = pointDAO.getPointNear(user.getId(),categoria, latitudine, longitudine);
		
		if(point == null){
			pointResponse.setEsito(false);
			pointResponse.setDescrizione("NON CI SONO PUNTI DI INTERESSE A TE VICINI");
			logger.debug(String.format("%s - %s::point null",CLASS,method));
			return pointResponse;
		}
		
		location.put("lat", point.getLat());
		location.put("lng", point.getLng());
		geometry.put("location", location);
		pointLocation.put("nome",point.getNome());
		pointLocation.put("citta",point.getCitta());
		pointLocation.put("stato", point.getStato());
		pointLocation.put("tipo", point.getTipo());
		pointLocation.put("descrizione", point.getDescrizione());
		pointLocation.put("geometry",geometry);
		pointResponse.setEsito(true);
		pointResponse.setDescrizione("PUNTO PIU' VICINO TROVATO");
		pointResponse.setPointOfInterest(pointLocation);
		logger.debug(String.format("%s - %s::punto piu' vicino[%s]",CLASS,method,point.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return pointResponse;
	}
	
	
	
	
	
}
