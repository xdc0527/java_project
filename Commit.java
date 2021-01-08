import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Commit extends KeyValue{

    public static File config = new File(gitFolder,"config");

    public Commit(String message) throws Exception {
        value = genValue(message);
        key = "C" + genKey();
        updateHead();
        setKVfile();
    }

    public byte[] genValue(String message) throws Exception {
        String rootTreeKey = new Tree(path).key;
        String parentCommitKey = Head.getCommitKey();
        String time = new Date().toString();
        String author = new Scanner(config).nextLine();
        String result =  rootTreeKey + "\n" + parentCommitKey + "\n";
               result += message + "\n" + time + "\n" + author;
        return result.getBytes();
    }

    public void updateHead() throws Exception {
        new Head(key);
    }
}
