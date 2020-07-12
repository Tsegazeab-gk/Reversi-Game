package services.network;

import java.io.IOException;
import java.net.*;

public class UDPConnection extends ConnectedUser {
    private DatagramSocket socket;
    private boolean running;
    private Thread listiningThread;
    private IConnection connectionState;

    public static enum ConnectionType {
        CLIENT,
        SERVER
    }

    private ConnectionType connectionType;

    public UDPConnection(String address, int port) {
        super(address, port);
        inetSocketAddress = new InetSocketAddress(address, port);
    }

    public UDPConnection(String address, int port, IConnection connectionState) {
        this(address, port);
        this.connectionState = connectionState;
    }

    public UDPConnection(String address, int port, IConnection connectionState, ConnectionType connectionType) {
        this(address, port, connectionState);
        this.connectionType = connectionType;
    }

    @Override
    public void sendMove(int i, int j) {
        try {
            Message msg = new Message(i, j);
            System.out.println("Send -> " + msg);

            byte[] buffer = msg.toString().getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setSocketAddress(inetSocketAddress);
            socket.send(packet);
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

            if (connectionType.equals(ConnectionType.SERVER)) {
                socket = new DatagramSocket(port);
            } else {
                socket = new DatagramSocket(port);
            }
            System.out.println(String.format("Address: %s - Port: %s", address, port));

            if (socket.isBound()) {
                this.connectionState.connected(true, "");
                System.out.println("Binding Done!!!");
            } else {
                this.connectionState.connected(false, "Binding Failed");
                System.out.println("Binding Failed -> " + socket.getLocalPort());
            }
        } catch (SocketException e) {
            this.connectionState.connected(false, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void cancelConnection() {
        running = false;
        socket.close();
    }

    @Override
    public void startListining() {
        listiningThread = new Thread(() -> {
            System.out.println("UDP startListining");
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            running = true;
            while (running) {
                try {
                    socket.receive(packet);
                    String msg = new String(buffer, 0, packet.getLength());
                    System.out.println("Received ->" + msg);
                    Message message = new Message(msg);
                    receivedMove(message.getI(), message.getJ());
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
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
