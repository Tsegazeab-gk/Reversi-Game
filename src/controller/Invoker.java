package controller;

import player.ai.AIPlayerDynamic;

import java.awt.*;
import java.util.Stack;

public class Invoker {
    private Command currentCommand=null;
    private Stack<Command> commandsExcuted=new Stack<>();

//    public Point playDynamic(int mark, int depth, int[][] board){
//        this.currentCommand=new PlayDynamicCommand(mark,depth);
//        commandsExcuted.push(currentCommand);
//        return currentCommand.execute(board);
//    }

    public int[][] getNewBoardAfterMove(int[][] board, Point aiPlayPoint ,int turn){
        this.currentCommand=new GetBoardCommand(board, aiPlayPoint, turn);
        commandsExcuted.push(currentCommand);
        currentCommand.execute();
        return ((GetBoardCommand) currentCommand).getBoard();
    }
}
