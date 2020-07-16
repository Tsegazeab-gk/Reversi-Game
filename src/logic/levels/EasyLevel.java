package logic.levels;


import logic.strategy.MoveStrategy;
import logic.strategy.RandomAlgorithm;

import java.awt.*;
import java.util.Random;

public class EasyLevel implements ILevelStrategy{

    private MoveStrategy strategy;
    Random rnd = new Random();
    private int myMark;

    public EasyLevel(int mark) {
        this.myMark=mark;
        strategy=new MoveStrategy();
        strategy.setMoveStrategy(new RandomAlgorithm(mark));
    }
    @Override
    public Point getNextMove(int[][] board, int player, int depth) {
       return strategy.getMoveStrategy().solve(board,player,depth,null);
    }
}
