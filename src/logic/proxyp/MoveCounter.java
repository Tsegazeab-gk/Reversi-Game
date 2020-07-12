package logic.proxyp;

import java.util.HashMap;

public class MoveCounter implements ICounter{
	
	HashMap<Integer, Integer> numberOfMoves=new HashMap<Integer, Integer>();
	
	 static MoveCounter instance=new MoveCounter();
	
	
	private MoveCounter() {
		super();
	}

	public static MoveCounter getInstance() {
		return instance;
	}

	@Override
	public void incrementMove(int p) {
int moves=0;

if(numberOfMoves.containsKey(p)) {
	moves=numberOfMoves.get(p);
}else {
	numberOfMoves.put(p, 0);
}
moves=moves+1;
numberOfMoves.put(p, moves);
		
	}

	

	@Override
	public int getNumberOfMoves(int p) {
	if(numberOfMoves.size()>0)
		return numberOfMoves.get(p);
	return 0;
	}

}
