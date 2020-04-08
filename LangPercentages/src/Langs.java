import java.util.ArrayList;
import java.util.List;

public class Langs {

    public static List<String> extensions = new ArrayList<>();
    public static List<Integer> lineCountList = new ArrayList<>();

    public static String getExtension(String path) {

        path = path.replace("\\", "/");
        String[] arr = path.split("/");
        String fileName = arr[arr.length - 1];

        return fileName.split("\\.")[1];
    }

    public void load(int lineCount, String extension) {

        if (!extensions.contains(extension)) {
            extensions.add(extension);
            lineCountList.add(lineCount);
            return;
        }
        if (extensions.contains(extension)) {

            int i;
            for (i = 0; i < extensions.size(); i++) {

                if (extensions.get(i).equals(extension)) {
                    int newVal = lineCountList.get(i) + lineCount;
                    lineCountList.set(i, newVal);
                }
            }
        }
    }

    public void showSummary() {

        int i;
        int total = 0;
        for (i = 0; i < lineCountList.size(); i++) {
            total = total + lineCountList.get(i);
        }

        int j;
        for (j = 0; j < extensions.size(); j++) {

            double currentLines = lineCountList.get(j);
            double raw = currentLines / total * 100;
            System.out.println(extensions.get(j) + " - " + (int)raw + "%");
        }
    }
}