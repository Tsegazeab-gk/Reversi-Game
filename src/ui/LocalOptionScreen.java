package ui;

import controller.GameWindowController;
import models.GameOption;
import models.Screen;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LocalOptionScreen extends JPanel {

	/**
	 * Create the panel.
	 */
	public LocalOptionScreen(GameWindowController gameWindowController) {
		setLayout(null);

		JButton btnNewButton_2 = new DefaultButton("Human vs Human", 220, 40);
		btnNewButton_2.setBounds(200, 100, 154, 25);
		add(btnNewButton_2);

		JButton btnNewButton = new DefaultButton("AI vs AI", 220, 40);
		btnNewButton.setBounds(200, 150, 154, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new DefaultButton("Human vs AI", 220, 40);
		btnNewButton_1.setBounds(200, 200, 154, 25);
		add(btnNewButton_1);

		JButton btnBack = FactoryUI.getBackButton();
		add(btnBack);

		btnNewButton.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.AI_VS_AI);
			gameWindowController.changePage(Screen.LEVEL_OPTION);
		});

		btnNewButton_1.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.HUMAN_VS_AI);
			gameWindowController.changePage(Screen.USER_FORM);
		});

		btnNewButton_2.addActionListener((ActionEvent event)->{
			gameWindowController.setOption(Screen.LOCAL_OPTION, GameOption.HUMAN_VS_HUMAN);
			gameWindowController.changePage(Screen.USER_FORM);
		});

		btnBack.addActionListener((ActionEvent event) -> {
			gameWindowController.goBack();
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
