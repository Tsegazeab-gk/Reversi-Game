package services.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends ConnectedUser {

	private String address;
	private int port;

	// constructor to put ip address and port
	public Client(String address, int port, GameConnection gc) {
		this.address = address;
		this.port = port;
		this.gc = gc;
		this.isYourTurn = false;
	}

	@Override
	public void run() {
		// establish a connection
		try {
			socket = new Socket(address, port);
			System.out.println("User " + userId + " accept request");

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

			out.close();
			socket.close();
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
