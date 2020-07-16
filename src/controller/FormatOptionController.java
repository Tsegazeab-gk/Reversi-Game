package controller;

import ui.FormatOptionScreen;

import java.awt.event.ActionEvent;

public class FormatOptionController {
    private FormatOptionScreen formatOptionScreen;

    public FormatOptionController(FormatOptionScreen formatOptionScreen) {
        this.formatOptionScreen = formatOptionScreen;
        this.formatOptionScreen.getBtnValidate().addActionListener((ActionEvent event) -> {

        });
    }


}
