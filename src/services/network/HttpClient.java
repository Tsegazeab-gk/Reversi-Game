package services.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient extends ConnectedUser {

    HttpURLConnection con;
    URL url;

    public HttpClient(String url) {
        super(url);
        this.httpUrl = url;
    }

    public HttpClient(String url, IConnection connectionState) {
        this(url);
        this.httpUrl = url;
    }

    private void sendPostMessage(String jsonInputString) throws IOException {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

    @Override
    public void sendMove(int x, int y) {
        try {
            String jsonInputString = String.format("{\"x\": %s, \"y\": 4}", x, y);
            sendPostMessage(jsonInputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedMove(int x, int y) {
        this.gc.receivedMove(x, y);
    }

    @Override
    public void startConnection() {
        try {
            //"http://localhost:8080/reversi-game/api/play"
            url = new URL(httpUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHttlUrl(){
        return httpUrl;
    }

    public void setHttpUrl(String url){
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
