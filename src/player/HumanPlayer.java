package player;

import java.awt.*;

public class HumanPlayer extends GamePlayer {
    private String name = "User";
    public HumanPlayer(int mark) {
        super(mark);
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean isUserPlayer() {
        return true;
    }

    @Override
    public String playerName() {
        return this.name ;
    }

    @Override
    public Point play(int[][] board) {
        return null;
    }

}
