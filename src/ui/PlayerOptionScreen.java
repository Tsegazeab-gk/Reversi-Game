package ui;

import controller.GameWindowController;
import models.PlayerType;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlayerOptionScreen extends ScreenPanel {

    /**
     * Create the panel.
     */

    private GameWindowController gameWindowController;
    private JRadioButton rdbButtonBlack, rdbButtonWhite;
    private Screen nextScreen;

    public PlayerOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);
        this.gameWindowController = gameWindowController;

        JPanel panel = new JPanel();
        panel.setBounds(150, 50, 300, 230);
        panel.setBackground(new Color(0, 0, 0, 100));
        add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.BLACK);
                g.fillOval(0, 0, 80, 80);
                g.drawOval(0, 0, 80, 80);
            }
        };
        panel_1.setBackground(new Color(255, 255, 255, 0));
        panel_1.setBounds(45, 54, 80, 80);
        panel.add(panel_1);

        JPanel panel_1_1 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.WHITE);
                g.fillOval(0, 0, 80, 80);
                g.drawOval(0, 0, 80, 80);
            }
        };
        panel_1_1.setBackground(new Color(255, 255, 255, 0));
        panel_1_1.setBounds(177, 54, 80, 80);
        panel.add(panel_1_1);

        ButtonGroup buttonGroup = new ButtonGroup();

        rdbButtonBlack = new JRadioButton("Black");
        rdbButtonBlack.setBounds(40, 146, 87, 25);
        //rdbButtonBlack.setBackground(new Color(255, 255, 255, 0));
        rdbButtonBlack.setSelected(true);
        rdbButtonBlack.setForeground(Color.BLACK);
        buttonGroup.add(rdbButtonBlack);
        panel.add(rdbButtonBlack);

        rdbButtonWhite = new JRadioButton("White");
        rdbButtonWhite.setBounds(177, 146, 87, 25);
        //rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 255, 0));
        rdbButtonWhite.setForeground(Color.BLACK);
        buttonGroup.add(rdbButtonWhite);
        panel.add(rdbButtonWhite);

        JLabel lblNewLabel = new JLabel("Player Option");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(25, 20, 234, 20);
        panel.add(lblNewLabel);

        JButton btnNewButton = new DefaultButton("Validate");
        btnNewButton.setBounds(92, 180, 97, 25);
        panel.add(btnNewButton);

        btnNewButton.addActionListener((ActionEvent event) -> {
            if (nextScreen != null) {
                gameWindowController.changePage(nextScreen);
            } else {
                gameWindowController.changePage(Screen.GAME_PANEL);
            }
        });

        JButton btnBack = FactoryUI.getBackButton();
        add(btnBack);

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });
    }

    public PlayerType getSelectedPlayer() {
        if (rdbButtonBlack.isSelected()) {
            return PlayerType.BLACK;
        } else {
            return PlayerType.WHITE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }
}
