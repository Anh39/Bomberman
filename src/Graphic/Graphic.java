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
    public static MyPanel saveLoadPanel = new MyPanel();
    // JLabel để chỉnh hình nền
    public static JLabel menuBackground = New.menuBackground();
    /**
     * Khởi tạo đồ họa
     */
    public static void startGraphic() {
        // Chỉnh cửa số chính
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(basePanel);

        // Chỉnh bảng nền
        basePanel.setPreferredSize(new Dimension(DefaultParameter.panelWidth,DefaultParameter.panelHeight));
        basePanel.add(panel,Integer.valueOf(1));
        basePanel.add(menuPanel,Integer.valueOf(2));

        // Chỉnh
        panel.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        basePanel.add(menuBackground,Integer.valueOf(0));
        basePanel.add(statusPanel,Integer.valueOf(10));
        basePanel.add(saveLoadPanel,Integer.valueOf(15));

        // Chỉnh bảng save/load
        saveLoadPanel.setOpaque(true);
        saveLoadPanel.setBackground(Color.WHITE);
        saveLoadPanel.setLocation(150,100);

        // Chỉnh bảng menu
        menuPanel.setBounds(0,0,DefaultParameter.menuPanelWidth,DefaultParameter.menuPanelHeight);
        menuPanel.setVisible(false);

        frame.pack();
        frame.setVisible(true);

        MyMenu.saveLoadMenuInitialization(); // Khỏi tạo menu save/load
        MyMenu.mainMenuInitialization(); // Khởi tạo menu
        MyMenu.addMainMenu(panel); // Ban đầu thêm menu


    }

    /**
     * Khởi tạo game sau khi đóng menu chính
     */
    public static void initialization() {
        menuBackground.setVisible(false);
        StatusBar.initialization(); // Khỏi tạo thanh trạng thái
        Render.initialize(); // Khỏi tạo render
        MyClock.startClock(); // Khỏi tạo đồng hồ
        MainProcess.generateTerrain(); // Khởi tạo địa hình
        KeyBoard.keyInitialization(); // Khởi tạo trạng thái phím
        MyMenu.subMenuInitialization(); // Khỏi tạo menu phụ
        if (TestModule.testEnable) {
            TestModule.panelTestInitialize();
        }
    }
}
