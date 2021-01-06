import java.io.File;

public class Blob extends KeyValueStorage{
	public Blob(File f) throws Exception{
	    this.type = "Blob";
		genKey(f);
	}

	@Override
	public String toString(){
		return "100644 blob " + key;
	}

	public static void main(String[] args) throws Exception {
		File f = new File("C:/Users/lenovo/Desktop/rw/java/MyGit/a.txt");
		Blob b = new Blob(f);
		b.write();
		System.out.println(b);
		System.out.println();
	}

}
