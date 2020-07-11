package game;

import controller.observer.Observer;

//interface that gui speaks to
public interface GameEngine {

    public int getBoardValue(int i,int j);

    public void setBoardValue(int i,int j,int value);

    public void handleClick(int i,int j);

    public void notifyObservers();
    void attach(Observer observer);
    void detach(Observer observer);

}
