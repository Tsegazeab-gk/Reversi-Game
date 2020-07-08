package util;

import java.nio.file.Paths;

public class Utils {
    public static String getResoursePath(String fileName) {
        return Paths.get("src", "resources", fileName).toAbsolutePath().toString();
    }
}
