package findMeNear.persistent.daoFactory;

import findMeNear.persistent.dao.UserDAO;

public abstract class DAOFactory {
	
	public static final String MYSQL = "mysql";

	public abstract UserDAO getUserDAO();
	 
	 
	public static DAOFactory getDAOFactory(String tipo) {
	  switch (tipo) {
	    case MYSQL: 
	        return new MySQLDbDAOFactory();
	    default: 
	    	return null;
	    }	
	}
}
