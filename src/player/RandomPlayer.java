package player;

import game.*;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import logic.strategy.RandomAlgorithm;
import util.BoardHelper;

import java.awt.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

//Easy level
//medium / hard
//difficult  // super hard
public class RandomPlayer extends GamePlayer {
    private MoveStrategyImpl strategy;


    Random rnd = new Random();

    public RandomPlayer(int mark) {
        super(mark);

        strategy=new MoveStrategyImpl();
        strategy.setMoveStrategy(new RandomAlgorithm(mark));
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Random Player";
    }

    @Override
    public Point play(int[][] board) {
        return  strategy.getMoveStrategy().solve(board,0,0,null);
//        ArrayList<Point> myPossibleMoves = BoardHelper.getAllPossibleMoves(board,myMark);
//
//        if(myPossibleMoves.size() > 0){
//            return myPossibleMoves.get(rnd.nextInt(myPossibleMoves.size()));
//        }else{
//            return null;
//        }


    }

}
