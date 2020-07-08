package ui;

import controller.GameWindowController;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RemoteOptionScreen extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RemoteOptionScreen(GameWindowController gameWindowController) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP Address");
		lblNewLabel.setBounds(188, 109, 75, 16);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(162, 138, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setBounds(162, 173, 116, 25);
		add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(89, 230, 256, 35);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Human");
		btnNewButton_1.setBounds(36, 5, 73, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("AI");
		btnNewButton_2.setBounds(136, 5, 81, 25);
		panel.add(btnNewButton_2);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		add(btnBack);

	}

}
