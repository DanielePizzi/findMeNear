package findMeNear.persistent.daoFactory;

import findMeNear.persistent.dao.PointDAO;
import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.dao.impl.MySQLPointDAO;
import findMeNear.persistent.dao.impl.MySQLUserDAO;

public class MySQLDbDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {	
		return new MySQLUserDAO();
	}

	@Override
	public PointDAO getPointDAO() {
		return new MySQLPointDAO();
	}
	

}
