package logic.factory;


import logic.levels.LevelFactory;
import models.GameOption;
import player.GamePlayer;
import player.HumanPlayer;
import player.ai.AIPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtime;
import player.ai.AIPlayerRealtimeKiller;

//this needs to get fixed
public class LevelFactoryImpl {//implements LevelFactory {

    private static LevelFactoryImpl factory = new LevelFactoryImpl();

    public LevelFactoryImpl() {
    }

    public static LevelFactoryImpl getFactory() {
        return factory;
    }



    public GamePlayer createPlayer(int mark, int depth, boolean isFirstUser, GameOption gameOption) {

        GamePlayer player = null;
        switch (gameOption) {
            case EASY_LEVEL:
                player = new AIPlayer(mark,depth,isFirstUser,"easy");
                break;
            case MEDIUM_LEVEL:
                player = new AIPlayer(mark,depth,isFirstUser,"medium");
                break;
            case HARD_LEVEL:
                player = new AIPlayer(mark,depth,isFirstUser,"hard");
                break;
            case AI:
                 player = new AIPlayer(mark,depth,isFirstUser,"");
                // player = new AIPlayerRealtime(mark, depth); //garanteed win
                break;
        }
        if (gameOption == null)
            player = new AIPlayer(mark,depth,isFirstUser,"hard");

        return player;
    }
}
