package logic.levels;


import logic.StatePattern.Evaluator;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import logic.strategy.RandomAlgorithm;
import util.BoardHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EasyLevel implements ILevelStrategy{

    private MoveStrategyImpl strategy;
    Random rnd = new Random();

    private int myMark;

    public EasyLevel(int mark) {
        this.myMark=mark;
      //  myMark=2;
        //super(mark);

        strategy=new MoveStrategyImpl();
        //attaching minimax algorithm
        strategy.setMoveStrategy(new RandomAlgorithm(mark));
    }
    @Override
    public Point getNextMove(int[][] board, int player, int depth) {
       return strategy.getMoveStrategy().solve(board,player,depth,null);
    }
}
