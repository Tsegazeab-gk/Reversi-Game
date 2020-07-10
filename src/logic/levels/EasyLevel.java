package logic.levels;


import logic.StatePattern.Evaluator;
import util.BoardHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EasyLevel implements ILevelStrategy{

    Random rnd = new Random();

    private int myMark;

    public EasyLevel(int mark) {
        this.myMark=mark;
      //  myMark=2;
        //super(mark);
    }
    @Override
    public Point getNextMove(int[][] board, int player, int depth) {
        ArrayList<Point> myPossibleMoves = BoardHelper.getAllPossibleMoves(board,myMark);

        if(myPossibleMoves.size() > 0){
            return myPossibleMoves.get(rnd.nextInt(myPossibleMoves.size()));
        }else{
            return null;
        }
    }
}
