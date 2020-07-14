package controller;

import controller.command.Invoker;
import controller.observer.Observer;
import game.BoardCell;
import game.GameEngine;
import game.GamePanel;


import logic.proxyp.IMoveStone;
import logic.proxyp.MoveCounterProxy;
import models.GameOption;
import models.Screen;
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
    private int turn = 1;
    private int[][] board;
    private GamePlayer player1;
    private GamePlayer player2;
    private Invoker invoker = Invoker.INSTANCE;
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
        player1HandlerTimer = new Timer(2000, (ActionEvent e) -> {
            handleAI(getPlayer1());
            player1HandlerTimer.stop();
            //manageTurn();
        });

        player2HandlerTimer = new Timer(2000, (ActionEvent e) -> {
            handleAI(getPlayer2());
            player2HandlerTimer.stop();
            //manageTurn();
        });

        gamePanel.getStartButton().addActionListener((ActionEvent event) -> {
            start();
        });
    }

    public void start() {
        updateBoardInfo();
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
            } else if (winner == 2) {
                this.gamePanel.getWinner().setText("winner is: player 2");
                totalscore2++;
            }
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
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update("Score: " + p1score, "Score: " + p2score);
    }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer))
            observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0)
            observers.remove(i);
    }

    public void updateTotalScore() {
        if (totalscore1 == 1)
            this.gamePanel.getWinner().setText("winner is: player 2");
        else
            this.gamePanel.getWinner().setText("winner is: player 1");
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
        //BoardHelper.canPlay(board, turn, i, j)
        if (true) {
//            if (awaitForClick) {
//                System.out.println("playing with "+i+" - "+j);
//                moveStoneProxy.moveStone(turn, i, j);
//                awaitForClick = false;
//            } else {
//                moveStoneProxy.moveStone(turn, i, j);
//            }
            moveStoneProxy.moveStone(turn, i, j);
            manageTurn();
            manageArrowTurns();
        } else
            System.out.println(turn + " can not play in: " + i + " - " + j);

    }

    // added for proxy pattern to count moves
    public void moveStone(int playerNumber, int i, int j) {
        board = invoker.getNewBoardAfterMove(board, new Point(i, j), turn);
        turn = (playerNumber == 1) ? 2 : 1;
        System.out.println("turn= " + turn);
        this.gamePanel.repaint();
    }

    public void handleAI(GamePlayer ai) {
        if (connectedUser != null && !connectedUser.isYourTurn()) {
            return;
        }
        Point aiPlayPoint = ai.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;

        if (connectedUser != null) {
            if (!connectedUser.isYourTurn())
                return;
            else
                connectedUser.sendMove(i, j);
        }
        handleMove(i, j);
    }

    void manageArrowTurns() {
        if (turn == 1) {
            gamePanel.getArrowRight().setVisible(false);
            gamePanel.getArrowLeft().setVisible(true);
        } else {
            gamePanel.getArrowLeft().setVisible(false);
            gamePanel.getArrowRight().setVisible(true);
        }
    }

    public void setPlayer1(GamePlayer player) {
        this.player1 = player;
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
//        this.handleMove(i, j);
        if (BoardHelper.canPlay(board, turn, i, j)){
//            if (awaitForClick) {
//                System.out.println("playing with "+i+" - "+j);
//                moveStoneProxy.moveStone(turn, i, j);
//                awaitForClick = false;
//            } else {
//                moveStoneProxy.moveStone(turn, i, j);
//            }
            moveStoneProxy.moveStone(turn, i, j);
            manageArrowTurns();
            manageTurn();
        } else
            System.out.println(turn + " can not play in: " + i + " - " + j);
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
