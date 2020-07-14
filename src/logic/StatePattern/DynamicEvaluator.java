package logic.StatePattern;

import  util.BoardHelper;


import static logic.StaticEvaluator.*;

public class DynamicEvaluator implements Evaluator {
    //Evaluation Function Changes during Early-Game / Mid-Game / Late-Game
    private Evaluator gamePhase = new EarlyPhase(this);  // Assign early game phase as a default.

    public int eval(int[][] board, int player){
        if(BoardHelper.isGameFinished(board)){
            return 1000 * evalDiscDiff(board, player);  // this is the algorith if the game is finished.
        }
        return gamePhase.eval(board, player);
    }

    public void setGamePhase(Evaluator gamePhase) {
        this.gamePhase = gamePhase;
    }
}
