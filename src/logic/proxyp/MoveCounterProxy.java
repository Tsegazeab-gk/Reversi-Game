package logic.proxyp;

import controller.GamePanelController;

public class MoveCounterProxy implements IMoveStone{
	
	GamePanelController  gamePanelController;
	
	ICounter counter;
	

	public MoveCounterProxy(GamePanelController gamePanelController) {
		super();
		this.gamePanelController = gamePanelController;
		
		counter=MoveCounter.getInstance();
	}



	@Override
	public void moveStone(int playerNumber, int i, int j) {
		// TODO Auto-generated method stub
		
		counter.incrementMove(playerNumber);
		
		gamePanelController.moveStone(playerNumber, i, j);
		
	}


	//@Override
	public void getNumberOfMoves(int p) {
		
		gamePanelController.getNumberOfMoves(counter.getNumberOfMoves(p));
	
		//return counter.getNumberOfMoves(p);
	}
	

}
