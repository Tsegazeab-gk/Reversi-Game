package controller.command;

import java.awt.*;
import java.util.Stack;

public enum  Invoker {
    INSTANCE;
    private Command currentCommand=null;
    private Stack<Command> commandsExcuted=new Stack<>();

    public int[][] getNewBoardAfterMove(int[][] board, Point aiPlayPoint ,int turn){
        this.currentCommand=new BoardCommand(board, aiPlayPoint, turn);
        commandsExcuted.push(currentCommand);
        currentCommand.execute();
        return ((BoardCommand) currentCommand).getBoard();
    }
}
