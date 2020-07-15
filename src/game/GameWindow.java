package game;

import controller.GameWindowController;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(){
        new GameWindowController(this);

    }

    public static void main(String[] args) {
        new GameWindow();
    }

}
