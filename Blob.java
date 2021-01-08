import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Blob extends KeyValue{

	public Blob(File file) throws IOException, NoSuchAlgorithmException {
		value = genValue(file); // Blob有自己的generate Value方法，方法在下面
		key = "B" + genKey(); //genKey的方法用的是父类KeyValue的，此外，在key前加一个代表Blob的B作为标志
		setKVfile(); // 父类的方法，将key和value送入gitfolder保存。
	}

	/**
	 * Blob的genValue方法，非常简单
	 * 因为Blob的value就是其文件内容
	 * 注意，return的仍然是朴素的二进制数据类型，即byte[]
	 */
	public byte[] genValue(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		return inputStream.readAllBytes();
	}

}