package controller.command;

public interface Command {
    public void execute();
    public boolean undo();

}
