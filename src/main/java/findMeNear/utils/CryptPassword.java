package findMeNear.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {

	public static MessageDigest md;
	
	public static String cryptWithMD5(String password){
		try{
			md = MessageDigest.getInstance("MD5");
			byte[] passwordByte = password.getBytes();
			md.reset();
			byte[] digested = md.digest(passwordByte);
			StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();	
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}
}
