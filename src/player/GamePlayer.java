package player;

import game.GamePanel;

import java.awt.*;

public abstract class GamePlayer {

    public int myMark;

    public GamePlayer(int mark){
        myMark = mark;
    }
    public void handleAITemplate(int[][] board, GamePlayer player, int turn, controller.GamePanelController gamePanelController, GamePanel gamePanel,controller.command.Invoker invoker) {
        Point aiPlayPoint = player.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;

        gamePanelController.board =invoker.getNewBoardAfterMove(board, aiPlayPoint, turn);
        gamePanelController.turn= (turn == 1) ? 2 : 1;
        gamePanel.repaint();
    }

        abstract public boolean isUserPlayer();

    abstract public String getPlayerName();

    abstract public void setPlayerName(String playerName);

    abstract public Point play(int[][] board);

}
