package ui;

import controller.GameWindowController;
import models.Screen;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
        panel.setBounds(76, 37, 301, 90);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Player 1 Name");
        lblNewLabel.setBounds(106, 19, 92, 16);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(80, 48, 150, 29);
        panel.add(textField);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(76, 140, 301, 90);
        add(panel_1);

        JLabel lblPlayerName = new JLabel("Player 2 Name");
        lblPlayerName.setBounds(106, 19, 92, 16);
        panel_1.add(lblPlayerName);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(80, 48, 150, 29);
        panel_1.add(textField_1);

        JButton btnNewButton = new JButton("Play");
        btnNewButton.setBounds(183, 262, 97, 25);
        add(btnNewButton);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.GAME_PANEL);
        });
    }

    public String getPlay1Name() {
        return textField.getText();
    }

    public String getPlay2Name() {
        return textField_1.getText();
    }
}
