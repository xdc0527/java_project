import java.io.IOException;

public class Head extends KeyValue{

    public final String key = "HEAD";
    public String newCommitKey;


    public Head(String newCommitKey) throws Exception {
        this.newCommitKey = newCommitKey;
        update();
    }


    public static String getCommitKey() throws Exception {
        String HEADvalue = new String(getValue("HEAD"));
        if(HEADvalue.startsWith("DETACHED")){
            return HEADvalue.substring(8);
        }else{
            return Branch.getCommitKey(HEADvalue);
        }
    }

    private void update() throws Exception {
        String oldHeadValue = new String(getValue("HEAD"));
        if(oldHeadValue.startsWith("DETACHED")){
            updateDetachedHead();
        }else{
            new Branch(oldHeadValue,newCommitKey);
        }
    }

    public void updateDetachedHead() throws IOException {
        if(checkIfKeyExists()){
            KVfile.delete();
        }
        value = ("DETACHED"+newCommitKey).getBytes();
        setKVfile();
    }
}
