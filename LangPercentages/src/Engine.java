import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    public static Langs langs = new Langs();
    public static List<String> filePaths = new ArrayList<>();
    public static String root;

    public Engine(String _root) {
        root = _root;
    }

    public void run() {

        tree(root);

        int i;
        for (i = 0; i < filePaths.size(); i++) {

            Path path = Paths.get(filePaths.get(i));

            try {
                int currentLines = Files.readAllLines(path, StandardCharsets.UTF_8).size();
                String extension = langs.getExtension(filePaths.get(i));
                extension = extension.toLowerCase();
                langs.load(currentLines, extension);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        langs.showSummary();
    }

    public void tree(String dir) {

        File directory = new File(dir);
        File[] fList = directory.listFiles();

        int i;
        for (i = 0; i < fList.length; i++) {

            if (!fList[i].isDirectory()) {
                filePaths.add(String.valueOf(fList[i]));
            }
            if (fList[i].isDirectory()) {

                File tempDir = new File(String.valueOf(fList[i]));
                File[] tempList = tempDir.listFiles();

                int j;
                for (j = 0; j < tempList.length; j++) {
                    if (tempList[j].isDirectory()) {
                        tree(String.valueOf(tempList[j]));
                    }
                    else {
                        filePaths.add(String.valueOf(tempList[j]));
                    }
                }
            }
        }
    }
}