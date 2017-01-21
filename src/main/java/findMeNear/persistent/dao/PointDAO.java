package findMeNear.persistent.dao;

import findMeNear.model.utils.PointDistance;
import findMeNear.persistent.entity.Point;

public interface PointDAO {
	public void addPoint(Point point);
	public PointDistance getPointNear(int id, String categoria, double latitudine, double longitudine);
	public boolean removePointDAO(Point point);
	public Point getPoinById(int idPoint);
}
