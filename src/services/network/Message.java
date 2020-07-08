package services.network;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = -5399605122490343339L;

	private String user;
	private int i;
	private int j;
	private boolean running = true;

	public Message() {
	}

	public Message(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public void parsePoint(String point) {
		if (point == null) {
			return;
		}
		String[] pt = point.split(",");
		if (pt.length < 2) {
			return;
		}
		this.i = Integer.parseInt(pt[0]);
		this.j = Integer.parseInt(pt[1]);
	}

	public String convertPoint(int i, int j) {
		return i + "," + j;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public String toString() {
		return "Message [User " + user + ": i=" + i + ", j=" + j + "]";
	}

}
