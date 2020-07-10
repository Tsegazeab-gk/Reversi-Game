package controller.COR;

import game.BoardCell;

import javax.swing.*;
import java.awt.*;

public class HighLightHandlerWithTen extends HighlightHandler {

    public HighLightHandlerWithTen() {

    }

    @Override
    public void handle(int highlight, BoardCell boardCell, JPanel parent, Graphics g) {
        if(highlight == 10){
            g.setColor(new Color(177, 43, 71));
            g.fillRect(0,0,boardCell.getWidth(),boardCell.getHeight());
        } else {
            if(nextHandler != null)
                nextHandler.handle(highlight, boardCell, parent, g);
        }
    }
}
