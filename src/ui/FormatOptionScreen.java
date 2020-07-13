package ui;

import controller.FormatOptionController;
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

public class FormatOptionScreen extends ScreenPanel {

    /**
     * Create the panel.
     */

    private JButton btnValidate;
    private GameWindowController gameWindowController;
    private JPanel formOptionPanel;
    private JLabel lblErrorMsg;
    private JTextField rowFormatTextField, colFormatPortTextField;
    private FormatOptionController formatOptionController;
    private Screen nextScreen;

    public FormatOptionScreen(GameWindowController gameWindowController) {
        setLayout(null);
        this.gameWindowController = gameWindowController;

        formOptionPanel = new JPanel();
        formOptionPanel.setBounds(150, 24, 300, 200);
        formOptionPanel.setBackground(new Color(0, 0, 0, 100));
        add(formOptionPanel);
        formOptionPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Format Option");
        lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(12, 25, 200, 16);
        formOptionPanel.add(lblNewLabel);

        // Row Format
        JLabel lblNewLabel_1 = new JLabel("Row");
        lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(12, 77, 130, 16);
        formOptionPanel.add(lblNewLabel_1);

        rowFormatTextField = new RoundJTextField(40);
        rowFormatTextField.setBounds(12, 106, 120, 22);
        rowFormatTextField.setText("x");
        formOptionPanel.add(rowFormatTextField);

        // Column Format
        JLabel lblNewLabel_2 = new JLabel("Column");
        lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 22));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(150, 77, 120, 16);
        formOptionPanel.add(lblNewLabel_2);

        colFormatPortTextField = new RoundJTextField(40);
        colFormatPortTextField.setBounds(150, 106, 130, 22);
        colFormatPortTextField.setText("y");
        formOptionPanel.add(colFormatPortTextField);

        lblErrorMsg = new JLabel();
        lblErrorMsg.setForeground(Color.RED);
        lblErrorMsg.setBounds(12, 133, 300, 16);
        lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        formOptionPanel.add(lblErrorMsg);

        btnValidate = new DefaultButton("Validate");
        btnValidate.setBounds(88, 155, 116, 25);
        formOptionPanel.add(btnValidate);

        btnValidate.addActionListener((ActionEvent event) -> {
            System.out.println(nextScreen);
            if (nextScreen != null){
                gameWindowController.changePage(Screen.GAME_PANEL);
            }
        });

        JButton btnBack = FactoryUI.getBackButton();
        add(btnBack);

        btnBack.addActionListener((ActionEvent event) -> {
            gameWindowController.goBack();
        });

        formatOptionController = new FormatOptionController(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public JButton getBtnValidate() {
        return btnValidate;
    }

    public JPanel getFormOptionPanel() {
        return formOptionPanel;
    }

    public JTextField getRowFormatTextField() {
        return rowFormatTextField;
    }

    public JTextField getColFormatPortTextField() {
        return colFormatPortTextField;
    }

    public JLabel getLblErrorMsg() {
        return lblErrorMsg;
    }

    public FormatOptionController getController() {
        return formatOptionController;
    }


    @Override
    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }

}