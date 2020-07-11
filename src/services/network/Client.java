package services.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends ConnectedUser {

    private IConnection connectionState;
    private Thread connectionThread;
    private Thread listiningThread;

    public Client(String address, int port) {
        super(address, port);
    }

    public Client(String address, int port, IConnection connectionState) {
        this(address, port);
        this.isYourTurn = false;
        this.connectionState = connectionState;
    }

    public void startConnection() {
        connectionThread = new Thread(() -> {
            try {
                System.out.println(String.format("Start Client Connection on port %s with address %s", port, address));
                socket = new Socket(address, port);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
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
    public void cancelConnection() {
        try {
            if (connectionThread.isAlive()){
                connectionThread.interrupt();
                System.out.println("Connection stopped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startListining() {
        listiningThread = new Thread(() -> {
            try {

                while (running) {
                    Message msg = (Message) in.readObject();
                    System.out.println("Received -> " + msg);
                    this.running = msg.isRunning();
                    if (!this.running) {
                        break;
                    }
                    this.gc.receivedMove(msg.getI(), msg.getJ());
                    this.isYourTurn = true;
                }

                out.close();
                socket.close();
            } catch (UnknownHostException u) {
                System.out.println(u);
            } catch (IOException i) {
                System.out.println(i);
            } catch (ClassNotFoundException e) {
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
