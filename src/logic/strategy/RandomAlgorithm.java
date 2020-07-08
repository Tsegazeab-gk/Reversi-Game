package logic.strategy;

import logic.Evaluator;
import util.BoardHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomAlgorithm implements IMoveStrategy{

    Random rnd = new Random();

    private int myMark;

    public RandomAlgorithm(int mark) {
        this.myMark=mark;
        myMark=2;
        //super(mark);
    }
    @Override
    public Point solve(int[][] board, int player, int depth, Evaluator e) {
        ArrayList<Point> myPossibleMoves = BoardHelper.getAllPossibleMoves(board,myMark);

        if(myPossibleMoves.size() > 0){
            return myPossibleMoves.get(rnd.nextInt(myPossibleMoves.size()));
        }else{
            return null;
        }
    }
}
