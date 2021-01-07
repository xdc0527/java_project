import java.nio.charset.StandardCharsets;

public class Head extends KeyValue{

    public final String key = "HEAD";
    public String NewCommitKey;


    public Head(String NewcommitKey) throws Exception {
        this.NewCommitKey = NewcommitKey;
        updateHead();
    }

    private void updateHead() throws Exception {
        String oldHEADvalue = new String(getValue("HEAD"), StandardCharsets.UTF_8);
        if(oldHEADvalue.substring(0,8) == "DETACHED"){
            updateDetachedHead();
        }else{
            updateBranch();
        }
    }

    public void UpdateDetachedHead(){

    }
}
