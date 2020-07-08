package ui.widgets;

import javax.swing.*;
import java.awt.*;

public class DefaultButton extends JButton {

    public DefaultButton(String text){
        super(text);
        this.setFont(new Font("Calibri", Font.PLAIN, 22));
        this.setBackground(new Color(0x194E39));
        this.setForeground(Color.white);
        setUI(new StyledButtonUI());
    }

    public DefaultButton(String text, int width, int height){
        this(text);
        setUI(new StyledButtonUI(width, height));
    }

    public DefaultButton(String text, int width, int height, Icon icon){
        this(text);
        setUI(new StyledButtonUI(width, height));
        setIcon(icon);
    }

}
