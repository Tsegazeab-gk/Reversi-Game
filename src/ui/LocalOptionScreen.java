package ui;

import controller.GameWindowController;
import models.Screen;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class LocalOptionScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public LocalOptionScreen(GameWindowController gameWindowController) {
		setLayout(null);
		
		JButton btnNewButton = new JButton("AI vs AI");
		btnNewButton.setBounds(155, 124, 154, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Human vs AI");
		btnNewButton_1.setBounds(155, 181, 154, 25);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Human vs Human");
		btnNewButton_2.setBounds(155, 66, 154, 25);
		add(btnNewButton_2);

		btnNewButton.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.GAME_PANEL);
		});

		btnNewButton_1.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.GAME_PANEL);
		});

		btnNewButton_2.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.GAME_PANEL);
		});

	}

}
