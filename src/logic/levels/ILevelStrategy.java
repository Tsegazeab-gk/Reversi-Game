package logic.levels;


import java.awt.*;

public interface ILevelStrategy {

    Point getNextMove(int[][] board, int player, int dept);
}
