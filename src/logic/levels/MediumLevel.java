package logic.levels;

import logic.StatePattern.Evaluator;
import logic.evaluatorfactory.EvaluatorFactoryImpl;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategy;

import java.awt.*;

public class MediumLevel implements ILevelStrategy {

    private int searchDepth;
    private Evaluator evaluator;
    private MoveStrategy strategy;
    private int myMark;

    public MediumLevel(int mark, int depth) {
        myMark = mark;
        searchDepth = depth;
        evaluator = EvaluatorFactoryImpl.getFactory().createDynamicEvaluator( mark);
        strategy = new MoveStrategy();
        strategy.setMoveStrategy(new MinimaxAlgorithm());
        System.out.println("Strategy created");

    }

    @Override
    public Point getNextMove(int[][] board, int player, int depth) {
        return strategy.getMoveStrategy().solve(board, myMark, searchDepth, evaluator);
    }
}
