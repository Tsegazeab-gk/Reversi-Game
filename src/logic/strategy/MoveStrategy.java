package logic.strategy;

public class MoveStrategy {

    IMoveStrategy moveStrategy;

    public void setMoveStrategy(IMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public IMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
}
