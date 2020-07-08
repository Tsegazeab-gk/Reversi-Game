package ui;

import controller.GameWindowController;
import models.Screen;
import ui.widgets.DefaultButton;
import ui.widgets.RoundJTextField;
import util.Utils;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoteOptionScreen extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RemoteOptionScreen(GameWindowController gameWindowController) {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(150, 216, 300, 55);
		panel.setBackground( new Color(0, 0, 0, 100) );
		add(panel);
		panel.setLayout(null);

		JButton btnNewButton_1 = new DefaultButton("Human");
		btnNewButton_1.setBounds(5, 10, 73, 25);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new DefaultButton("AI");
		btnNewButton_2.setBounds(150, 10, 81, 25);
		panel.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(150, 71, 300, 132);
		panel_1.setBackground( new Color(0, 0, 0, 100) );
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("IP Address");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(90, 13, 275, 16);
		panel_1.add(lblNewLabel);

		textField = new RoundJTextField(40);
		textField.setBounds(80, 42, 116, 29);
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new DefaultButton("Connect");
		btnNewButton.setBounds(67, 85, 116, 25);
		panel_1.add(btnNewButton);

		JButton btnBack = FactoryUI.getBackButton();
		add(btnBack);

		btnBack.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.LOCATION_SETTING);
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
