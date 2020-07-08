package controller;

import game.GamePanel;
import game.GameWindow;
import models.Screen;
import ui.*;

import javax.swing.*;
import java.awt.*;

public class GameWindowController {
    private GameWindow gameWindow;
    private JPanel startScreen,
            userFormScreen,
            localOptionScreen,
            remoteOptionScreen,
            locationSettingScreen;

    public GameWindowController(GameWindow gameWindow) {

        startScreen = new StartScreen(this);
        userFormScreen = new UserFormScreen(this);
        localOptionScreen = new LocalOptionScreen(this);
        remoteOptionScreen = new RemoteOptionScreen(this);
        locationSettingScreen = new LocationSettingScreen(this);

        gameWindow.setTitle("Reversi v0.1");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setSize(new Dimension(500, 350));
        gameWindow.getContentPane().add(new StartScreen(this));
        this.gameWindow = gameWindow;
    }

    public void changePage(Screen screen) {
        JPanel panel = null;
        switch (screen) {
            case START:
                panel = startScreen;
                break;
            case USER_FORM:
                panel = userFormScreen;
                break;
            case LOCAL_OPTION:
                panel = localOptionScreen;
                break;
            case REMOTE_OPTION:
                panel = remoteOptionScreen;
                break;
            case LOCATION_SETTING:
                panel = locationSettingScreen;
                break;
            case GAME_PANEL:
                panel = new GamePanel();
                break;
        }
        gameWindow.getContentPane().removeAll();
        gameWindow.getContentPane().add(panel);
        gameWindow.revalidate();
    }
}
