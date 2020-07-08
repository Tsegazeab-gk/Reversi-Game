package logic.strategy;

import logic.Evaluator;

import java.awt.*;

public interface IMoveStrategy {

    Point solve(int[][] board, int player, int depth, Evaluator e);
}
