package services.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -5399605122490343339L;

    private int i;
    private int j;
    private boolean running = true;
    private JSONObject jsonObject;

//    private static String ROW_FORMAT = "x";
//    private static String COLUMN_FORMAT = "y";

    private static String ROW_FORMAT = "row";
    private static String COLUMN_FORMAT = "col";

    public Message() {
    }

    public Message(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Message(String jsonString) {
        try {
            jsonObject = new JSONObject(jsonString);
            Object object = jsonObject.get("homeNewPiece");
            System.out.println("Received => " + object);
            if (object != null) {
                try {
                    JSONObject j2 = (JSONObject) object;
                    i = (int) j2.get(ROW_FORMAT);
                    j = (int) j2.get(COLUMN_FORMAT);
                } catch (Exception e) {
                    i = -1;
                    j = -1;
                }
            } else {
                i = (int) jsonObject.get(ROW_FORMAT);
                j = (int) jsonObject.get(COLUMN_FORMAT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void setMessageFormat(String row, String column) {
        Message.ROW_FORMAT = row;
        Message.COLUMN_FORMAT = column;
    }

    @Override
    public String toString() {
        return String.format("{\"%s\": %s, \"%s\": %s}", ROW_FORMAT, i, COLUMN_FORMAT, j);
    }

}
