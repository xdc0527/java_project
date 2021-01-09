import java.io.File;
import java.util.Scanner;

public class Command {

    public static File path;

    public static void setPath(File path) {
        Command.path = path;
    }

    public void init(){
        Scanner scanner = new Scanner(System.in);
        // 先索要path和author（写在config里）
        askPath();
        askAuthor();
        // 然后创建Head和main branch
        // 然后根据用户输入的单词，执行任务
    }

    public static void checkout(String branchOrCommit) throws Exception {
        boolean isBranch = KeyValue.checkIfKeyExists("R" + branchOrCommit);
        boolean isCommit = KeyValue.checkIfKeyExists("C" + branchOrCommit);
        if(isBranch){
            Head.checkoutBranch("R" + branchOrCommit);
        }else if(isCommit){
            Head.checkoutCommit("C" + branchOrCommit);
        }else{
            System.out.println("无法找到对应的Branch或Commit");
        }

    }
}
