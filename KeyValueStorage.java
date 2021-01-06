import java.io.*;
import java.util.Scanner;

public class KeyValueStorage{
	protected String key;
	protected File file;
    	protected String type;
    	protected String value;

	public void genKey(File file) throws Exception{
		this.file = file;
		this.key = Hash.SHA1CheckFile(file);
	}

	public void genKey(String s) throws Exception{
		this.key = Hash.SHA1CheckName(s);
	}

	public String getType(){
		return this.type;
	}

	public String getKey(){
		return this.key;
	}

	public void write() throws Exception{
		FileInputStream fileInputStream = new FileInputStream(this.file);
		FileOutputStream output = new FileOutputStream(this.key);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		do {
			numRead = fileInputStream.read(buffer);
			if(numRead > 0){
				output.write(buffer);
			}
		}while(numRead!=-1);
		fileInputStream.close();
		output.close();
	}



}
