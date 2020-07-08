package player.ai;


import player.GamePlayer;
import logic.DynamicEvaluator;
import logic.Evaluator;
import logic.Minimax;
import logic.factory.EvaluatorFactoryImpl;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import logic.StatePattern.DynamicEvaluator;
import logic.StatePattern.Evaluator;

import java.awt.*;

public class AIPlayerDynamic extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    private MoveStrategyImpl strategy;

    public AIPlayerDynamic(int mark, int depth) {
        super(mark);
        searchDepth = depth;
        evaluator = EvaluatorFactoryImpl.getFactory().createEvaluator("Dynamic",0);
        //new DynamicEvaluator();
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
