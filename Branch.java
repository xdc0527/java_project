import java.io.IOException;

public class Branch extends KeyValue {

    public Branch(String branchName, String CommitKey) throws IOException {
        value = CommitKey.getBytes();
        key = "R" + branchName;
        if(checkIfKeyExists()){
            KVfile.delete();
        }
        setKVfile();
    }

    public static String getCommitKey(String branchName) throws Exception {
        return new String(getValue(branchName));
    }
}
