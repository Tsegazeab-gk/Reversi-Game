package logic.strategy;


import logic.StatePattern.Evaluator;
import util.ReversiBoardHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomAlgorithm implements IMoveStrategy{

    private int myMark;

    public RandomAlgorithm(int mark) {
        this.myMark=mark;
    }
    @Override
    public Point solve(int[][] board, int player, int depth, Evaluator e) {
        Random rnd = new Random();
        ArrayList<Point> myPossibleMoves = ReversiBoardHelper.getAllPossibleMoves(board,player);

        if(myPossibleMoves.size() > 0){
            return myPossibleMoves.get(rnd.nextInt(myPossibleMoves.size()));
        }else{
            return null;
        }
    }
}
