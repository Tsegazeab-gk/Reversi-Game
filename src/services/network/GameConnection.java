package services.network;

import game.GameEngine;

public interface GameConnection extends GameEngine {
	public void receivedMove(int i, int j);

	public void startCommunication();
}
