package findMeNear.utils;

import java.util.GregorianCalendar;

public class SessionToken {
	
	private static int id = 0;
	
	public static String getSessionToken(){
		GregorianCalendar calendar = new GregorianCalendar();
		String date= String.valueOf(calendar.DATE);
		String hour = String.valueOf(calendar.HOUR);
		String minute = String.valueOf(calendar.MINUTE);
		String millisecond = String.valueOf(calendar.MILLISECOND);
		String i = String.valueOf(id++);
		String sessionToken = date+hour+minute+millisecond+i;
		return sessionToken;
	}
}
