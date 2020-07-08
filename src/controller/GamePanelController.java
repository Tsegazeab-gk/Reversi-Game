package controller;

import game.BoardCell;
import game.GameEngine;
import game.GamePanel;
import game.GamePlayer;
import logic.factory.LevelFactoryImpl;
import player.HumanPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtimeKiller;
import util.BoardHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GamePanelController implements GameEngine {

    private GamePanel gamePanel;
    private    BoardCell[][] cells;
    private int turn = 1;
    private int[][] board;

    private GamePlayer player1 = LevelFactoryImpl.getFactory().createPlayer(1,6,true,"superhard");
            //new AIPlayerRealtimeKiller(1,6,true);
    private GamePlayer player2 = LevelFactoryImpl.getFactory().createPlayer(2,6,false,"superhard");
                    //new AIPlayerDynamic(2,6);
//    private GamePlayer player1 = new HumanPlayer(1);
//    private GamePlayer player2 = new HumanPlayer(2);
    private boolean awaitForClick = false;
    private Timer player1HandlerTimer;
    private Timer player2HandlerTimer;
    private int totalscore1 = 0;
    private int totalscore2 = 0;

    public GamePanelController(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        resetBoard();

        cells = new BoardCell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new BoardCell(this,this.gamePanel.getReversiBoard(),i,j);
                this.gamePanel.getReversiBoard().add(cells[i][j]);
            }
        }

        //
        updateBoardInfo();
        updateTotalScore();

        //AI Handler Timer (to unfreeze gui)
        player1HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player1);
            player1HandlerTimer.stop();
            manageTurn();
        });

        player2HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player2);
            player2HandlerTimer.stop();
            manageTurn();
        });

        manageTurn();
    }


    public void manageTurn(){
        if(BoardHelper.hasAnyMoves(board,1) || BoardHelper.hasAnyMoves(board,2)) {
            updateBoardInfo();
            if (turn == 1) {
                if(BoardHelper.hasAnyMoves(board,1)) {
                    if (player1.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player1HandlerTimer.start();
                    }
                }else{
                    //forfeit this move and pass the turn
                    System.out.println("Player 1 has no legal moves !");
                    turn = 2;
                    manageTurn();
                }
            } else {
                if(BoardHelper.hasAnyMoves(board,2)) {
                    if (player2.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player2HandlerTimer.start();
                    }
                }else{
                    //forfeit this move and pass the turn
                    System.out.println("Player 2 has no legal moves !");
                    turn = 1;
                    manageTurn();
                }
            }
        }else{
            //game finished
            System.out.println("Game Finished !");
            int winner = BoardHelper.getWinner(board);
            if(winner==1) totalscore1++;
            else if(winner==2) totalscore2++;
            updateTotalScore();
            //restart
            //resetBoard();
            //turn=1;
            //manageTurn();
        }
    }

    public void updateBoardInfo(){

        int p1score = 0;
        int p2score = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 1) p1score++;
                if(board[i][j] == 2) p2score++;

                if(BoardHelper.canPlay(board,turn,i,j)){
                    cells[i][j].setHighlight(1);
                }else{
                    cells[i][j].setHighlight(0);
                }
            }
        }

       this.gamePanel.getScore1().setText(player1.playerName() + " : " + p1score);
        this.gamePanel.getScore2().setText(player2.playerName() + " : " + p2score);
    }

    public void updateTotalScore(){
        this.gamePanel.getTscore1().setText(player1.playerName() + " : " + totalscore1);
        this.gamePanel.getTscore2().setText(player2.playerName() + " : " + totalscore2);
    }

    public void resetBoard(){
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        //initial board state
        setBoardValue(3,3,2);
        setBoardValue(3,4,1);
        setBoardValue(4,3,1);
        setBoardValue(4,4,2);
    }

    public void handleClick(int i,int j){
        if(awaitForClick && BoardHelper.canPlay(board,turn,i,j)){
            System.out.println("User Played in : "+ i + " , " + j);

            //update board
            board = BoardHelper.getNewBoardAfterMove(board,new Point(i,j),turn);

            //advance turn
            turn = (turn == 1) ? 2 : 1;

            this.gamePanel.repaint();

            awaitForClick = false;

            //callback
            manageTurn();
        }
    }

    public void handleAI(GamePlayer ai){
        Point aiPlayPoint = ai.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;
        if(!BoardHelper.canPlay(board,ai.myMark,i,j)) System.err.println("FATAL : AI Invalid Move !");
        System.out.println(ai.playerName() + " Played in : "+ i + " , " + j);

        //update board
        board = BoardHelper.getNewBoardAfterMove(board,aiPlayPoint,turn);

        //advance turn
        turn = (turn == 1) ? 2 : 1;

        this.gamePanel.repaint();
    }

    @Override
    public int getBoardValue(int i,int j){
        return board[i][j];
    }

    @Override
    public void setBoardValue(int i,int j,int value){
        board[i][j] = value;
    }
}
/*
  private GamePlayer player1 = LevelFactoryImpl.getFactory().createPlayer(1,6,true,"hard");
            //new AIPlayerRealtimeKiller(1,6,true);
    private GamePlayer player2 = LevelFactoryImpl.getFactory().createPlayer(2,6,false,"superhard");
 */