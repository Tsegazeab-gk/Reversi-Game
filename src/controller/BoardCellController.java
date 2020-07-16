package controller;

import controller.COR.HighlightHandler;
import controller.COR.HighlightHandlerFactory;
import game.BoardCell;
import game.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

public class BoardCellController{

    private BoardCell boardCell;
    int i;
    int j;

    private GameEngine ge;
    private JPanel parent;
    public int highlight = 2;
    public String text = "";

    private HighlightHandler highlightHandler = HighlightHandlerFactory.getHighlightHandler();
    
    public BoardCellController(BoardCell boardCell, GameEngine ge ,JPanel parent,int i,int j){
        this.ge = ge;
        this.parent = parent;
        this.i = i;
        this.j = j;
        this.boardCell=boardCell;
    }

    public void paint(Graphics g){
        int margin_left = this.boardCell.getWidth() / 10;
        int margin_top = this.boardCell.getHeight() / 10;



        //draw highlight
        highlightHandler.handle(highlight, boardCell, parent, g);


        //draw border
        g.setColor(Color.BLACK);
        g.drawRect(0,0,this.boardCell.getWidth(),this.boardCell.getHeight());

        //draw piece
        int value = ge.getBoardValue(i,j);

        //    highlight=value;

        if(value == 1){

            g.setColor(Color.BLACK);
            g.fillOval(margin_left,margin_top,this.boardCell.getWidth()-2*margin_left,this.boardCell.getHeight()-2*margin_top);
        }
        else if(value == 2) {

            g.setColor(Color.WHITE);
            g.fillOval(margin_left,margin_top,this.boardCell.getWidth()-2*margin_left,this.boardCell.getHeight()-2*margin_top);
        }

        if(!text.isEmpty()){
            g.setColor(new Color(255, 255, 0));
            Font font = g.getFont();
            Font nfont = new Font(font.getName(),Font.PLAIN,30);
            g.setFont(nfont);

            drawStringInCenterOfRectangle(g,0,0,this.boardCell.getWidth(),this.boardCell.getHeight(),text);
        }



    }

    public void drawStringInCenterOfRectangle(Graphics g,int x,int y,int w,int h,String text){
        Graphics2D g2 = (Graphics2D) g;
        Font bfont = g2.getFont();
        FontRenderContext context = g2.getFontRenderContext();
        g2.setFont(bfont);
        int textWidth = (int) bfont.getStringBounds(text, context).getWidth();
        LineMetrics ln = bfont.getLineMetrics(text, context);
        int textHeight = (int) (ln.getAscent() + ln.getDescent());
        int tx = x+(w - textWidth)/2;
        int ty = (int)((y + h + textHeight)/2 - ln.getDescent());
        g2.drawString(text, (int)tx, (int)ty);
    }

    public GameEngine getGe(){
        return this.ge;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }

    public void setHighlight(int h){
        this.highlight = h;
    }

    public int getHighlight(){
        return this.highlight;
    }

}
