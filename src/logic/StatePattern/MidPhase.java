package logic.StatePattern;

import util.BoardHelper;

import static logic.StaticEvaluator.*;

public class MidPhase  implements Evaluator {

    private DynamicEvaluator dynamicEvaluator;

    public MidPhase(DynamicEvaluator gameEvaluator) {
        this.dynamicEvaluator = dynamicEvaluator;
    }


    @Override
    public int eval(int[][] board, int player) {
        int sc = BoardHelper.getTotalStoneCount(board);   // sc = stone count when we give the board for the method in the GameHelper
        if(sc > 58) {
            dynamicEvaluator.setGamePhase(new LatePhase(dynamicEvaluator));
            return dynamicEvaluator.eval(board, player);
        }
        return 1000*evalCorner(board,player) + 20*evalMobility(board,player) + 10*evalDiscDiff(board, player) + 100*evalParity(board);
    }
}
