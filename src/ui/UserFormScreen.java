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
    private JPanel formPanel1;
    private JPanel formPanel2;

    /**
     * Create the panel.
     */
    public UserFormScreen(GameWindowController gameWindowController) {
        setLayout(null);

        formPanel1 = new JPanel();
        formPanel1.setBounds(150, 37, 300, 90);
        add(formPanel1);
        formPanel1.setBackground(new Color(0, 0, 0, 100));
        formPanel1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Player 1 Name");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(90, 19, 150, 16);
        formPanel1.add(lblNewLabel);

        textField = new RoundJTextField(50);
        textField.setBounds(80, 48, 150, 29);
        formPanel1.add(textField);
        textField.setColumns(10);

        formPanel2 = new JPanel();
        formPanel2.setLayout(null);
        formPanel2.setBounds(150, 140, 300, 90);
        formPanel2.setBackground(new Color(0, 0, 0, 100));
        add(formPanel2);

        JLabel lblPlayerName = new JLabel("Player 2 Name");
        lblPlayerName.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblPlayerName.setForeground(Color.WHITE);
        lblPlayerName.setBounds(90, 19, 150, 16);
        formPanel2.add(lblPlayerName);

        textField_1 = new RoundJTextField(50);
        textField_1.setColumns(10);
        textField_1.setBounds(80, 48, 150, 29);
        formPanel2.add(textField_1);

        JButton btnNewButton = new DefaultButton("Next");
        btnNewButton.setBounds(230, 262, 97, 25);
        add(btnNewButton);

        JButton btnBack = new DefaultButton("<", 45, 40);
        btnBack.setBounds(12, 13, 97, 25);
        add(btnBack);

        btnNewButton.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });
    }

    public String getPlay1Name() {
        return textField.getText();
    }

    public String getPlay2Name() {
        return textField_1.getText();
    }

    public void setEnableForm1(boolean enabled) {
        formPanel1.setEnabled(enabled);
    }

    public void setEnableForm2(boolean enabled) {
        formPanel1.setEnabled(enabled);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
