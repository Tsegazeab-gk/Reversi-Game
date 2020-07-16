package logic.StatePattern;

import static logic.evaluatorfactory.StaticEvaluator.*;

public class LatePhase implements Evaluator {
    private EarlyPhase.DynamicEvaluator gameEvaluator;

    public LatePhase(EarlyPhase.DynamicEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }

    @Override
    public int eval(int[][] board, int player) {
        return 1000 * evalCorner(board,player) + 100 * evalMobility(board,player) + 500 * evalDiscDiff(board, player) + 500 * evalParity(board);
    }
}
