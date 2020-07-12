package util;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class Utils {
    public static String getResoursePath(String fileName) {
        return Paths.get("src", "resources", fileName).toAbsolutePath().toString();
    }

    public static boolean validateIPAddress(final String ip) {
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(PATTERN);
    }

    public static void setEnabledJPanel(JPanel panel, boolean b){
        if (panel == null)
            return;
        for (Component component : panel.getComponents()) {
            component.setEnabled(b);
        }
    }
}
