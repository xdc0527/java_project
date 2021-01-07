import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Scanner;

public class Commit extends KeyValue{

    public static File config = new File(gitFolder,"config");
    public static File HEAD = new File(gitFolder,"HEAD");

    public String parentCommit;

    public Commit(String message) throws IOException, NoSuchAlgorithmException {
        value = genValue(message);
        key = "C" + genKey();
        UpdateHEAD();
    }

    public byte[] genValue(String message) throws IOException, NoSuchAlgorithmException {
        String result = new Tree(path).key;
        result += "\n" + message;
        String time = new Date().toString();
        result += "\n" + time;
        String author = new Scanner(config).nextLine();
        result += "\n" + author;
        return result.getBytes();
    }

    public void UpdateHEAD() throws Exception {
        new Head(key);
    }
}
