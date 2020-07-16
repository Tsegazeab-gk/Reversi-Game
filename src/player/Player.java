package player;

import java.awt.*;

public abstract class Player {

    public int myMark;

    public Player(int mark){
        myMark = mark;
    }

    abstract public boolean isUserPlayer();

    abstract public String getPlayerName();

    abstract public void setPlayerName(String playerName);

    abstract public Point play(int[][] board);

}
