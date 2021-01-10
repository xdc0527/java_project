import java.io.File;
public class Test {
    public static void testBlob(String path) throws Exception{
        Blob b = new Blob(new File(path));
        System.out.println(b.key);
    }

    public static void testTree(String path) throws Exception{
        Tree t = new Tree(new File(path));
        System.out.println(t.key);
    }

    public static void main(String[] args) throws Exception{
        testBlob("C:/Users/lenovo/Desktop/rw/java/hw4/FileHash.java");
        testTree("C:/Users/lenovo/Desktop/rw/java/hw4");
        Command.init();
        Commit c = new Commit("added");
        System.out.println(Head.getCommitKey());
    }
}
