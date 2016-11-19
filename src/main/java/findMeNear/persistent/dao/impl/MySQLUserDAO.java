package findMeNear.persistent.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import findMeNear.hibernate.config.HibernateUtil;
import findMeNear.persistent.dao.UserDAO;
import findMeNear.persistent.entity.User;
import findMeNear.utils.CryptPassword;


public class MySQLUserDAO implements UserDAO{
	
	@Autowired
    private SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	private Session getSession(){
		return sessionFactory.openSession();
	}
	
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
