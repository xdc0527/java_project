import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash{

	/**
	 * @param bytes 因为value我们统一采取了二进制byte[]类型，因此这里输入的参数类型是byte[]
	 * @return return的是String，可以直接用来作为key即文件名
	 *
	 */
	public static String getSHA1(byte[] bytes) throws IOException, NoSuchAlgorithmException {
		byte[] buffer = new byte[1024]; 
		MessageDigest complete = MessageDigest.getInstance("SHA-1");
		// message digest输出的本来仍是byte[]，
		// 使用byteArrayToHexString方法，将其转换成表示十六进制的String
		// byteArrayToHexString方法的定义在下面
		return byteArrayToHexString(complete.digest(bytes));
	}

	public static String byteArrayToHexString(byte[] bytes){
		String result = "";
		// 下面这部分是在补零
		// 因为类似14,15这样的数字会被Integer.toString转换为e,f
		// 但是他们应当被转换成0e,0f
		// 所以如果数字小于16，要在前面补一个0
		for(byte eachByte:bytes){
			if(Integer.toHexString(eachByte).length() == 1){
				result += "0" + Integer.toHexString(eachByte);
			}
			else result += Integer.toHexString(eachByte);
		}
		return result;
	}


}

