package ui;

import controller.GameWindowController;
import controller.RemoteOptionController;
import models.Screen;
import ui.widgets.DefaultButton;
import ui.widgets.RoundJTextField;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteOptionScreen extends JPanel {

    /**
     * Create the panel.
     */

    private JTextField iPAddressTextField;
    private JLabel loading;
    private JButton btnSendRequest;
    private JButton btnAcceptRequest;
    private GameWindowController gameWindowController;
    private JPanel playerOptionPanel;
    private JButton btnCancel;

    public RemoteOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);
        this.gameWindowController = gameWindowController;

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(150, 71, 300, 132);
        panel_1.setBackground(new Color(0, 0, 0, 100));
        add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("IP Address");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(90, 13, 275, 16);
        panel_1.add(lblNewLabel);

        // Player Option Panel
        playerOptionPanel = new JPanel();
        playerOptionPanel.setBounds(150, 216, 300, 55);
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

        // ====================================
        iPAddressTextField = new RoundJTextField(40);
        iPAddressTextField.setBounds(80, 42, 116, 29);
        panel_1.add(iPAddressTextField);
        iPAddressTextField.setColumns(10);

        btnSendRequest = new DefaultButton("Send");
        btnSendRequest.setBounds(5, 85, 116, 25);
        panel_1.add(btnSendRequest);

        btnAcceptRequest = new DefaultButton("Accept");
        btnAcceptRequest.setBounds(153, 85, 116, 25);
        panel_1.add(btnAcceptRequest);

        loading = new JLabel(new ImageIcon(Utils.getResoursePath("ajax-loader.gif")), JLabel.CENTER);
        loading.setBounds(80, 95, 116, 25);
        loading.setVisible(false);
        panel_1.add(loading);

        btnCancel = new DefaultButton("X", 45, 40);
        btnCancel.setBounds(210, 85, 97, 25);
        btnCancel.setVisible(false);
        panel_1.add(btnCancel);

        JButton btnBack = FactoryUI.getBackButton();
        add(btnBack);

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });

        btnHumanOption.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.USER_FORM);
        });

        btnAIOption.addActionListener((ActionEvent event) -> {
            gameWindowController.changePage(Screen.GAME_PANEL);
        });

        new RemoteOptionController(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public JLabel getLoading() {
        return loading;
    }

    public JButton getBtnSendRequest() {
        return btnSendRequest;
    }

    public JButton getBtnAcceptRequest() {
        return btnAcceptRequest;
    }

    public JPanel getPlayerOptionPanel() {
        return playerOptionPanel;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JTextField getiPAddressTextField(){
        return iPAddressTextField;
    }

}
