package logic.strategy;


import logic.StatePattern.Evaluator;
import util.ReversiBoardHelper;

import java.awt.*;

public class MinimaxAlgorithm implements IMoveStrategy{

    static int nodesExplored = 0;

    //returns max score move
    public  Point solve(int[][] board, int player, int depth, Evaluator e){
        nodesExplored = 0;
        int bestScore = Integer.MIN_VALUE;
        Point bestMove = null;
        for(Point move : ReversiBoardHelper.getAllPossibleMoves(board,player)){
            //create new node
            int[][] newNode = ReversiBoardHelper.getNewBoardAfterMove(board,move,player);
            //recursive call
            int childScore = MMAB(newNode,player,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE,e);
                if(childScore > bestScore) {
                bestScore = childScore;
                bestMove = move;
            }
        }
        System.out.println("Nodes Explored : " + nodesExplored);
        return bestMove;
    }


    //returns minimax value for a given node with A/B pruning
    private  int MMAB(int[][] node,int player,int depth,boolean max,int alpha,int beta,Evaluator e){
        nodesExplored++;
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || ReversiBoardHelper.isGameFinished(node)){
            //BoardPrinter bpe = new BoardPrinter(node,"Depth : " + depth);
            return e.eval(node,player);
        }
        int oplayer = (player==1) ? 2 : 1;
        //if no moves available then forfeit turn
        if((max && !ReversiBoardHelper.hasAnyMoves(node,player)) || (!max && !ReversiBoardHelper.hasAnyMoves(node,oplayer))){
            //System.out.println("Forfeit State Reached !");
            return MMAB(node,player,depth-1,!max,alpha,beta,e);
        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
            for(Point move : ReversiBoardHelper.getAllPossibleMoves(node,player)){ //my turn
                //create new node
                int[][] newNode = ReversiBoardHelper.getNewBoardAfterMove(node,move,player);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,false,alpha,beta,e);
                if(childScore > score) score = childScore;
                //update alpha
                if(score > alpha) alpha = score;
                if(beta <= alpha) break; //Beta Cutoff
            }
        }else{
            //minimizing
            score = Integer.MAX_VALUE;
            for(Point move : ReversiBoardHelper.getAllPossibleMoves(node,oplayer)){ //opponent turn
                //create new node
                int[][] newNode = ReversiBoardHelper.getNewBoardAfterMove(node,move,oplayer);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,true,alpha,beta,e);
                if(childScore < score) score = childScore;
                //update beta
                if(score < beta) beta = score;
                if(beta <= alpha) break; //Alpha Cutoff
            }
        }
        return score;
    }
    //print
    /*public static void printMM(int[][] board, int player,int depth){
        BoardPrinter BP = new BoardPrinter(board,"Main");
        int bestScore = Integer.MIN_VALUE;
        Point bestMove = null;
        for(Point move : BoardHelper.getAllPossibleMoves(board,player)){
            //create new node
            int[][] newNode = BoardHelper.getNewBoardAfterMove(board,move,player);
            //recursive call
            int childScore = MMAB(newNode,player,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE);
            BP.cells[move.x][move.y].text = String.valueOf(childScore);
        }
        BP.repaint();
    }*/

    //returns minimax value for a given node (without A/B pruning)
    /*private static int MM(int[][] node,int player,int depth,boolean max){
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || BoardHelper.isGameFinished(node)){
            //BoardPrinter bpe = new BoardPrinter(node,"Depth : " + depth);
            return Evaluator.eval(node,player);
        }
        int oplayer = (player==1) ? 2 : 1;
        //if no moves available then forfeit turn
        if((max && !BoardHelper.hasAnyMoves(node,player)) || (!max && !BoardHelper.hasAnyMoves(node,oplayer))){
            System.out.println("Forfeit State Reached !");
            return MM(node,player,depth-1,!max);
        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
            for(Point move : BoardHelper.getAllPossibleMoves(node,player)){ //my turn
                //create new node
                int[][] newNode = BoardHelper.getNewBoardAfterMove(node,move,player);
                //recursive call
                int childScore = MM(newNode,player,depth-1,false);
                if(childScore > score) score = childScore;
            }
        }else{
            //minimizing
            score = Integer.MAX_VALUE;
            for(Point move : BoardHelper.getAllPossibleMoves(node,oplayer)){ //opponent turn
                //create new node
                int[][] newNode = BoardHelper.getNewBoardAfterMove(node,move,oplayer);
                //recursive call
                int childScore = MM(newNode,player,depth-1,true);
                if(childScore < score) score = childScore;
            }
        }
        return score;
    }*/

}
