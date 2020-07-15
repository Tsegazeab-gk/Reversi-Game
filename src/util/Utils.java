package util;

import org.json.JSONException;
import org.json.JSONObject;
import services.network.Message;

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


    public static Message receivedMessageParser(String jsonString) {
        int i = -1, j = -1;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            Object object = jsonObject.get("homeNewPiece");
            System.out.println("Received => " + object);
            if (object != null) {
                try {
                    JSONObject j2 = (JSONObject) object;
                    i = (int) j2.get(Message.ROW_FORMAT);
                    j = (int) j2.get(Message.COLUMN_FORMAT);
                } catch (Exception e) {
                    i = -1;
                    j = -1;
                }
            } else {
                i = -1;
                j = -1;
            }

        } catch (Exception e) {
            try {
                jsonObject = new JSONObject(jsonString);
                i = (int) jsonObject.get(Message.ROW_FORMAT);
                j = (int) jsonObject.get(Message.COLUMN_FORMAT);
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }

        return new Message(i, j);
    }

    public static String sendMessageParser(Message message) {
        String output = String.format("{ \"Point\":{ \"%s\": %s, \"%s\":%s }, \"gameId\":%s }",
                Message.ROW_FORMAT, message.getI(), message.getJ(), Message.COLUMN_FORMAT, "test");

        if (false)
            return output;
        return message.toString();
    }


}
