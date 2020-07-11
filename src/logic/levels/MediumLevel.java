package logic.levels;

import logic.StatePattern.Evaluator;
import logic.factory.EvaluatorFactoryImpl;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;

import java.awt.*;

public class MediumLevel implements ILevelStrategy{

    private int searchDepth;
    private Evaluator evaluator;

    private MoveStrategyImpl strategy;
    private int myMark;

    public MediumLevel(int mark, int depth) {
        //super(mark);
        myMark=mark;

        searchDepth = depth;
        evaluator = EvaluatorFactoryImpl.getFactory().createEvaluator("Dynamic",0);
        //new DynamicEvaluator();
        strategy=new MoveStrategyImpl();
        strategy.setMoveStrategy(new MinimaxAlgorithm());

        System.out.println("Strategy created");

    }
    @Override
    public Point getNextMove(int[][] board, int player, int depth) {
        return strategy.getMoveStrategy().solve(board,myMark,searchDepth,evaluator);
    }
}
