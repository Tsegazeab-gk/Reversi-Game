package player.ai;


import logic.StatePattern.Evaluator;

import logic.levels.ILevelStrategy;
import logic.levels.LevelFactory;
import logic.levels.LevelStrategyImpl;
import player.GamePlayer;

import java.awt.*;

public class AIPlayer extends GamePlayer {

    String name = "AI";

    private int searchDepth;
    private boolean isFirstPlayer;

    private ILevelStrategy level;

    private LevelStrategyImpl levelStrategy;

    public AIPlayer(int mark, int depth, boolean firstplayer, String option) {
        super(mark);
        searchDepth = depth;
        this.isFirstPlayer = firstplayer;
        levelStrategy = new LevelStrategyImpl();

        if (option.equals("easy")) {
            level = LevelFactory.getFactory().createEasyLevel(mark);
        } else if (option.equals("hard")) {
//            level=LevelFactory.getFactory().createMediumLevel(mark, depth);
            level = LevelFactory.getFactory().createDifficultLevel(mark, depth, firstplayer);
        } else {
            //level=LevelFactory.getFactory().createDifficultLevel(mark,depth,firstplayer);
            level = LevelFactory.getFactory().createMediumLevel(mark, depth);
        }
        levelStrategy.setLevelStrategy(level);


        //System.out.println("Strategy created");

    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String getPlayerName() {
        // return "Dynamic AI (Depth " + searchDepth + ")";
        return this.name;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.name = playerName;
    }

    @Override
    public Point play(int[][] board) {
        // return Minimax.solve(board,myMark,searchDepth,evaluator);
        return levelStrategy.getLevelStrategy().getNextMove(board, myMark, searchDepth);

        // return strategy.getMoveStrategy().solve(board,myMark,searchDepth,evaluator);
    }
}
