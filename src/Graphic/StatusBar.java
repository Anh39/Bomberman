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
    private static JTextArea basicStatus = New.statusField();
    private static JTextArea playerScoreStatus = New.statusField();

    /**
     * Khởi tạo thanh trạng thái
     */
    public static void initialization() {
        statusPanel.setBounds(0,0,250,500);
        statusPanel.setLocation(0,50);

        basicStatus.setBounds(0,0,basicStatus.getWidth(),basicStatus.getHeight()*4);

        statusPanel.add(basicStatus,Integer.valueOf(5));
        statusPanel.add(playerScoreStatus,Integer.valueOf(5));

        basicStatus.setLocation(0,0);
        playerScoreStatus.setLocation(basicStatus.getX(), basicStatus.getY()+ basicStatus.getHeight());
    }

    /**
     * Làm mới trạng thái
     */
    public static void updateStatusPanel() {
        String temp = "";
        temp += "Heath : " + MainProcess.player.getHeath();
        temp += "\nDamage : " + MainProcess.player.getDamage();
        temp += "\nSpeed : " + MainProcess.player.getSpeed();
        temp += "\nRange : " + MainProcess.player.getRange();
        basicStatus.setText(temp);
        playerScoreStatus.setText("Score : " + MainProcess.player.getScore());
    }
}
