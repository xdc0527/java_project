import java.io.IOException;

public class Head extends KeyValue{

    public final String key = "HEAD";

    public static String getCommitKey() throws Exception {
        String HEADvalue = new String(getValue("HEAD"));
        if(HEADvalue.startsWith("C")){
            return HEADvalue.substring(8);
        }else{
            return Branch.getCommitKey(HEADvalue);
        }
    }

    public Head(String StringValue) throws IOException {
        this.value = StringValue.getBytes();
        setKVfileHard();
    }

    public static void checkoutBranch(String branchKey) throws Exception {
        new Head(branchKey);
        Checkout.checkCommit(Branch.getCommitKey(branchKey));
    }

    public static void checkoutCommit(String commitKey) throws Exception {
        new Head(commitKey);
        Checkout.checkCommit(commitKey);
    }

    public static void update(String newCommitKey) throws Exception {
        String oldHeadValue = new String(getValue("HEAD"));
        if(oldHeadValue.startsWith("C")){
            new Head(newCommitKey);
        }else{
            new Branch(oldHeadValue.substring(1), newCommitKey);
        }
    }


}
