package ui;

import controller.GameWindowController;
import models.Screen;
import ui.widgets.DefaultButton;
import ui.widgets.RoundJTextField;
import util.Utils;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class UserFormScreen extends JPanel {
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Create the panel.
     */
    public UserFormScreen(GameWindowController gameWindowController) {
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(150, 37, 300, 90);
        add(panel);
        panel.setBackground( new Color(0, 0, 0, 100) );
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Player 1 Name");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(90, 19, 150, 16);
        panel.add(lblNewLabel);

        textField = new RoundJTextField(50);
        textField.setBounds(80, 48, 150, 29);
        panel.add(textField);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(150, 140, 300, 90);
        panel_1.setBackground( new Color(0, 0, 0, 100) );
        add(panel_1);

        JLabel lblPlayerName = new JLabel("Player 2 Name");
        lblPlayerName.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblPlayerName.setForeground(Color.WHITE);
        lblPlayerName.setBounds(90, 19, 150, 16);
        panel_1.add(lblPlayerName);

        textField_1 = new RoundJTextField(50);
        textField_1.setColumns(10);
        textField_1.setBounds(80, 48, 150, 29);
        panel_1.add(textField_1);

        JButton btnNewButton = new DefaultButton("Play");
        btnNewButton.setBounds(230, 262, 97, 25);
        add(btnNewButton);

        JButton btnBack = new DefaultButton("<", 45, 40);
        btnBack.setBounds(12, 13, 97, 25);
        add(btnBack);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnBack.addActionListener((ActionEvent event)->{
            gameWindowController.changePage(Screen.LOCATION_SETTING);
        });
    }

    public String getPlay1Name() {
        return textField.getText();
    }

    public String getPlay2Name() {
        return textField_1.getText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
