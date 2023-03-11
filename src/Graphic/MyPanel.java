package Graphic;

import BackEnd.DefaultParameter;

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
public class MyPanel extends JLayeredPane {
    private int startWidth = 0;
    private int startHeight = 0;
    /**
     * Khởi tạo không tham số
     */
    public MyPanel() {
        this.startWidth = DefaultParameter.panelStartWidth;
        this.startHeight = DefaultParameter.panelStartHeight;
        this.setPreferredSize(new Dimension(DefaultParameter.panelWidth, DefaultParameter.panelHeight));
        this.setBackground(Color.black);
        this.setLayout(null);
    }
    /**
     * Khởi tạo có tham số
     * @param width : chiều dài
     * @param height : chiều rộng
     */
    public MyPanel(int width, int height) {
        this.startWidth = DefaultParameter.panelStartWidth;
        this.startHeight = DefaultParameter.panelStartHeight;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setLayout(null);
    }

    public void setStartWidth(int startWidth) {
        this.startWidth = startWidth;
    }

    public int getStartWidth() {
        return startWidth;
    }

    public void setStartHeight(int startHeight) {
        this.startHeight = startHeight;
    }

    public int getStartHeight() {
        return startHeight;
    }
}
