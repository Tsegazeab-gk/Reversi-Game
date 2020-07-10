package services.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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
        this.isYourTurn = true;
        this.connectionState = connectionState;
    }

    public void startConnection() {
        connectionThread = new Thread(() -> {
            try {
                System.out.println(String.format("Start Server Connection on port %s", port));
                //InetAddress addr = InetAddress.getByName(address);
                server = new ServerSocket(port);
                //server.bind(new InetSocketAddress(address, port));
                System.out.println(server.getInetAddress());
                socket = server.accept();
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
                    Message msg = (Message) in.readObject();
                    System.out.println("Received -> " + msg);
                    this.running = msg.isRunning();
                    if (!this.running) {
                        break;
                    }
                    this.gc.receivedMove(msg.getI(), msg.getJ());
                    this.isYourTurn = true;
                }
                System.out.println("Closing connection");
                socket.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
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
