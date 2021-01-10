import java.io.File;

public class Checkout {

    public static File path = Command.path;

    public static void check(String commitKey){
        assert KeyValue.checkIfKeyExists(commitKey);
        deleteAllFiles();
    }

    private static void deleteAllFiles() {

    }
}
