package services.network;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class ConnectedUser {
    protected Socket socket = null;

    // IO String
    protected DataInputStream in;
    protected DataOutputStream out;

    protected String address;
    protected int port;
    protected InetSocketAddress inetSocketAddress;

    protected String userId;
    protected boolean isYourTurn = true;
    protected boolean running = true;
    protected GameConnection gc;
    protected String httpUrl;

    public ConnectedUser(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public ConnectedUser(String url){

        this.httpUrl = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void endConnection() {
        try {
            Message msg = new Message();
            out.writeUTF(msg.toString());
            out.flush();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void sendMove(int x, int y);

    public abstract void receivedMove(int x, int y);

    public void setGameConnection(GameConnection gameConnection) {
        this.gc = gameConnection;
    }

    public abstract void startConnection();

    public abstract void cancelConnection();

    public abstract void startListining();

    public abstract void stopListining();

}
