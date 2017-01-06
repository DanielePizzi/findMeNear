package findMeNear.persistent.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import findMeNear.persistent.dao.PointDAO;
import findMeNear.persistent.entity.Point;

public class MySQLPointDAO extends SessionFactoryHibernate implements PointDAO  {
	

	@Transactional
	public void addPoint(Point point) {
		 getSession().save(point);
	     getSession().flush(); 
	}

	@Override
	public Point getPoint(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
