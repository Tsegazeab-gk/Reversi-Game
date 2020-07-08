package logic.StatePattern;

import util.BoardHelper;

import static logic.StaticEvaluator.*;

public class MidPhase  implements Evaluator {

    private DynamicEvaluator gameEvaluator;

    public MidPhase(DynamicEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }


    @Override
    public int eval(int[][] board, int player) {
        int sc = BoardHelper.getTotalStoneCount(board);
        if(sc > 58) {
            gameEvaluator.setGamePhase(new LatePhase(gameEvaluator));
            return gameEvaluator.eval(board, player);
        }
        return 1000*evalCorner(board,player) + 20*evalMobility(board,player) + 10*evalDiscDiff(board, player) + 100*evalParity(board);
    }
}
