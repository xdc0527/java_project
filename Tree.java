import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tree extends KeyValue{

	public Tree(File dir) throws IOException, NoSuchAlgorithmException {
		value = genValue(dir); // Tree独有的generate value方法，方法在下面
		key = "T" + genKey(); // 父类的gen key方法，前面加T标志Tree
		setKVfile(); //将key和value保存进gitfolder
	}

	public byte[] genValue(File dir) throws IOException, NoSuchAlgorithmException {
		String result = "";
		for(File f : dir.listFiles()){
			if(f.isFile()) {
				// 如果是子文件，就生成一个Blob对象
				Blob blob = new Blob(f);
				// 子文件生成Blob对象后，会生成一个key
				// Tree对象的value包含子文件的key和文件名
				result += blob.key + "/n" + f.getName() + "/n";
			} else{
				// 如果是子文件夹，就生成一个Tree对象
				Tree tree = new Tree(f);
				// 子文件夹生成Tree对象后，会生成一个key
				// Tree对象的value包含子文件夹的的key和文件夹名
				result += tree.key + "/n" + f.getName() + "/n";
			}
		}
		return result.getBytes(); // 最后return的是二进制byte[]
	}

}