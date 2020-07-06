package game;

public class Board {

    protected int[][] position;


    public int getBoardValue(int i,int j)
    {
        return position[i][j];
    }


    public void setBoardValue(int i,int j,int value){
        position[i][j] = value;
    }
}
