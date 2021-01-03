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

}