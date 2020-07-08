package logic.StatePattern;

import util.BoardHelper;

import static logic.StaticEvaluator.evalCorner;
import static logic.StaticEvaluator.evalMobility;

public class EarlyPhase implements Evaluator {
    private DynamicEvaluator gameEvaluator;

    public EarlyPhase(DynamicEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }

    @Override
    public int eval(int[][] board, int player) {
        int sc = BoardHelper.getTotalStoneCount(board);
        if(sc > 20) {
            gameEvaluator.setGamePhase(new MidPhase(gameEvaluator));
            return gameEvaluator.eval(board, player);
        }
        return 1000 * evalCorner(board,player) + 50 * evalMobility(board,player);
    }

}
