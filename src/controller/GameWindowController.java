package controller;

import controller.builder.GamePanelBuilder;
import game.GamePanel;
import game.GameWindow;
import logic.factory.LevelFactoryImpl;
import models.GameOption;
import models.Screen;
import player.GamePlayer;
import player.HumanPlayer;
import player.ai.AIPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtimeKiller;
import services.network.ConnectedUser;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameWindowController {

    private JFrame gameWindow;
    private JPanel startScreen,
            localOptionScreen,
            locationSettingScreen,
            levelOptionScreen,
            remoteLocationSettingScreen;
    private UserFormScreen userFormScreen;
    private RemoteTCPOptionScreen remoteTCPOptionScreen;
    private RemoteUDPOptionScreen remoteUDPOptionScreen;
    private RemoteHTTPOptionScreen remoteHTTPOptionScreen;
    private FormatOptionScreen formatOptionScreen;
    private PlayerOptionScreen playerOptionScreen;

    private Map<Screen, GameOption> optionMap = new HashMap<Screen, GameOption>();
    private Stack<JPanel> screenStack = new Stack<JPanel>();

    public GameWindowController(JFrame gameWindow) {

        startScreen = new StartScreen(this);
        userFormScreen = new UserFormScreen(this);
        localOptionScreen = new LocalOptionScreen(this);
        remoteLocationSettingScreen = new RemoteLocationSettingScreen(this);
        remoteTCPOptionScreen = new RemoteTCPOptionScreen(this);
        remoteUDPOptionScreen = new RemoteUDPOptionScreen(this);
        remoteHTTPOptionScreen = new RemoteHTTPOptionScreen(this);
        locationSettingScreen = new LocationSettingScreen(this);
        levelOptionScreen = new LevelOptionScreen(this);
        formatOptionScreen = new FormatOptionScreen(this);
        playerOptionScreen = new PlayerOptionScreen(this);

        gameWindow.setTitle("Reversi Game");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setSize(new Dimension(600, 400));
        gameWindow.getContentPane().add(startScreen);
        gameWindow.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        gameWindow.setLocation(dim.width / 2 - gameWindow.getSize().width / 2, dim.height / 2 - gameWindow.getSize().height / 2);
        this.gameWindow = gameWindow;
    }

    private JPanel routingScreen(Screen screen) {
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
            case REMOTE_TCP_OPTION:
                panel = remoteTCPOptionScreen;
                break;
            case REMOTE_UDP_OPTION:
                panel = remoteUDPOptionScreen;
                break;
            case REMOTE_HTTP_OPTION:
                panel = remoteHTTPOptionScreen;
                break;
            case REMOTE_LOCATION_SETTING:
                panel = remoteLocationSettingScreen;
                break;
            case LOCATION_SETTING:
                panel = locationSettingScreen;
                break;
            case LEVEL_OPTION:
                panel = levelOptionScreen;
                break;
            case FORMAT_OPTION:
                panel = formatOptionScreen;
                break;
            case PLAYER_OPTION:
                panel= playerOptionScreen;
                break;
            case GAME_PANEL:
                panel = buildGame();
                break;
        }

        if (screen == Screen.GAME_PANEL) {
            gameWindow.setSize(new Dimension(700, 550));
        } else {
            gameWindow.setSize(new Dimension(600, 400));
        }

        return panel;
    }

    public void changePage(Screen screen) {
        JPanel panel = routingScreen(screen);

        setPanel(panel);
        screenStack.add(panel);
    }

    public void changeWithNextPage(Screen screen, Screen nextScreen) {
        ScreenPanel panel = (ScreenPanel) routingScreen(screen);
        panel.setNextScreen(nextScreen);

        setPanel(panel);
        screenStack.add(panel);
    }

    public void goBack() {
        if (!screenStack.empty()) {
            setPanel(screenStack.pop());
        } else {
            setPanel(startScreen);
        }
    }

    public GamePanel buildGame() {
        GamePlayer p1 = null, p2 = null;
        ConnectedUser connectedUser = null;

        if (optionMap.get(Screen.LOCATION_SETTING).equals(GameOption.LOCAL)) {
            GameOption levelOption = optionMap.get(Screen.LEVEL_OPTION);

            if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.HUMAN_VS_HUMAN)) {
                //number=userFormScreen.getStart()2
                p1 = new HumanPlayer(1,userFormScreen.getPlay1Name());
                p2 = new HumanPlayer(2,userFormScreen.getPlay2Name());
            } else if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.AI_VS_AI)) {
                p1 = LevelFactoryImpl.getFactory().createPlayer(1, 6, true, levelOption);
                p2 = LevelFactoryImpl.getFactory().createPlayer(2, 6, false, levelOption);
            } else if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.HUMAN_VS_AI)) {
                p1 = new HumanPlayer(1,userFormScreen.getPlay1Name());
                p2 = LevelFactoryImpl.getFactory().createPlayer(1, 6, true, levelOption);
            }

        } else {

            if (optionMap.get(Screen.REMOTE_LOCATION_SETTING).equals(GameOption.TCP_CONNECTION)) {
                GameOption levelOption = optionMap.get(Screen.REMOTE_TCP_OPTION);

                if (optionMap.get(Screen.REMOTE_TCP_OPTION).equals(GameOption.HUMAN)) {
                    p1 = new HumanPlayer(1,userFormScreen.getPlay1Name());
                    p2 = new HumanPlayer(2,userFormScreen.getPlay2Name());
                } else if (optionMap.get(Screen.REMOTE_TCP_OPTION).equals(GameOption.AI)) {
                    p1 = LevelFactoryImpl.getFactory().createPlayer(1, 6, true, levelOption);
                    p2=LevelFactoryImpl.getFactory().createPlayer(2, 6, false, levelOption);
                }
                connectedUser = remoteTCPOptionScreen.getController().getConnectedUser();

            } else if (optionMap.get(Screen.REMOTE_LOCATION_SETTING).equals(GameOption.UDP_CONNECTION)) {
                GameOption levelOption = optionMap.get(Screen.REMOTE_UDP_OPTION);

                if (optionMap.get(Screen.REMOTE_UDP_OPTION).equals(GameOption.HUMAN)) {
                    p1 = new HumanPlayer(1,userFormScreen.getPlay1Name());
                    p2 = new HumanPlayer(2,userFormScreen.getPlay2Name());

                } else if (optionMap.get(Screen.REMOTE_UDP_OPTION).equals(GameOption.AI)) {
                    p1 = LevelFactoryImpl.getFactory().createPlayer(1, 6, true, levelOption);
                    p2=LevelFactoryImpl.getFactory().createPlayer(2, 6, false, levelOption);
                }
                connectedUser = remoteUDPOptionScreen.getController().getConnectedUser();

            } else if (optionMap.get(Screen.REMOTE_LOCATION_SETTING).equals(GameOption.HTTP_CONNECTION)) {
                GameOption levelOption = optionMap.get(Screen.REMOTE_HTTP_OPTION);

                if (optionMap.get(Screen.REMOTE_HTTP_OPTION).equals(GameOption.HUMAN)) {
                    p1 = new HumanPlayer(1,"Player 1");
                    p2 = new HumanPlayer(2,"Player 2");
                } else if (optionMap.get(Screen.REMOTE_HTTP_OPTION).equals(GameOption.AI)) {
                    p1 = LevelFactoryImpl.getFactory().createPlayer(1, 6, true, levelOption);
                    p2 = new HumanPlayer(2,"Player 2");
                }
                connectedUser = remoteHTTPOptionScreen.getController().getConnectedUser();

            }

        }

        GamePanel gamePanel = new GamePanelBuilder.Builder()
                .setPlayerOne(p1)
                .setPlayerTwo(p2)
                .setConnectedUser(connectedUser)
                .build();
        gamePanel.getController().start();
        return gamePanel;
    }

    public void setOption(Screen screen, GameOption option) {
        this.optionMap.put(screen, option);
    }

    private void setPanel(JPanel panel) {
        gameWindow.getContentPane().remove(0);
        gameWindow.getContentPane().add(panel, 0);
        gameWindow.revalidate();
        gameWindow.repaint();
    }
}
