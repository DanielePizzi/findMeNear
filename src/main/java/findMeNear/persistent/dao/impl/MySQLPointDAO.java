package findMeNear.persistent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import findMeNear.persistent.dao.PointDAO;
import findMeNear.persistent.entity.Point;
import findMeNear.utils.CalcolaPointNear;

public class MySQLPointDAO extends SessionFactoryHibernate implements PointDAO  {
	

	@Transactional
	public void addPoint(Point point) {
		 getSession().save(point);
	     getSession().flush(); 
	}

	@Override
	@Transactional
	public Point getPointNear(int id,String categoria, double latitudine, double longitudine) {
		@SuppressWarnings("unchecked")
		ArrayList<Point> listResult = ((ArrayList<Point>) getSession().createQuery("SELECT * FROM Point WHERE Tipo = :categoria AND idUser = :id")
				.setParameter("categoria", categoria).setParameter("idUser", id).list());
		if(listResult == null || listResult.isEmpty()) return null;
		return CalcolaPointNear.distanceMin(listResult, latitudine, longitudine);
	}

}
