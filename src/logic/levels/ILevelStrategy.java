package logic.levels;



import logic.StatePattern.Evaluator;

import java.awt.*;

public interface ILevelStrategy {

    Point getNextMove(int[][] board, int player, int dept);
}
