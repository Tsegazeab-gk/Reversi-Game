package ui;

import ui.widgets.DefaultButton;

public class FactoryUI {
    public static DefaultButton getBackButton(){
        DefaultButton btnBack = new DefaultButton("<", 45, 40);
        btnBack.setBounds(12, 13, 97, 25);
        return btnBack;
    }
}
