package logic.StatePattern;

import util.ReversiBoardHelper;

import static logic.evaluatorfactory.StaticEvaluator.*;

public class EarlyPhase implements Evaluator {
    private DynamicEvaluator gameEvaluator;

    public EarlyPhase(DynamicEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }

    @Override
    public int eval(int[][] board, int player) {
        int sc = ReversiBoardHelper.getTotalStoneCount(board);
        if(sc > 20) {
            gameEvaluator.setGamePhase(new MidPhase(gameEvaluator));
            return gameEvaluator.eval(board, player);
        }
        return 1000 * evalCorner(board,player) + 50 * evalMobility(board,player);
    }

    public static class DynamicEvaluator implements Evaluator {
        //Evaluation Function Changes during Early-Game / Mid-Game / Late-Game
        private Evaluator gamePhase = new EarlyPhase(this);

        public int eval(int[][] board, int player){
            if(ReversiBoardHelper.isGameFinished(board)){
                return 1000 * evalDiscDiff(board, player);
            }
            return gamePhase.eval(board, player);
        }

        public void setGamePhase(Evaluator gamePhase) {
            this.gamePhase = gamePhase;
        }
    }
}
