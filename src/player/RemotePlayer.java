package player;

import game.GamePlayer;

import java.awt.*;

public class RemotePlayer extends GamePlayer {

    public RemotePlayer(int mark) {
        super(mark);
    }

    @Override
    public boolean isUserPlayer() {
        return true;
    }

    @Override
    public String playerName() {
        return "Remote" ;
    }

    @Override
    public Point play(int[][] board) {
        return null;
    }

}
