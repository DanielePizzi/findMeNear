package findMeNear.persistent.daoFactory;

import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.dao.impl.MySQLUserDAO;

public class MySQLDbDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {	
		return new MySQLUserDAO();
	}

}
