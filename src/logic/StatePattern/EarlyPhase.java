package logic.StatePattern;

import util.BoardHelper;

import static logic.StaticEvaluator.evalCorner;
import static logic.StaticEvaluator.evalMobility;

public class EarlyPhase implements Evaluator {
    private DynamicEvaluator dynamicEvaluator;

    public EarlyPhase(DynamicEvaluator dynamicEvaluator) {
        this.dynamicEvaluator = dynamicEvaluator;
    }

    @Override
    public int eval(int[][] board, int player) {
        int sc = BoardHelper.getTotalStoneCount(board);
        if(sc > 20) {
            dynamicEvaluator.setGamePhase(new MidPhase(dynamicEvaluator));
            return dynamicEvaluator.eval(board, player);
        }
        return 1000 * evalCorner(board,player) + 50 * evalMobility(board,player);
    }

}
