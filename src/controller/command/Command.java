package controller.command;

import java.awt.*;

public interface Command {
    public void execute();
    public boolean undo();

}
