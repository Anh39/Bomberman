package Graphic;

import javax.swing.*;
import java.awt.*;

public abstract class StatusBar {
    public static Font font = new Font("Arial", Font.PLAIN,20);
    public static JTextField textField = new JTextField();
    public static void initialization() {
        textField.setBounds(0,0,250,50);
        textField.setOpaque(false);
        textField.setFont(font);
        Graphic.panel.add(textField,Integer.valueOf(10));
    }
}
