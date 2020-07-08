package logic.strategy;



import logic.StatePattern.Evaluator;

import java.awt.*;

public interface IMoveStrategy {

    Point solve(int[][] board, int player, int depth, Evaluator e);
}
