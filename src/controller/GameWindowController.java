package controller;

import game.GamePanel;
import game.GameWindow;
import models.GameOption;
import models.Screen;
import player.HumanPlayer;
import player.ai.AIPlayerDynamic;
import player.ai.AIPlayerRealtimeKiller;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameWindowController {
    private GameWindow gameWindow;
    private JPanel startScreen,
            localOptionScreen,
            remoteOptionScreen,
            locationSettingScreen,
            levelOptionScreen;
    private UserFormScreen userFormScreen;

    private Map<Screen, GameOption> optionMap = new HashMap<Screen, GameOption>();
    private Stack<JPanel> screenStack = new Stack<JPanel>();

    public GameWindowController(GameWindow gameWindow) {

        startScreen = new StartScreen(this);
        userFormScreen = new UserFormScreen(this);
        localOptionScreen = new LocalOptionScreen(this);
        remoteOptionScreen = new RemoteOptionScreen(this);
        locationSettingScreen = new LocationSettingScreen(this);
        levelOptionScreen = new LevelOptionScreen(this);

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
            case LEVEL_OPTION:
                panel = levelOptionScreen;
                break;
            case GAME_PANEL:
                panel = buildGame();
                break;
        }
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

    public JPanel buildGame() {
        GamePanel gamePanel = new GamePanel();
        //GamePanelBuilder().setLevel(...).setPlayer1(...).setPlayer1(...)
        if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.HUMAN_VS_HUMAN)) {
            HumanPlayer p1 = new HumanPlayer(1);
            p1.setName(userFormScreen.getPlay1Name());
            HumanPlayer p2 = new HumanPlayer(2);
            p2.setName(userFormScreen.getPlay2Name());
            //
            gamePanel.getController().setPlayer1(p1);
            gamePanel.getController().setPlayer2(p2);
        } else if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.AI_VS_AI)) {
            gamePanel.getController().setPlayer1(new AIPlayerRealtimeKiller(1, 6, true));
            gamePanel.getController().setPlayer2(new AIPlayerDynamic(2, 6));
        } else if (optionMap.get(Screen.LOCAL_OPTION).equals(GameOption.HUMAN_VS_AI)) {
            gamePanel.getController().setPlayer1(new HumanPlayer(1));
            gamePanel.getController().setPlayer2(new AIPlayerDynamic(2, 6));
        }
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
