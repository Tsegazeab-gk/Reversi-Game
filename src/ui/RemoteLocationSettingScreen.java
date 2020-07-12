package ui;

import controller.GameWindowController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteLocationSettingScreen extends JPanel {

    /**
     * Create the panel.
     */
    public RemoteLocationSettingScreen(GameWindowController gameWindowController) {
        setLayout(null);

        JButton btnNewButton = new DefaultButton("TCP Connection", 180, 40);
        btnNewButton.setBounds(200, 150, 200, 25);
        add(btnNewButton);

        JButton btnNewButton_1 = new DefaultButton("UDP Connection", 180, 40);
        btnNewButton_1.setBounds(200, 200, 200, 25);
        add(btnNewButton_1);

        JButton btnBack = new DefaultButton("<", 45, 40);
        btnBack.setBounds(12, 13, 97, 25);
        add(btnBack);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.REMOTE_LOCATION_SETTING, GameOption.TCP_CONNECTION);
            gameWindowController.changePage(Screen.REMOTE_TCP_OPTION);
        });

        btnNewButton_1.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.REMOTE_LOCATION_SETTING, GameOption.UDP_CONNECTION);
            gameWindowController.changePage(Screen.REMOTE_UDP_OPTION);
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