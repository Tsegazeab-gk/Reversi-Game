package logic.factory;


import logic.levels.LevelFactory;
import models.GameOption;
import player.GamePlayer;
import player.HumanPlayer;
import player.ai.AIPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtimeKiller;

//this needs to get fixed
public class LevelFactoryImpl {//implements LevelFactory {

    private static LevelFactoryImpl factory = new LevelFactoryImpl();

    public LevelFactoryImpl() {
    }

    public static LevelFactoryImpl getFactory() {
        return factory;
    }

//    @Override
//    public GamePlayer createPlayer(int mark, int depth, boolean isFirstUser, String option) {
//
//        return new AIPlayer(mark,depth,isFirstUser,option);
////        GamePlayer player = null;
////        if (option.equals("easy")) {
////            player = new AIPlayer(mark,depth,isFirstUser,option);// new RandomPlayer(mark);
////        } else if (option.equals("hard")) {
////            player = new AIPlayerDynamic(mark, depth);
////        } else if (option.equals("superhard")) {
////            player = new AIPlayerRealtimeKiller(mark, depth, isFirstUser);
////        }
////        return player;
//    }

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
//                player = new AIPlayerRealtimeKiller(mark, depth, isFirstUser);
                break;
        }
        if (gameOption == null)
            player = new AIPlayer(mark,depth,isFirstUser,"hard");

        return player;
    }
}
