package services.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedUser extends Thread {
	protected Socket socket = null;
	protected ObjectInputStream in = null;
	protected ObjectOutputStream out = null;

	protected String userId;
	protected boolean isYourTurn = true;
	protected boolean running = true;
	protected GameConnection gc;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void sendMove(int i, int j) {
		try {
			Message msg = new Message(i, j);
			msg.setUser(userId + "");
			System.out.println("Send -> " + msg);
			System.out.println(out);
			out.writeObject(msg);
			isYourTurn = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isYourTurn() {
		return isYourTurn;
	}

	public void endConnection() {
		try {
			Message msg = new Message();
			msg.setRunning(false);
			out.writeObject(msg);
			out.flush();
			running = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
