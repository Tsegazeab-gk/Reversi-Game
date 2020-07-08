package logic.strategy;

public class MoveStrategyImpl {

    IMoveStrategy moveStrategy;

    public void setMoveStrategy(IMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public IMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
}
