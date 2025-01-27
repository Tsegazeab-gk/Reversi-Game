package ui;

import controller.GameWindowController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LocationSettingScreen extends JPanel {

    /**
     * Create the panel.
     */
    public LocationSettingScreen(GameWindowController gameWindowController) {
        setLayout(null);

        JButton btnNewButton = new DefaultButton("Local");
        btnNewButton.setBounds(220, 150, 97, 25);
        add(btnNewButton);

        JButton btnNewButton_1 = new DefaultButton("Remote");
        btnNewButton_1.setBounds(220, 200, 97, 25);
        add(btnNewButton_1);

        JButton btnBack = new DefaultButton("<", 45, 40);
        btnBack.setBounds(12, 13, 97, 25);
        add(btnBack);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.LOCATION_SETTING, GameOption.LOCAL);
            gameWindowController.changePage(Screen.LOCAL_OPTION);
        });

        btnNewButton_1.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.LOCATION_SETTING, GameOption.REMOTE);
            gameWindowController.changePage(Screen.REMOTE_LOCATION_SETTING);
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
