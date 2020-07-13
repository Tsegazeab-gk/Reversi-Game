package ui;

import models.Screen;

import javax.swing.*;

public abstract class ScreenPanel extends JPanel {
    public abstract void setNextScreen(Screen nextScreen);
}
