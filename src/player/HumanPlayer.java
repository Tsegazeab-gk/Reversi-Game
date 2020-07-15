package player;

import java.awt.*;

public class HumanPlayer extends GamePlayer {

    String name;

    public HumanPlayer(int mark, String name) {
        super(mark);
        this.name=name;
    }

    @Override
    public boolean isUserPlayer() {
        return true;
    }

    @Override
    public String getPlayerName()
    {
        return this.name ;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.name=playerName;
    }

    @Override
    public Point play(int[][] board) {
        return null;
    }

}
