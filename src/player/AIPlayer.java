package player;


import logic.levels.ILevelStrategy;
import logic.levels.LevelFactory;
import logic.levels.LevelStrategyImpl;

import java.awt.*;

public class AIPlayer extends Player {

    String name="AI Team 5 Player";

    private int searchDepth;
    private boolean isFirstPlayer;

    private ILevelStrategy level;

    private LevelStrategyImpl levelStrategy;

    public AIPlayer(int mark, int depth,boolean firstplayer,String option) {
        super(mark);
        searchDepth = depth;
        this.isFirstPlayer=firstplayer;
        levelStrategy=new LevelStrategyImpl();

        if(option.equals("easy")){
            level= LevelFactory.getFactory().createEasyLevel(mark);
        }else if(option.equals("hard")){

            level=LevelFactory.getFactory().createDifficultLevel(mark,depth,firstplayer);
        }else {
            level=LevelFactory.getFactory().createMediumLevel(mark, depth);
        }
        levelStrategy.setLevelStrategy(level);

    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String getPlayerName() {
        return this.name;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.name=playerName;
    }

    @Override
    public Point play(int[][] board)
    {
        return levelStrategy.getLevelStrategy().getNextMove(board,myMark,searchDepth);
    }
}
