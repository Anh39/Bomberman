package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPanel extends JLayeredPane implements MouseListener {

    public boolean isClicked = false;
    private int mouseX = 0;
    private int mouseY = 0;

    public MyPanel() {
        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addMouseListener(this);
    }

    public MyPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addMouseListener(this);
    }

    public int getMouseX() {
        if (getMousePosition() != null) {
            mouseX = this.getMousePosition().x;
        }
        return mouseX;
    }

    public int getMouseY() {
        if (getMousePosition() != null) {
            mouseY = this.getMousePosition().y;
        }
        return mouseY;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
