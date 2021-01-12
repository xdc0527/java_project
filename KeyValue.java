import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * KeyValue是Blob,Tree,Commit的父类,提供了一些三者都需要的公共功能
 */
public class KeyValue {

    // path是根目录的路径，由Command class提供。Command运行时会要求用户输入path。
    public static File path;
    // gitFolder相当于.git文件夹，用来存储所有的备份。
    public static File gitFolder;

    //不论是Blob，Tree，还是Commit，都有Key和Value。Key是根据Value产生的。注意Value我们采取了byte[]类型。
    public String key;
    public byte[] value;
    // KVfile就是该Blob或Tree或Commit在gitFolder中的文件地址，也就是gitFolder文件夹中文件名为key的文件。
    public File KVfile ;

    public static void setPath(){
        path = Command.path;
        gitFolder = new File(path, "gitFolder");
        gitFolder.mkdir();
    }

    // 下面两个不重要，不需要仔细看。
    // 这个方法可以看看gitFolder里有没有文件名是key的文件，它主要是为了辅助实现下面的getFile方法。
    public static boolean checkIfKeyExists(String key){
        return new File(gitFolder,key).exists();
    }

    // 如果key文件存在，就在gitFolder中找到它，return这个文件的File。这个方法是用来辅助实现下面的getValue方法。
    private static File getFileInGitFolder(String key) throws Exception {
        if(checkIfKeyExists(key)) return new File(gitFolder,key);
        else throw new FileNotFoundException("No such key found");
    }

    // 下面这个是比较重要的，其他class会调用这个方法。
    // 根据key，在gitFolder中找到对应的文件File，获取Value。注意这里的Value仍然是原始的二进制格式，即byte[]。
    public static byte[] getValue(String key) throws Exception {
        FileInputStream inputStream = new FileInputStream(getFileInGitFolder(key));
        return inputStream.readAllBytes();
    }

    public static String getStringValue(String key) throws Exception{
        return new String(getValue(key));
    }


    // Blob,Tree,Commit的gen value方法不一致，但是由value去gen key的方法是一致的
    // 因此他们有共同的generate key方法
    // 这个方法调用Hash类，根据value输出形如0f2ab384模样的key String
    public String genKey() throws IOException, NoSuchAlgorithmException {
        return Hash.getSHA1(value);
    }

    // 有了value和key就可以在gitFolder生成一个对应的文件。
    // 这个方法就是用来生成gitFolder中的文件的。
    public void setKVfile() throws IOException {
        if(!checkIfKeyExists()){
            FileOutputStream outputStream = new FileOutputStream(KVfile);
            outputStream.write(value);
            outputStream.close();
        }
    }

    public void setKVfileHard() throws IOException {
        if(checkIfKeyExists()){
            KVfile.delete();
        }
        setKVfile();
    }


    // 在上面的setKVfile方法中用到，看看该instance的key在gitfolder中是不是已经有了，有了就没必要再添加一个一样的了。
    public boolean checkIfKeyExists(){
        KVfile = new File(gitFolder,key);
        return KVfile.exists();
    }
}
