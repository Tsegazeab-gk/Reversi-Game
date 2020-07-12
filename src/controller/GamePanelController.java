package controller;

import controller.command.Invoker;
import controller.observer.Observer;
import game.BoardCell;
import game.GameEngine;
import game.GamePanel;


import logic.proxyp.IMoveStone;
import logic.proxyp.MoveCounterProxy;
import player.GamePlayer;

import services.dao.IScoreService;
import services.dao.ScoreService;
import services.network.ConnectedUser;
import services.network.GameConnection;
import util.BoardHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GamePanelController implements GameEngine, GameConnection, IMoveStone {

    private GamePanel gamePanel;
    private BoardCell[][] cells;
    private int turn;//= 1;
    private int[][] board;
    private GamePlayer player1;
    private GamePlayer player2;    //new AIPlayerDynamic(2,6);
    private Invoker invoker=Invoker.INSTANCE;
    //    private GamePlayer player1 = new AIPlayerRealtimeKiller(1,6,true);
//    private GamePlayer player2 = new AIPlayerDynamic(2,6);
    private boolean awaitForClick = false;
    private Timer player1HandlerTimer;
    private Timer player2HandlerTimer;
    private int totalscore1 = 0;
    private int totalscore2 = 0;

    private int p1score = 0;
    private int p2score = 0;
    int winner;

    private java.util.List<Observer> observers;
    private ConnectedUser connectedUser;

    IMoveStone moveStoneProxy;

    public GamePanelController(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        observers = new ArrayList<Observer>();

        // setting for proxy pattern
        moveStoneProxy = new MoveCounterProxy(this);

        this.attach(gamePanel);
        resetBoard();

        cells = new BoardCell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new BoardCell(this, this.gamePanel.getReversiBoard(), i, j);
                this.gamePanel.getReversiBoard().add(cells[i][j]);
            }
        }


        //AI Handler Timer (to unfreeze gui)
        player1HandlerTimer = new Timer(1000, (ActionEvent e) -> {
            handleAI(getPlayer1());
            player1HandlerTimer.stop();
            manageTurn();
        });

        player2HandlerTimer = new Timer(1000, (ActionEvent e) -> {
            handleAI(getPlayer2());
            player2HandlerTimer.stop();
            manageTurn();
        });
    }

    public void start() {
        updateBoardInfo();
//        updateTotalScore();
        manageTurn();
    }


    public void manageTurn() {
        if (BoardHelper.hasAnyMoves(board, 1) || BoardHelper.hasAnyMoves(board, 2)) {
            updateBoardInfo();
            if (turn == 1) {
                if (BoardHelper.hasAnyMoves(board, 1)) {
                    if (player1.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player1HandlerTimer.start();
                    }
                } else {
                    //forfeit this move and pass the turn
                    System.out.println("Player 1 has no legal moves !");
                    turn = 2;
                    manageTurn();
                }
            } else {
                if (BoardHelper.hasAnyMoves(board, 2)) {
                    if (player2.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player2HandlerTimer.start();
                    }
                } else {
                    //forfeit this move and pass the turn
                    System.out.println("Player 2 has no legal moves !");
                    turn = 1;
                    manageTurn();
                }
            }
        } else {
            //game finished
            updateBoardInfo();
            System.out.println("Game Finished !");
              winner = BoardHelper.getWinner(board);
            if (winner == 1) {
                 totalscore1++;
                this.gamePanel.getWinner().setText("winner is: player 1");
            }
            else if (winner == 2) {
                this.gamePanel.getWinner().setText("winner is: player 2");
                 totalscore2++;
            }
//            updateTotalScore();
            //restart
            //resetBoard();
            //turn=1;
            //manageTurn();
            updateTotalScore();
            moveStoneProxy.getNumberOfMoves(winner);

        }
    }

    @Override
    public void getNumberOfMoves(int moves) {

        IScoreService score = new ScoreService();

        if (winner == 1) {

            score.saveWinner(player1.getPlayerName(), moves, p1score);
        } else if (winner == 2) {
            score.saveWinner(player2.getPlayerName(), moves, p2score);
        }

        System.out.println("Number Of Moves" + moves + player1.getPlayerName());

    }

    public void updateBoardInfo() {

        this.p1score = 0;
        this.p2score = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) p1score++;
                if (board[i][j] == 2) p2score++;

                if (BoardHelper.canPlay(board, turn, i, j)) {
                    cells[i][j].setHighlight(1);
                } else {
                    cells[i][j].setHighlight(0);
                }
            }
        }

        notifyObservers();
