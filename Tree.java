import java.io.File;
public class Tree extends KeyValueStorage{
	public Tree(File f) throws Exception{
		this.type = "Tree";
		this.value = "";
		for (File file : f.listFiles()){
			if (file.isFile()){
				value += "100644 blob " + new Blob(file).getKey() + " " + file.getName() + "\n";
			} else if (file.isDirectory()){
				value += "040000 tree " + new Tree(file).getKey() + " " + file.getName() + "\n";
			}
		}
		genKey(value);
	}

	@Override
	public String toString(){
		return "040000 tree " + key;
	}

	public static void main(String[] args) throws Exception{
		File f = new File("C:/Users/lenovo/Desktop/rw/java/MyGit");
		Tree t = new Tree(f);
		System.out.println(t);
	}
}