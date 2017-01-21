package findMeNear.persistent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import findMeNear.model.utils.PointDistance;
import findMeNear.persistent.dao.PointDAO;
import findMeNear.persistent.entity.Point;
import findMeNear.utils.CalcolaPointNear;

public class MySQLPointDAO extends SessionFactoryHibernate implements PointDAO  {
	
	private static final String CLASS = "MySQLPointDAO";
	
	Logger logger = Logger.getLogger("FINDMENEAR");
	

	@Transactional
	public void addPoint(Point point) {
		 getSession().save(point);
	     getSession().flush(); 
	}

	@Override
	@Transactional
	public PointDistance getPointNear(int idUser ,String categoria, double latitudine, double longitudine) {
		@SuppressWarnings("unchecked")
		ArrayList<Point> listResult = ((ArrayList<Point>) getSession().createQuery("FROM Point WHERE Tipo = :categoria AND idUser = :idUser")
				.setParameter("categoria", categoria).setParameter("idUser", idUser).list());
		if(listResult == null || listResult.isEmpty()) return null;
		return CalcolaPointNear.distanceMin(listResult, latitudine, longitudine);
	}

	@Override
	@Transactional
	public boolean removePointDAO(Point point) {
		String method = "removePointDAO";
		try{
			getSession().createQuery("DELETE FROM Point WHERE id = "+ point.getId());
		}catch(Exception e){
			logger.debug(String.format("%s-%s error[%s]",CLASS,method,e));
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Point getPoinById(int id) {
		String method = "getPoinById";
		Point point = null;
		try{
			point = (Point) getSession().createQuery("FROM Point WHERE id = :id")
					.setParameter("id", id).uniqueResult();			
		}catch(Exception e){
			logger.debug(String.format("%s-%s error[%s]",CLASS,method,e));
			return null;
		}
		return point;
	}
	
}
