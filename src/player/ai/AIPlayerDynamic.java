package player.ai;


import player.GamePlayer;
import logic.DynamicEvaluator;
import logic.Evaluator;
import logic.Minimax;
import logic.StatePattern.DynamicEvaluator;
import logic.StatePattern.Evaluator;

import java.awt.*;

public class AIPlayerDynamic extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    public AIPlayerDynamic(int mark, int depth) {
        super(mark);
        searchDepth = depth;
        evaluator = new DynamicEvaluator();
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Dynamic AI (Depth " + searchDepth + ")";
    }

    @Override
    public Point play(int[][] board) {
        return Minimax.solve(board,myMark,searchDepth,evaluator);
    }
}
