package services.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection extends ConnectedUser {

    HttpURLConnection con;
    URL url;
    private IConnection connectionState;

    public HttpConnection(String url) {
        super(url);
        this.httpUrl = url;
    }

    public HttpConnection(String url, IConnection connectionState) {
        this(url);
        this.connectionState = connectionState;
    }

    private void sendPostMessage(String jsonInputString) throws IOException {
        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            byte[] output = jsonInputString.getBytes("utf-8");
            out.write(output, 0, output.length);
            out.flush();
            this.isYourTurn = false;
        }

        int code = con.getResponseCode();
        System.out.println(code);
        if(code != 200){
            this.isYourTurn = true;
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Received -> " + response.toString());
            Message message = new Message(response.toString());

            if(code == 200){
                receivedMove(message.getI(), message.getJ());
                this.isYourTurn = true;
            }
        }

        con.disconnect();
    }

    @Override
    public void sendMove(int i, int j) {
        try {
            Message message = new Message(i, j);
            String jsonInputString = message.toString();
            sendPostMessage(jsonInputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedMove(int i, int j) {
        this.gc.receivedMove(i, j);
    }

    @Override
    public void startConnection() {
        try {
            // https://reversit1.herokuapp.com/
            // http://localhost:8080/reversi-game/api/play
            url = new URL(httpUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            this.connectionState.connected(true, "");
        } catch (MalformedURLException e) {
            this.connectionState.connected(true, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            this.connectionState.connected(true, e.getMessage());
            e.printStackTrace();
        }
    }

    public String getHttlUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String url) {
        this.httpUrl = url;
    }

    @Override
    public void cancelConnection() {
        con.disconnect();
    }

    @Override
    public void startListining() {

    }

    @Override
    public void stopListining() {

    }
}