//        this.gamePanel.getScore1().setText(player1.playerName() + " : " + p1score);
//        this.gamePanel.getScore2().setText(player2.playerName() + " : " + p2score);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update("Score: " + p1score, "Score: " + p2score);
    }

    @Override
    public void attach(Observer observer) {
        if(!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        int i=observers.indexOf(observer);
        if(i>=0)
            observers.remove(i);
    }

    public void updateTotalScore() {
        if (totalscore1==1)
            this.gamePanel.getWinner().setText("winner is: player 2");
        else
            this.gamePanel.getWinner().setText("winner is: player 1");
//        this.gamePanel.getTscore1().setText(""+totalscore1);
//        this.gamePanel.getTscore2().setText(""+totalscore2);
        this.gamePanel.getTscore1().setText("" + totalscore1);
        this.gamePanel.getTscore2().setText("" + totalscore2);
    }

    public void resetBoard() {
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        //initial board state
        setBoardValue(3, 3, 2);
        setBoardValue(3, 4, 1);
        setBoardValue(4, 3, 1);
        setBoardValue(4, 4, 2);
    }

    public void handleClick(int i, int j) {
        if (connectedUser != null) {
            if (!connectedUser.isYourTurn())
                return;
            else
                connectedUser.sendMove(i, j);
        }
        handleMove(i, j);
    }

    private void handleMove(int i, int j) {
        if (awaitForClick && BoardHelper.canPlay(board, turn, i, j)) {
            System.out.println("User Played in : " + i + " , " + j);

            // System.out.println("Calling proxy turn Number: " + turn);

            moveStoneProxy.moveStone(turn, i, j);
            System.out.println("your turn: " + turn);


            /// moveStone(turn, i, j);

        }
    }

    // added for proxy pattern to count moves
    public void moveStone(int playerNumber, int i, int j) {

        if (connectedUser != null) {
            connectedUser.sendMove(i, j);
        }
        // update board
//        board = BoardHelper.getNewBoardAfterMove(board, new Point(i, j), playerNumber);
        board = invoker.getNewBoardAfterMove(board, new Point(i, j), turn);
        // advance turn
        turn = (playerNumber == 1) ? 2 : 1;

            this.gamePanel.repaint();

            awaitForClick = false;

        // callback
        //   manageTurn();
        manageArrowTurns();

    }

    public void handleAI(GamePlayer ai) {

        if (connectedUser != null) {
            if (!connectedUser.isYourTurn()){
                System.out.println("returning.....");
                return;
            }
        }

        Point aiPlayPoint = ai.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;

        if (!BoardHelper.canPlay(board, ai.myMark, i, j)) System.err.println("FATAL : AI Invalid Move !");
//        System.out.println(ai.playerName() + " Played in : " + i + " , " + j);

        // if (connectedUser != null) {
        //     connectedUser.sendMove(i, j);
        // }

        // //update board using the invoker of the command pattern
        // board=invoker.getNewBoardAfterMove(board,aiPlayPoint,turn);
/*
        // update board using the invoker of the command pattern
        board = invoker.getNewBoardAfterMove(board, aiPlayPoint, turn);
//        board = BoardHelper.getNewBoardAfterMove(board,aiPlayPoint,turn);

        // advance turn
//        turn = (turn == 1) ? 2 : 1;

        this.gamePanel.repaint();

        System.out.println("Calling proxy turn Number: " + turn);
        moveStoneProxy.moveStone(turn, i, j);

        */
        System.out.println("Calling proxy turn Number: " + turn);
        moveStoneProxy.moveStone(turn, i, j);
    }

    void manageArrowTurns() {

        if (turn == 1) {
            gamePanel.getArrowRight().setVisible(false);
            gamePanel.getArrowLeft().setVisible(true);
        } else if (turn == 2) {
            gamePanel.getArrowLeft().setVisible(false);
            gamePanel.getArrowRight().setVisible(true);
        } else {

        }

        manageTurn();
    }

    public void setPlayer1(GamePlayer player) {
        this.player1 = player;
        this.turn = (player.myMark == 1) ? player.myMark : 2;

        System.out.println("Player  name:" + player.getPlayerName() + "Player num" + player.myMark);
        manageArrowTurns();

        gamePanel.getLabelPlayer1().setText(player.getPlayerName());
    }

    public void setPlayer2(GamePlayer player) {
        this.player2 = player;
    }

    public void setConnectedUser(ConnectedUser connectedUser) {
        this.connectedUser = connectedUser;
    }

    @Override
    public int getBoardValue(int i, int j) {
        return board[i][j];
    }

    @Override
    public void setBoardValue(int i, int j, int value) {
        board[i][j] = value;
    }


    @Override
    public void receivedMove(int i, int j) {
//        this.handleClick(i,j);
        this.handleMove(i,j);

    }

    public GamePlayer getPlayer1() {
        return player1;
    }

    public GamePlayer getPlayer2() {
        return player2;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
