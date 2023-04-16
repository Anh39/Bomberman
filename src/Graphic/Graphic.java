package Graphic;

import BackEnd.*;
import Entities.New;

import javax.swing.*;
import java.awt.*;

/**
 * Lớp trừu tượng xứ lý đồ họa nói chung
 */
public abstract class Graphic {
    // Khung hình , cửa số chính
    public static JFrame frame = new JFrame();
    // Bảng nền để đặt vào cửa số
    public static MyPanel basePanel = new MyPanel();
    // Bảng để đặt đồ họa game
    // Note: Tác vụ đồ họa chính diễn ra ở đây
    public static MyPanel panel = new MyPanel();
    // Bảng chứa menu
    public static MyPanel menuPanel = new MyPanel();
    // Bảng chưa thanh trạng thái
    public static MyPanel statusPanel = new MyPanel();
    // Bảng chứa bảng save/load
    public static MyPanel settingPanel = new MyPanel();
    // JLabel để chỉnh hình nền
    public static JLabel menuBackground = New.menuBackground();
    //
    public static MyPanel gameOver = New.gameOver();
    /**
     * Khởi tạo đồ họa
     */
    public static void startGraphic() {
        // Chỉnh cửa số chính
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(basePanel);

        // Chỉnh bảng nền
        basePanel.setBackground(Color.BLACK);
        basePanel.setPreferredSize(new Dimension(DefaultParameter.panelWidth,DefaultParameter.panelHeight));
        basePanel.add(panel,Integer.valueOf(1));
        basePanel.add(menuPanel,Integer.valueOf(2));

        //
        panel.setBounds(0, 0, DefaultParameter.panelWidth, DefaultParameter.panelHeight);
        basePanel.add(menuBackground,Integer.valueOf(0));
        basePanel.add(statusPanel,Integer.valueOf(10));
        basePanel.add(settingPanel,Integer.valueOf(20));

        // Chỉnh bảng setting
        settingPanel.setOpaque(true);
        settingPanel.setBackground(DefaultParameter.menuColor);
        settingPanel.setBounds(250,50,850,650);

        // Chỉnh bảng menu
        menuPanel.setBounds(0,0,DefaultParameter.menuPanelWidth,DefaultParameter.menuPanelHeight);
        menuPanel.setVisible(true);

        statusPanel.setVisible(false);

        frame.pack();
        frame.setVisible(true);

        MyMenu.mainMenuInitialization(); // Khởi tạo menu
        MyMenu.addMainMenu(basePanel); // Ban đầu thêm menu
        MyMenu.showMainMenu();
        MyMenu.settingMenuInitialization();
        MyClock.startClock(); // Khỏi tạo đồng hồ
        StatusBar.initialization(); // Khỏi tạo thanh trạng thái
        MyMenu.subMenuInitialization(); // Khỏi tạo menu phụ
        basePanel.add(gameOver,Integer.valueOf(39));
        gameOver.setVisible(false);
    }

    /**
     * Khởi tạo game sau khi đóng menu chính
     */
    public static void initialization() {
        gameOver.setVisible(false);
        menuBackground.setVisible(false);
        Render.initialize(); // Khỏi tạo render
        if (DefaultParameter.adventureMode) {
            panel.setBounds(0,0,DefaultParameter.panelWidth*DefaultParameter.adventureModeX, DefaultParameter.panelHeight*DefaultParameter.adventureModeY);
        }
        SpawnManager.spawnPlayer();
        MainProcess.generateTerrain(); // Khởi tạo địa hình
        KeyBoard.keyInitialization(); // Khởi tạo trạng thái phím
        if (DefaultParameter.adventureMode) {
            AdventureMode.panelTestInitialize();
        }
    }
}
