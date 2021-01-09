import java.io.IOException;

public class Branch extends KeyValue {

    public Branch(String branchName, String CommitKey) throws IOException {
        value = CommitKey.getBytes();
        key = "R" + branchName;
        setKVfileHard();
    }

    public static String getCommitKey(String branchName) throws Exception {
        return new String(getValue(branchName));
    }
}
