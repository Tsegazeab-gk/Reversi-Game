package controller;

import models.Screen;
import ui.FormatOptionScreen;
import ui.ScreenPanel;

import java.awt.event.ActionEvent;

public class FormatOptionController {
    private FormatOptionScreen formatOptionScreen;

    public FormatOptionController(FormatOptionScreen formatOptionScreen) {
        this.formatOptionScreen = formatOptionScreen;
        this.formatOptionScreen.getBtnValidate().addActionListener((ActionEvent event) -> {

        });
    }


}
