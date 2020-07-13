package ui;

import controller.GameWindowController;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartScreen extends JPanel {

    /**
     * Create the panel.
     */
    public StartScreen(GameWindowController gameWindowController) {
        setLayout(null);

        DefaultButton btnNewButton = new DefaultButton("Start");
        btnNewButton.setBounds(220, 250, 97, 25);
        add(btnNewButton);
        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.LOCATION_SETTING);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("reversi-bg.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
