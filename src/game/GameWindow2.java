package game;

import controller.GameWindowController;

import javax.swing.*;

public class GameWindow2 extends JFrame {

    public GameWindow2(){
        new GameWindowController(this);
    }

    public static void main(String[] args) {
        new GameWindow2();
    }

}
