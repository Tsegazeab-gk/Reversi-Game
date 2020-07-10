package controller.command;


import util.BoardHelper;

import java.awt.*;

public class BoardCommand implements Command{

    private int[][] board;
    private Point aiPlayPoint;
    private int turn;

    public BoardCommand(int[][] board, Point aiPlayPoint, int turn){
        this.board=board;
        this.aiPlayPoint=aiPlayPoint;
        this.turn=turn;
    }

    @Override
    public void execute() {
        this.board=BoardHelper.getNewBoardAfterMove(board,aiPlayPoint,turn);
    }

    @Override
    public boolean undo() {
        return false;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Point getAiPlayPoint() {
        return aiPlayPoint;
    }

    public void setAiPlayPoint(Point aiPlayPoint) {
        this.aiPlayPoint = aiPlayPoint;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
