package ui;

import controller.GameWindowController;
import models.GameOption;
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

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		add(btnBack);

		btnNewButton.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.AI_VS_AI);
			gameWindowController.changePage(Screen.GAME_PANEL);
		});

		btnNewButton_1.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.HUMAN_VS_AI);
			gameWindowController.changePage(Screen.GAME_PANEL);
		});

		btnNewButton_2.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.HUMAN_VS_HUMAN);
			gameWindowController.changePage(Screen.USER_FORM);
		});

		btnBack.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.LOCATION_SETTING);
		});

	}

}
