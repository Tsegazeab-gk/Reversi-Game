package ui;

import controller.GameWindowController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LevelOptionScreen extends JPanel {

    /**
     * Create the panel.
     */
    public LevelOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);

        JButton btnNewButton_2 = new DefaultButton("Easy", 220, 40);
        btnNewButton_2.setBounds(200, 100, 154, 25);
        add(btnNewButton_2);

        JButton btnNewButton = new DefaultButton("Medium", 220, 40);
        btnNewButton.setBounds(200, 150, 154, 25);
        add(btnNewButton);

        JButton btnNewButton_1 = new DefaultButton("Hard", 220, 40);
        btnNewButton_1.setBounds(200, 200, 154, 25);
        add(btnNewButton_1);

        JButton btnBack = FactoryUI.getBackButton();
        add(btnBack);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.LEVEL_OPTION, GameOption.EASY_LEVEL);
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnNewButton_1.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.LEVEL_OPTION, GameOption.MEDIUM_LEVEL);
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnNewButton_2.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.LEVEL_OPTION, GameOption.HARD_LEVEL);
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

}
