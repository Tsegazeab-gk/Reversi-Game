package logic.factory;


import player.GamePlayer;
import player.HumanPlayer;
import player.RandomPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtimeKiller;

//this needs to get fixed
public class LevelFactoryImpl implements LevelFactory{

    private static LevelFactoryImpl factory=new LevelFactoryImpl();

    public LevelFactoryImpl() {
    }

    public static LevelFactoryImpl getFactory() {
        return factory;
    }

    @Override
    public GamePlayer createPlayer(int mark, int depth, boolean isFirstUser, String option) {

        GamePlayer player=null;
if(option.equals("easy")){
    player=new RandomPlayer(mark);
}else if(option.equals("hard")){
    player=new AIPlayerDynamic(mark,depth);
}else if(option.equals("superhard")){
    player = new AIPlayerRealtimeKiller(mark,depth,isFirstUser);
}
        return player;
    }
}
