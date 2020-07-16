package logic.StatePattern;

import util.ReversiBoardHelper;

import static logic.evaluatorfactory.StaticEvaluator.*;

public class MidPhase  implements Evaluator {

    private EarlyPhase.DynamicEvaluator gameEvaluator;

    public MidPhase(EarlyPhase.DynamicEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }


    @Override
    public int eval(int[][] board, int player) {
        int sc = ReversiBoardHelper.getTotalStoneCount(board);
        if(sc > 58) {
            gameEvaluator.setGamePhase(new LatePhase(gameEvaluator));
            return gameEvaluator.eval(board, player);
        }
        return 1000*evalCorner(board,player) + 20*evalMobility(board,player) + 10*evalDiscDiff(board, player) + 100*evalParity(board);
    }
}
