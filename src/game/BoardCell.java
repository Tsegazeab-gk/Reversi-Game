package game;

import controller.BoardCellController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

public class BoardCell extends JLabel implements MouseListener{


    private BoardCellController boardCellController;

    public BoardCell(GameEngine ge ,JPanel parent,int i,int j){
        this.addMouseListener(this);
        boardCellController = new BoardCellController(this, ge, parent, i, j);
    }

    @Override
    public void paint(Graphics g) {
        this.boardCellController.paint(g);
        super.paint(g);
    }

    public void setHighlight(int h){
        this.boardCellController.setHighlight(h);
    }
    public int getHighlight(){
        return this.boardCellController.getHighlight();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.boardCellController.getGe().handleClick(this.boardCellController.getI(),this.boardCellController.getJ());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
