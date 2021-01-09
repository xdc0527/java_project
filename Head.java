import java.io.IOException;

public class Head extends KeyValue{

    public final String key = "HEAD";
    public String newCommitKey;

    public static String getCommitKey() throws Exception {
        String HEADvalue = new String(getValue("HEAD"));
        if(HEADvalue.startsWith("DETACHED")){
            return HEADvalue.substring(8);
        }else{
            return Branch.getCommitKey(HEADvalue);
        }
    }

    public Head(String newCommitKey) throws Exception {
        this.newCommitKey = newCommitKey;
        update();
    }

    private void update() throws Exception {
        String oldHeadValue = new String(getValue("HEAD"));
        if(oldHeadValue.startsWith("DETACHED")){
            newDetachedHead();
        }else{
            new Branch(oldHeadValue,newCommitKey);
        }
    }

    private void newDetachedHead() throws IOException {
        value = ("DETACHED"+newCommitKey).getBytes();
        setKVfile();
    }
}
