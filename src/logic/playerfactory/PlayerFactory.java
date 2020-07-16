package logic.playerfactory;


import models.GameOption;
import player.AIPlayer;
import player.Player;

public class PlayerFactory {

    private static PlayerFactory factory = new PlayerFactory();

    private PlayerFactory() {
    }

    public static PlayerFactory getFactory() {
        return factory;
    }

    public Player createPlayer(int mark, int depth, boolean isFirstUser, GameOption gameOption) {

        Player player = null;
        if(gameOption==null){
            gameOption= GameOption.AI;
        }
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
                break;
        }
        if (gameOption == null)
            player = new AIPlayer(mark,depth,isFirstUser,"hard");

        return player;
    }
}
