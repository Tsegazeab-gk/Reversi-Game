package ui;

import controller.GameWindowController;
import controller.RemoteHTTPOptionController;
import controller.RemoteTCPOptionController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import ui.widgets.RoundJTextField;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteHTTPOptionScreen extends JPanel {

    /**
     * Create the panel.
     */

    private JTextField addressTextField;
    private JButton btnConnection;
    private GameWindowController gameWindowController;
    private JPanel playerOptionPanel, formOptionPanel;
    private RemoteHTTPOptionController controller;
    private JLabel lblErrorMsg;

    public RemoteHTTPOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);
        this.gameWindowController = gameWindowController;

        formOptionPanel = new JPanel();
        formOptionPanel.setBounds(150, 71, 300, 150);
        formOptionPanel.setBackground(new Color(0, 0, 0, 100));
        add(formOptionPanel);
        formOptionPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("HTTP(S) Address");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(80, 13, 275, 16);
        formOptionPanel.add(lblNewLabel);

        // Connection Panel
        addressTextField = new RoundJTextField(40);
        addressTextField.setBounds(10, 42, 280, 29);
        formOptionPanel.add(addressTextField);
        addressTextField.setColumns(10);

        lblErrorMsg = new JLabel();
        lblErrorMsg.setForeground(Color.RED);
        lblErrorMsg.setBounds(70, 80, 180, 15);
        lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        formOptionPanel.add(lblErrorMsg);

        btnConnection = new DefaultButton("Connect");
        btnConnection.setBounds(85, 100, 116, 25);
        formOptionPanel.add(btnConnection);

        // Player Option Panel
        playerOptionPanel = new JPanel();
        playerOptionPanel.setBounds(150, 230, 300, 55);
        playerOptionPanel.setBackground(new Color(0, 0, 0, 100));
        playerOptionPanel.setLayout(null);
        playerOptionPanel.setVisible(false);
        add(playerOptionPanel);

        JButton btnHumanOption = new DefaultButton("Human");
        btnHumanOption.setBounds(5, 10, 73, 25);
        playerOptionPanel.add(btnHumanOption);

        JButton btnAIOption = new DefaultButton("AI");
        btnAIOption.setBounds(150, 10, 81, 25);
        playerOptionPanel.add(btnAIOption);

        JButton btnBack = FactoryUI.getBackButton();
        add(btnBack);

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });

        btnHumanOption.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.REMOTE_HTTP_OPTION, GameOption.HUMAN);
            gameWindowController.changePage(Screen.FORMAT_OPTION, Screen.GAME_PANEL);
        });

        btnAIOption.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.REMOTE_HTTP_OPTION, GameOption.AI);
            gameWindowController.changePage(Screen.FORMAT_OPTION, Screen.GAME_PANEL);
        });

        controller = new RemoteHTTPOptionController(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public JButton getBtnConnection() {
        return btnConnection;
    }

    public JPanel getPlayerOptionPanel() {
        return playerOptionPanel;
    }

    public JPanel getFormOptionPanel() {
        return formOptionPanel;
    }

    public JLabel getLblErrorMsg() {
        return lblErrorMsg;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public RemoteHTTPOptionController getController() {
        return this.controller;
    }


}
