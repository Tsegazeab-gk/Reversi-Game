package util;

import controller.GamePanelController;

public interface vistorInterface {
    //boolean isGameFinished(int[][] board);
    boolean canPlay(GamePanelController gameControl,int[][] board, int player, int i, int j);

}
