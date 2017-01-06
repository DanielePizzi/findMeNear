package findMeNear.persistent.dao.impl;


import org.springframework.transaction.annotation.Transactional;

import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.entity.User;
import findMeNear.utils.CryptPassword;


public class MySQLUserDAO extends SessionFactoryHibernate implements UserDAO{
	
	@Transactional
	public void addUser(User user) {
		user.setPassword(CryptPassword.cryptWithMD5(user.getPassword()));
        getSession().save(user);
        getSession().flush();        	
	}

	public User getUser(String email) {
		return (User) getSession().createQuery("from User u where u.email = :email")
				.setParameter("email", email).uniqueResult();
	}
	
}
