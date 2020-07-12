package ui;

import controller.GameWindowController;
import controller.RemoteUDPOptionController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import ui.widgets.RoundJTextField;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteUDPOptionScreen extends JPanel {

    /**
     * Create the panel.
     */

    private JTextField addressTextField;
    private JLabel loading;
    private JButton btnConnection;
    private GameWindowController gameWindowController;
    private JPanel playerOptionPanel, formOptionPanel;
    private JButton btnCancel;
    private JLabel lblErrorMsg;
    private RemoteUDPOptionController controller;
    private JTextField portTextField;

    public RemoteUDPOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);
        this.gameWindowController = gameWindowController;

        formOptionPanel = new JPanel();
        formOptionPanel.setBounds(150, 24, 300, 200);
        formOptionPanel.setBackground(new Color(0, 0, 0, 100));
        add(formOptionPanel);
        formOptionPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("URL Server Address");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(12, 13, 200, 16);
        formOptionPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Port");
        lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(12, 77, 200, 16);
        formOptionPanel.add(lblNewLabel_1);

        portTextField = new RoundJTextField(40);
        portTextField.setBounds(12, 106, 276, 22);
        formOptionPanel.add(portTextField);

        // Connection Panel
        addressTextField = new RoundJTextField(40);
        addressTextField.setBounds(12, 42, 276, 22);
        formOptionPanel.add(addressTextField);
        addressTextField.setColumns(10);

        lblErrorMsg = new JLabel();
        lblErrorMsg.setForeground(Color.RED);
        lblErrorMsg.setBounds(12, 133, 300, 16);
        lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        formOptionPanel.add(lblErrorMsg);

        btnConnection = new DefaultButton("Connect");
        btnConnection.setBounds(88, 155, 116, 25);
        formOptionPanel.add(btnConnection);


        loading = new JLabel(new ImageIcon(Utils.getResoursePath("ajax-loader.gif")), JLabel.CENTER);
        loading.setBounds(80, 95, 116, 25);
        loading.setVisible(false);
        formOptionPanel.add(loading);

        btnCancel = new DefaultButton("X", 45, 40);
        btnCancel.setBounds(210, 85, 97, 25);
        btnCancel.setVisible(false);
        formOptionPanel.add(btnCancel);

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
            gameWindowController.setOption(Screen.REMOTE_UDP_OPTION, GameOption.HUMAN);
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        btnAIOption.addActionListener((ActionEvent event) -> {
            gameWindowController.setOption(Screen.REMOTE_UDP_OPTION, GameOption.AI);
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        controller = new RemoteUDPOptionController(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public JLabel getLoading() {
        return loading;
    }

    public JButton getBtnConnection(){return btnConnection;}

    public JPanel getPlayerOptionPanel() {
        return playerOptionPanel;
    }

    public JPanel getFormOptionPanel(){return formOptionPanel;}

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public JTextField getPortTextField(){return  portTextField;}

    public JLabel getLblErrorMsg() {
        return lblErrorMsg;
    }

    public RemoteUDPOptionController getController() {
        return this.controller;
    }

}