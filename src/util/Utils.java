package util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class Utils {
    public static String getResoursePath(String fileName) {
        return Paths.get("src", "resources", fileName).toAbsolutePath().toString();
    }

    public static boolean validateIPAddress(final String ip) {
        if (ip.equals("localhost"))
            return true;
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        return ip.matches(PATTERN);
    }

    public static boolean validateHTTPUrl(final String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void setEnabledJPanel(JPanel panel, boolean b) {
        if (panel == null)
            return;
        for (Component component : panel.getComponents()) {
            component.setEnabled(b);
        }
    }
}
