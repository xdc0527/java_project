import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Checkout {

    public static void checkCommit(String commitKey) throws Exception {
        assert KeyValue.checkIfKeyExists(commitKey);
        deleteAllFiles();
        String stringValueOfCommit = KeyValue.getStringValue(commitKey);
        String rootTreeKey = new Scanner(stringValueOfCommit).nextLine();
        checkoutTree(rootTreeKey,KeyValue.path);
    }

    private static void checkoutTree(String treeKey, File dir) throws Exception {
        String stringValueOfTree = KeyValue.getStringValue(treeKey);
        Scanner scanner = new Scanner(stringValueOfTree);
        while (scanner.hasNext()) {
            String key = scanner.nextLine();
            String name = scanner.nextLine();
            File subFile = new File(dir,name);
            if(subFile.isDirectory()){
                subFile.mkdir();
                checkoutTree(key,subFile);
            }else{
                checkoutBlob(key,subFile);
            }
        }
    }

    private static void checkoutBlob(String key, File subFile) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(subFile);
        outputStream.write(KeyValue.getValue(key));
    }

    private static void deleteAllFiles () {
        for (File file : KeyValue.path.listFiles()) {
            if (file.isDirectory() && !file.getName().equals("gitFolder")) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
    }

    private static void deleteDirectory (File file){
        if (file.listFiles() != null) {
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) deleteDirectory(subFile);
                else subFile.delete();
            }
        }
        file.delete();
    }
}


