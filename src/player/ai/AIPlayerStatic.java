package player.ai;


import player.GamePlayer;

import logic.Minimax;
import logic.StaticEvaluator;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import logic.StatePattern.Evaluator;

import java.awt.*;

public class AIPlayerStatic extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    //for selecting algorithm to get move point
    private MoveStrategyImpl strategy;

    public AIPlayerStatic(int mark, int depth) {
        super(mark);
        searchDepth = depth;
        evaluator = new StaticEvaluator();

        //creating move strategy
        strategy=new MoveStrategyImpl();
        //attaching minimax algorithm
        strategy.setMoveStrategy(new MinimaxAlgorithm());
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Static AI (Depth " + searchDepth + ")";
    }

    @Override
    public Point play(int[][] board) {

       // return Minimax.solve(board,myMark,searchDepth,evaluator);

        return strategy.getMoveStrategy().solve(board,myMark,searchDepth,evaluator);
    }
}
