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
}
