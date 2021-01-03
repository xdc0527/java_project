import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
public class Hash{

	public static String SHA1CheckFile(File file) throws Exception{
		FileInputStream is = new FileInputStream(file);
		byte[] buffer = new byte[1024]; 
		MessageDigest complete = MessageDigest.getInstance("SHA-1"); 
		int numRead = 0;
		do {
			numRead = is.read(buffer); 
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1); 
		is.close();
		return convertToHex(complete.digest());
	}

	public static String SHA1CheckName(String s) throws Exception{
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		complete.update(s.getBytes());
        return convertToHex(complete.digest());
	}

 	public static String convertToHex(byte sha1[]) { 
		String result = ""; 
		for (int i = 0; i < sha1.length; i ++) {
			result += Integer.toString(sha1[i] & 0xFF, 16);                               
		}
		return result;
	}

}