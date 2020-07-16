package services.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server extends ConnectedUser {

    private ServerSocket server = null;
    private IConnection connectionState;
    private Thread connectionThread;
    private Thread listiningThread;

    public Server(String address, int port) {
        super(address, port);
    }

    public Server(String address, int port, IConnection connectionState) {
        this(address, port);
        this.connectionState = connectionState;
    }

    public void startConnection() {
        connectionThread = new Thread(() -> {
            try {
                System.out.println(String.format("Start Server Connection on port %s", port));
                server = new ServerSocket(port);
                socket = server.accept();
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Connection Done!!!");
                this.connectionState.connected(true, "");
            } catch (Exception e) {
                this.connectionState.connected(false, e.getMessage());
                e.printStackTrace();
            }
        });
        connectionThread.start();
    }

    @Override
    public void sendMove(int i, int j) {
        try {
            Message msg = new Message(i, j);
            System.out.println("Send -> " + msg);
            out.writeUTF(msg.toString());
            isYourTurn = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedMove(int x, int y) {
        this.gc.receivedMove(x, y);
    }

    @Override
    public void cancelConnection() {
        try {
            if (connectionThread.isAlive()) {
                connectionThread.interrupt();
                server.close();
                System.out.println("Connection stopped");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void startListining() {
        listiningThread = new Thread(() -> {
            try {
                while (running) {
                    int i = 0, j = 0;
                    Message msg = new Message(in.readUTF());
                    System.out.println("Received -> " + msg);
                    i = msg.getI();
                    j = msg.getJ();
                    this.running = msg.isRunning();
                    if (!this.running) {
                        break;
                    }
                    this.receivedMove(i, j);
                    this.isYourTurn = true;
                }
                System.out.println("Closing connection");
                socket.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listiningThread.start();
    }

    @Override
    public void stopListining() {
        try {
            running = false;
            if (listiningThread.isAlive())
                listiningThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
