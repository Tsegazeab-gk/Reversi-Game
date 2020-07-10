package player;

import java.awt.*;

public abstract class GamePlayer {

    public int myMark;

    public GamePlayer(int mark){
        myMark = mark;
    }

    abstract public boolean isUserPlayer();

    abstract public String getPlayerName();

    abstract public void setPlayerName(String playerName);

    abstract public Point play(int[][] board);

}
