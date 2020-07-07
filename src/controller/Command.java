package controller;

import java.awt.*;

public interface Command {
    public void execute();
    public boolean undo();

}
