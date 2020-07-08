package logic.factory;

import game.GamePlayer;

public interface LevelFactory {

    GamePlayer  createPlayer(int mark, int depth, boolean isFirstUser, String option);
}
