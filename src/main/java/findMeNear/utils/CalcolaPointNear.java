package findMeNear.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ch.qos.logback.core.read.ListAppender;
import findMeNear.persistent.entity.Point;

public class CalcolaPointNear {
	
	private static final String CLASS = "calcolaPointNear";
	
	static Logger logger = Logger.getLogger("FINDMENEAR");
	
	public static Point distanceMin(ArrayList<Point> listPoint, double latitudine, double longitudine){
		String method = "calcolaPoint";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::lista punti[%s]",CLASS,method,listPoint));
		logger.debug(String.format("%s - %s::latitudine posizione utente[%s]",CLASS,method,latitudine));
		logger.debug(String.format("%s - %s::longitudine posizione utnete[%s]",CLASS,method, longitudine));
		logger.debug(String.format("%s - %s::lista punti lunghezza[%s]",CLASS,method,listPoint.size()));
		
		if(listPoint == null || listPoint.isEmpty()){
			logger.debug(String.format("%s - %s:: la lista di input e' vuota",CLASS,method));
			return null;
		}
		
		double dmin = -1;
		Point pointMin = null;
		Iterator<Point> itr = listPoint.iterator();
		while(itr.hasNext()){
			Point point = itr.next();
			double d = distance(latitudine, longitudine, Double.parseDouble(point.getLat()), Double.parseDouble(point.getLng()), 'K');
			
			if(dmin == -1 || dmin > d){
				dmin = d;
				pointMin = point;
			}
		}
		
		if(dmin >= 40){
			logger.debug(String.format("%s - %s::punto piu' vicino ad una distanza maggiore di 40 kilometri[%s]",CLASS,method,pointMin.toString()));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			logger.debug(String.format("%s - %s::           END",CLASS,method));
			logger.debug(String.format("%s - %s::*****************************",CLASS,method));
			return null;
		}
		
		logger.debug(String.format("%s - %s::distanza minima[%s]",CLASS,method,dmin));
		logger.debug(String.format("%s - %s::punto piu' vicino[%s]",CLASS,method,pointMin.toString()));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		return pointMin;
	}
	
	
	private static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		
		String method = "distance";
		
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           START",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                    + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
          dist = dist * 1.609344;
        } else if (unit == 'N') {
              dist = dist * 0.8684;
        }
        logger.debug(String.format("%s - %s::distanza[%s]",CLASS,method,dist));
        logger.debug(String.format("%s - %s::*****************************",CLASS,method));
		logger.debug(String.format("%s - %s::           END",CLASS,method));
		logger.debug(String.format("%s - %s::*****************************",CLASS,method));
        return dist;
	}
	
	 // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
      return (rad * 180 / Math.PI);
    }
}
