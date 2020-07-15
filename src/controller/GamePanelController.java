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


import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanelController implements GameEngine, GameConnection, IMoveStone {

    private GamePanel gamePanel;
    private BoardCell[][] cells;
    private int turn;
    private int[][] board;
    private GamePlayer player1;
    private GamePlayer player2;
    private Invoker invoker = Invoker.INSTANCE;
    private boolean awaitForClick = false;

    private TimerTask player1HandlerTimer;
    private Timer player2HandlerTimer;
    private int totalscore1 = 0;
    private int totalscore2 = 0;

    private int p1score = 0;
    private int p2score = 0;
    int winner;

    public static int PLAYER_ONE;
    public static int PLAYER_TWO;

    private java.util.List<Observer> observers;
    private ConnectedUser connectedUser;

    IMoveStone moveStoneProxy;

    static int INSTANCE_COUNT = 0;

    Timer timer;

    boolean isFirstPlayer=false;

    boolean isPlayerCreated=false;

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


        timer = new Timer();

        player1HandlerTimer = new TimerTask() {
            @Override
            public void run() {
                handleAI(player1);
                timer.cancel();
            }
        };

        gamePanel.getStartButton().addActionListener((ActionEvent event) -> {
            start();
        });

    }

    public void start() {
        System.out.println("start function started");
        isFirstPlayer=true;
        PLAYER_ONE = 1;
        PLAYER_ONE = 2;
        turn = 1;
        //updateBoardInfo();
        manageTurn();
        isPlayerCreated=true;
        manageArrowTurns();
    }

    public void updateBoardInfo() {
        System.out.println("updateBoardInfo function started turn : " + turn);
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

    public void manageTurn() {

        System.out.println("manageTurn---->BoardHelper.hasAnyMoves(board, PLAYER_ONE) " + BoardHelper.hasAnyMoves(board, PLAYER_ONE));
        if (BoardHelper.hasAnyMoves(board, 1) || BoardHelper.hasAnyMoves(board, 2)) {
            updateBoardInfo();
            if (turn == 1) {
                if (BoardHelper.hasAnyMoves(board, 1)) {
                    if (player1.isUserPlayer()) {
                        awaitForClick = true;
                        System.out.println("player1.isUserPlayer() True");
                        //after click this function should be call backed
                    } else {
                        System.out.println("player1.isUserPlayer() false");
                        // player1HandlerTimer.restart();
                        try {
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000 * 2);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    handleAI(player1);
                                }
                            });
                            thread.start();
                        } catch (IllegalStateException il) {
                            il.printStackTrace();
                        }
                        // handleAI(player1);
                        // timer.schedule(player1HandlerTimer,2*60*1000,2*60*1000);
                    }
                } else {
                    //forfeit this move and pass the turn
                    System.out.println("forfeit Player 1 has no legal moves sending -->  i=-1  j= -1");
                   if(connectedUser!=null)
                    connectedUser.sendMove(-1, -1);
                    turn = 2;
                    manageTurn();
                }
            } else if (turn == 2) {
                if (BoardHelper.hasAnyMoves(board, 2)) {
                    if (player2.isUserPlayer()) {
                        System.out.println("player2.isUserPlayer() True");
                        //   awaitForClick = true;
                        //after click this function should be call backed
                        turn = 2;
                    } else {
                        System.out.println("player2.isUserPlayer() false");
                        // player1HandlerTimer.restart();
                        try {
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1000 * 2);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    handleAI(player2);
                                }
                            });
                            thread.start();
                        } catch (IllegalStateException il) {
                            il.printStackTrace();
                        }

                    }
                } else {
                    //forfeit this move and pass the turn
                    System.out.println("forfeit Player 2 has no legal moves!  moves sending -->  i=-1  j= -1");
                    if(connectedUser!=null)
                        connectedUser.sendMove(-1, -1);
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
                this.gamePanel.getWinner().setText("Winner is: "+player1.getPlayerName());
            } else if (winner == 2) {
                this.gamePanel.getWinner().setText("Winner is: "+player2.getPlayerName());
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
       /* if (totalscore1 == 1)
            this.gamePanel.getWinner().setText("winner is: player 2");
        else
            this.gamePanel.getWinner().setText("winner is: player 1");

        */
        this.gamePanel.getTscore1().setText("" + totalscore1);
        this.gamePanel.getTscore2().setText("" + totalscore2);
    }

    public void resetBoard() {

        System.out.println("resetBoard called");
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
            //moveStoneProxy.moveStone(turn, i, j);
            board = BoardHelper.getNewBoardAfterMove(board, new Point(i, j), turn);
            turn = (turn == 1) ? 2 : 1;
            this.gamePanel.repaint();
            System.out.println("Got new Board moveStone");
            for (int a = 0; a < 8; a++) {
                for (int b = 0; b < 8; b++) {

                    System.out.print(board[a][b] + " | ");

                }
                System.out.println();
            }
            manageArrowTurns();
            manageTurn();
            System.out.println("Got new Board moveStone --->turn= " + turn);
            updateBoardInfo();
            //  manageTurn();

           // manageArrowTurns();
        } else
            System.out.println(turn + " can not play in: " + i + " - " + j);

    }

    // added for proxy pattern to count moves
    public void moveStone(int playerNumber, int i, int j) {
        /*
int mark=0;
        // board = invoker.getNewBoardAfterMove(board, new Point(i, j), turn);
        if(isFirstPlayer){
            mark=1;
        }else
            mark=2;
        */
        board = BoardHelper.getNewBoardAfterMove(board, new Point(i, j), turn);

        this.gamePanel.repaint();
        System.out.println("Player 2 ---> new Board after move:--> i=" + i + " j=" + j);
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {

                System.out.print(board[a][b] + " | ");

            }
            System.out.println();
        }
        turn = (playerNumber == 1) ? 2 : 1;
        // System.out.println("Got new Board moveStone --->turn changed to= " + turn);
        updateBoardInfo();
    }

    public void handleAI(GamePlayer ai) {
        System.out.println("handleAI Method");
   /*
       if (connectedUser != null && !connectedUser.isYourTurn()) {
            System.out.println("!connectedUser.isYourTurn() Not your turn"+turn);
          //  return;
        }
*/
        System.out.println("Player 1 Move------>>>>>  Handle AI Before  new Board Before move: turn="+turn);
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {

                System.out.print(board[a][b] + " | ");

            }
            System.out.println();
        }

        Point aiPlayPoint = ai.play(board);

        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;
        System.out.println("aiPlayPoint i=" + i + "j=" + j);
        if (!BoardHelper.canPlay(board, ai.myMark, i, j))
            System.out.println("aiPlayPoint ---> Invalid Move by AI");

        board = BoardHelper.getNewBoardAfterMove(board, new Point(i, j), ai.myMark);
        this.gamePanel.repaint();

        //handleMove(i, j);
        // moveStoneProxy.
        /*
        if (connectedUser != null) {
            if (!connectedUser.isYourTurn()) {
                System.out.println("Not your turn ai......!connectedUser.isYourTurn() turn:"+turn);
                return;
            }
            else
            { System.out.println("Sending...... i="+i +"j="+j);
                connectedUser.sendMove(i, j);

            }
        }*/
        System.out.println("Sending...... i=" + i + "j=" + j);
        connectedUser.sendMove(i, j);

        System.out.println("Player 1 Move------>>>>>>>>>>>>>>>>> Got new Board after move --->turn= " + turn);
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {

                System.out.print(board[a][b] + " | ");

            }
            System.out.println();
        }
       // turn = 2;
        turn = (turn == 1) ? 2 : 1;

        manageArrowTurns();
        updateBoardInfo();

        // turn = (turn == 1) ? 2 : 1;
        //manageTurn();
        //  manageArrowTurns();
    }

    void manageArrowTurns() {
        if (turn == 1) {
            gamePanel.getArrowRight().setVisible(false);
            gamePanel.getArrowLeft().setVisible(true);
        } else if (turn == 2) {
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

        if(!isFirstPlayer){
          //  player1.
                    turn=1;
                    if(!isPlayerCreated){
                     //  GamePlayer temp;
                     //   temp=player1;
                     //   player1=player2;
                     //   player2=temp;
                        System.out.println("Player one: Name:"+player1.myMark+ " Mark"+player1.myMark+ "is User Player"+player1.isUserPlayer());
                        System.out.println("Player two: Name:"+player2.myMark+ " Mark"+player2.myMark+ "is User Player"+player2.isUserPlayer());

                    }
        }else
        turn = 2;

        System.out.println("Player 2 Move------>>>>>  new Board before move turn " + turn +" X="+i+ " Y="+j);
//        this.handleMove(i, j);
        for (int a = 0; a < 8; a++) {
            for (int b = 0; b < 8; b++) {

                System.out.print(board[a][b] + " | ");

            }
            System.out.println();
        }
        if(i>=0 && j>=0) {


        if (BoardHelper.canPlay(board, turn, i, j)) {
//            if (awaitForClick) {
//                System.out.println("playing with "+i+" - "+j);
//                moveStoneProxy.moveStone(turn, i, j);
//                awaitForClick = false;
//            } else {
//                moveStoneProxy.moveStone(turn, i, j);
//            }
            moveStone(turn, i, j);

            manageTurn();
            //  player1HandlerTimer.start();
            // manageArrowTurns();
        }
        }
        else {

            System.out.println(turn + " can not play in: " + i + " - " + j);
            if(i==-1 && j==-1){
              //  System.out.println("Player "+turn +"loses");
                turn = (turn == 1) ? 2 : 1;
               manageTurn();
            }

        }
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
