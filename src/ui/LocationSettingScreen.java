package ui;

import controller.GameWindowController;
import models.Screen;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class LocationSettingScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public LocationSettingScreen(GameWindowController gameWindowController) {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Local");
		btnNewButton.setBounds(182, 95, 97, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remote");
		btnNewButton_1.setBounds(182, 185, 97, 25);
		add(btnNewButton_1);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 13, 97, 25);
		add(btnBack);

		btnNewButton.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.LOCAL_OPTION);
		});

		btnNewButton_1.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.REMOTE_OPTION);
		});

		btnBack.addActionListener((ActionEvent event)->{
			gameWindowController.changePage(Screen.START);
		});

	}

}
