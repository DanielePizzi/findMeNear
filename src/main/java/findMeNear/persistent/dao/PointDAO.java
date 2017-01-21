package findMeNear.persistent.dao;

import findMeNear.persistent.entity.Point;

public interface PointDAO {
	public void addPoint(Point point);
	public Point getPointNear(int id, String categoria, double latitudine, double longitudine);
}
