package logic.levels;

public class LevelStrategyImpl {

    ILevelStrategy levelStrategy;

    public void setLevelStrategy(ILevelStrategy levelStrategy) {

        this.levelStrategy = levelStrategy;
    }

    public ILevelStrategy getLevelStrategy() {
        return levelStrategy;
    }
}
