package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Kế thừa JLayeredPane (Bảng nhiều lớp của Java)
 * Lớp Custom để đặt các đối tượng
 * Tất cả các phần đồ họa đều được đặt trên bảng này, (trừ JFrame để đặt MyPanel trong JFrame)
 */

/**
 * Một số phương thức:
 * panel.repaint() : refresh bảng
 * panel.add() : thêm phần tử vào bảng
 * panel.remove() : xóa phần tử khỏi bảng ( sau đó cần refresh)
 */
public class MyPanel extends JLayeredPane implements MouseListener {
    // Các biến để xử lý chuột. Hiện chưa dùng
    public boolean isClicked = false;
    private int mouseX = 0;
    private int mouseY = 0;

    /**
     * Khởi tạo không tham số
     */
    public MyPanel() {
        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.addMouseListener(this);
    }

    /**
     * Khởi tạo có tham số
     * @param width : chiều dài
     * @param height : chiều rộng
     */
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

    /**
     * Ghi đè Graphics với Graphics2D
     * @param g  the Graphics context within which to paint
     */
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
