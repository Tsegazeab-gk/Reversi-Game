package controller.COR;

import game.BoardCell;

import javax.swing.*;
import java.awt.*;

public abstract class HighlightHandler {
    protected HighlightHandler nextHandler;

    public abstract void handle(int highlight, BoardCell boardCell, JPanel parent, Graphics g);
    public HighlightHandler getNextHandler() {
        return nextHandler;
    }
}
