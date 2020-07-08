package ui;

import controller.GameWindowController;
import models.Screen;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class StartScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public StartScreen(GameWindowController gameWindowController) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reversi Game");
		lblNewLabel.setBounds(181, 76, 97, 16);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(181, 155, 97, 25);
		add(btnNewButton);
		btnNewButton.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.LOCATION_SETTING);
		});

	}
}
