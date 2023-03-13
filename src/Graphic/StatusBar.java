package Graphic;

import BackEnd.MainProcess;
import Entities.*;

import javax.swing.*;
import java.awt.*;

/**
 * Lớp trừu tượng cho việc xử lý thanh máu , thanh trạng thái
 * Note : Chưa làm nhiều
 */
public abstract class StatusBar {
    private static MyPanel statusPanel = Graphic.statusPanel;
    public static Font font = new Font("Arial", Font.PLAIN,20);
    private static JTextField heathStatus = New.statusField();
    private static JTextField playerScoreStatus = New.statusField();
    public static void initialization() {
        statusPanel.setBounds(0,0,250,500);
        statusPanel.setLocation(0,50);

        statusPanel.add(heathStatus,Integer.valueOf(5));
        statusPanel.add(playerScoreStatus,Integer.valueOf(5));

        heathStatus.setLocation(0,0);
        playerScoreStatus.setLocation(heathStatus.getX(),heathStatus.getY()+heathStatus.getHeight());
    }
    public static void updateStatusPanel() {
        heathStatus.setText("Heath : " + MainProcess.player.getHeath());
        playerScoreStatus.setText("Score : " + MainProcess.player.getScore());
    }
}
