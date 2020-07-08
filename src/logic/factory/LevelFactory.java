package logic.factory;


import player.GamePlayer;

public interface LevelFactory {

    GamePlayer createPlayer(int mark, int depth, boolean isFirstUser, String option);
}
