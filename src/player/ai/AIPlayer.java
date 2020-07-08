package player.ai;


import logic.StatePattern.DynamicEvaluator;
import logic.StatePattern.Evaluator;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import player.GamePlayer;

import java.awt.*;

public class AIPlayer extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    private MoveStrategyImpl strategy;

    public AIPlayer(int mark, int depth) {
        super(mark);
        searchDepth = depth;

        evaluator = new DynamicEvaluator();

        strategy=new MoveStrategyImpl();
        strategy.setMoveStrategy(new MinimaxAlgorithm());

        System.out.println("Strategy created");

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
    public Point play(int[][] board)
    {
       // return Minimax.solve(board,myMark,searchDepth,evaluator);

        return strategy.getMoveStrategy().solve(board,myMark,searchDepth,evaluator);
    }
}
