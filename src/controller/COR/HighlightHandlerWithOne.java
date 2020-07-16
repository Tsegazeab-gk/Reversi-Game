package controller.COR;

import game.BoardCell;

import javax.swing.*;
import java.awt.*;

public class HighlightHandlerWithOne extends HighlightHandler {

    public HighlightHandlerWithOne() {
        this.nextHandler = new HighlightHandlerWithTwo();
    }

    @Override
    public void handle(int highlight, BoardCell boardCell, JPanel parent, Graphics g) {
        if(highlight ==1) {
            g.setColor(new Color(255, 255, 255));
            g.fillRect(0,0, boardCell.getWidth(), boardCell.getHeight());
            g.setColor(parent.getBackground());
            g.fillRect(4,4,boardCell.getWidth()-8,boardCell.getHeight()-8);
        } else {
            if(nextHandler != null)
                nextHandler.handle(highlight, boardCell, parent, g);
        }
    }
}
