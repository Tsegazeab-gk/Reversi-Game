package services.network;

public class FactoryConnection {

    public static ConnectedUser createTCPServerConnection(String ipAddr, int port, IConnection connectionState) {
        return new Server(ipAddr, port, connectionState);
    }

    public static ConnectedUser createTCPClientConnection(String ipAddr, int port, IConnection connectionState) {
        return new Client(ipAddr, port, connectionState);
    }

    public static ConnectedUser createUDPConnection(String addr, int sourcePortInt, int destPortInt, IConnection connectionState) {
        return new UDPConnection(addr, sourcePortInt, destPortInt, connectionState);
    }

    public static ConnectedUser createHTTPConnection(String addr, IConnection connectionState) {
        return new HttpConnection(addr, connectionState);
    }
}
