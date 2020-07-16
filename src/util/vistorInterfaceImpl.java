package util;

import controller.GamePanelController;

public class vistorInterfaceImpl implements vistorInterface{
    @Override
    public boolean canPlay(GamePanelController gamecontroler,int[][] board, int player, int i, int j) {
        if(board[i][j] != 0) return false;

            int mi , mj , c;
            int oplayer = ((player == 1) ? 2 : 1);

            //move up
            mi = i - 1;
            mj = j;
            c = 0;
            while(mi>0 && board[mi][mj] == oplayer){
                mi--;
                c++;
            }
            if(mi>=0 && board[mi][mj] == player && c>0) return true;


            //move down
            mi = i + 1;
            mj = j;
            c = 0;
            while(mi<7 && board[mi][mj] == oplayer){
                mi++;
                c++;
            }
            if(mi<=7 && board[mi][mj] == player && c>0) return true;

            //move left
            mi = i;
            mj = j - 1;
            c = 0;
            while(mj>0 && board[mi][mj] == oplayer){
                mj--;
                c++;
            }
            if(mj>=0 && board[mi][mj] == player && c>0) return true;

            //move right
            mi = i;
            mj = j + 1;
            c = 0;
            while(mj<7 && board[mi][mj] == oplayer){
                mj++;
                c++;
            }
            if(mj<=7 && board[mi][mj] == player && c>0) return true;

            //move up left
            mi = i - 1;
            mj = j - 1;
            c = 0;
            while(mi>0 && mj>0 && board[mi][mj] == oplayer){
                mi--;
                mj--;
                c++;
            }
            if(mi>=0 && mj>=0 && board[mi][mj] == player && c>0) return true;

            //move up right
            mi = i - 1;
            mj = j + 1;
            c = 0;
            while(mi>0 && mj<7 && board[mi][mj] == oplayer){
                mi--;
                mj++;
                c++;
            }
            if(mi>=0 && mj<=7 && board[mi][mj] == player && c>0) return true;

            //move down left
            mi = i + 1;
            mj = j - 1;
            c = 0;
            while(mi<7 && mj>0 && board[mi][mj] == oplayer){
                mi++;
                mj--;
                c++;
            }
            if(mi<=7 && mj>=0 && board[mi][mj] == player && c>0) return true;

            //move down right
            mi = i + 1;
            mj = j + 1;
            c = 0;
            while(mi<7 && mj<7 && board[mi][mj] == oplayer){
                mi++;
                mj++;
                c++;
            }
            if(mi<=7 && mj<=7 && board[mi][mj] == player && c>0) return true;

            //when all hopes fade away
            return false;

    }
}
