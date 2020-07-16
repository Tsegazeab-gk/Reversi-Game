package logic.levels;

import logic.OpeningBook;
import logic.StatePattern.Evaluator;
import logic.evaluatorfactory.EvaluatorFactoryImpl;
import logic.strategy.MinimaxAlgorithm;
import logic.strategy.MoveStrategyImpl;
import util.ReversiBoardHelper;

import java.awt.*;
import java.util.ArrayList;

public class DifficultLevel implements ILevelStrategy{

    private int searchDepth;
    private Evaluator evaluator;
    private boolean isFirstPlayer;

    private ArrayList<Point> moveHistory;
    private int[][] lastboard;
    private boolean firstRun = true;

    OpeningBook OB;
    private boolean isOpeningActive = true;
    private MoveStrategyImpl strategy;

    int myMark;
    public DifficultLevel(int mark, int depth, boolean firstplayer) {
        myMark=mark;
        strategy=new MoveStrategyImpl();
        //attaching minimax algorithm
        strategy.setMoveStrategy(new MinimaxAlgorithm());
        //init OpeningBook
        OB = new OpeningBook();
        OB.initOpening();
        moveHistory = new ArrayList<>();
        searchDepth = depth;
        isFirstPlayer = firstplayer;

        evaluator= EvaluatorFactoryImpl.getFactory().createKillerEvaluator(mark);
    }


    @Override
    public Point getNextMove(int[][] board, int player, int depth){

        if(firstRun){
            if(!isFirstPlayer){
                Point opMove = ReversiBoardHelper.getMove(ReversiBoardHelper.getStartBoard(),board);
                if(opMove != null) moveHistory.add(opMove);
                else isOpeningActive = false;
            }
            firstRun = false;
        }else{
            Point opMove = ReversiBoardHelper.getMove(lastboard,board);
            if(opMove != null) moveHistory.add(opMove);
            else isOpeningActive = false;
        }

        Point myMove = playUtil(board);
        lastboard = ReversiBoardHelper.getNewBoardAfterMove(board,myMove,myMark);
        moveHistory.add(myMove);
        return myMove;
    }

    public Point playUtil(int[][] board) {
        ArrayList<Point> moves = ReversiBoardHelper.getAllPossibleMoves(board,myMark);
        int opMark = ((myMark == 1) ? 2 : 1);

        Point bestToPlay = null;
        int bestValue = Integer.MIN_VALUE;

        //Corner Detection
        ArrayList<Point> corners = new ArrayList<>();
        corners.add(new Point(0,0));
        corners.add(new Point(0,7));
        corners.add(new Point(7,0));
        corners.add(new Point(7,7));
        for(Point move : moves){
            for(Point corner : corners){
                if(corner.equals(move)){
                    int mval = evaluator.eval(ReversiBoardHelper.getNewBoardAfterMove(board,move,myMark),myMark);
                    if(mval > bestValue) {
                        //update best corner
                        bestToPlay = move;
                        bestValue = mval;
                    }
                }
            }
        }
        if(bestToPlay != null){
            return bestToPlay;
        }

        bestToPlay = null;
        bestValue = Integer.MIN_VALUE;

        //Blocking Move Detection
        for(Point move : moves){
            int[][] resBoard = ReversiBoardHelper.getNewBoardAfterMove(board,move,myMark);
            if(ReversiBoardHelper.getAllPossibleMoves(resBoard,opMark).size() == 0){ //if opponent has no moves
                int mval = evaluator.eval(resBoard,myMark);
                if(mval > bestValue) {
                    //update best corner
                    bestToPlay = move;
                    bestValue = mval;
                }
            }
        }
        if(bestToPlay != null){
            return bestToPlay;
        }
        if(isOpeningActive) {
            Point opmove = OB.getMoveFromOpeningBook(moveHistory);
            if (opmove != null) {
                System.out.println("\033[1;30;34m OPENING MOVE \033[0m\n");
                return opmove;
            }
            isOpeningActive = false;
            System.out.println("\033[1;30;44m OPENING DEACTIVATED \033[0m\n");
        }

        return strategy.getMoveStrategy().solve(board,myMark,searchDepth,evaluator);
    }
}
