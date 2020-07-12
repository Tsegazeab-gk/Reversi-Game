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

    public Message() {
    }

    public Message(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Message(String jsonString) {
        try {
            jsonObject = new JSONObject(jsonString);
            i = (int) jsonObject.get("x");
            j = (int) jsonObject.get("y");
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

    @Override
    public String toString() {
        return String.format("{\"x\": %s, \"y\": %s}", i, j);
    }

}
