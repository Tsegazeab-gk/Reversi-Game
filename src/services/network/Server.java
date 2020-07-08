package services.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server extends ConnectedUser {
	private ServerSocket server = null;
	private int port;

	// constructor with port
	public Server(int port, GameConnection gc) {
		this.port = port;
		this.gc = gc;
		this.isYourTurn = true;
	}

	@Override
	public void run() {

		try {
			server = new ServerSocket(port);
			System.out.println("User " + this.userId + " start a request");
			System.out.println("Waiting for a user to play ...");

			socket = server.accept();
			System.out.println("User accepted");

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			this.gc.startCommunication();

			System.out.println("User " + userId + " start listening");
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
			// close connection
			socket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
