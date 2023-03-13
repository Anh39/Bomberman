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
    // Bảng để đặt vào cửa sổ
    public static MyPanel panel = new MyPanel();
    // Bảng nền
    public static MyPanel basePanel = new MyPanel();
    public static MyPanel menuPanel = new MyPanel();
    public static MyPanel statusPanel = new MyPanel();
    public static JLabel menuBackground = New.menuBackground();
    /**
     * Khởi tạo
     */
    public static void startGraphic() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(basePanel);

        basePanel.setPreferredSize(new Dimension(DefaultParameter.panelWidth,DefaultParameter.panelHeight));
        basePanel.add(panel,Integer.valueOf(1));
        basePanel.add(menuPanel,Integer.valueOf(2));

        panel.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        panel.add(menuBackground,Integer.valueOf(0));
        panel.add(statusPanel,Integer.valueOf(10));

        menuPanel.setBounds(0,0,DefaultParameter.menuPanelWidth,DefaultParameter.menuPanelHeight);
        menuPanel.setVisible(false);

        frame.pack();
        frame.setVisible(true);
        MyMenu.mainMenuInitialization(); // Khởi tạo menu
        MyMenu.addMainMenu(panel); // Ban đầu thêm menu
    }

    /**
     * Khởi tạo game sau khi đóng menu chính
     */
    public static void initialization() {
        menuBackground.setVisible(false);
        StatusBar.initialization();
        Render.initialize();
        MyClock.startClock();
        MainProcess.generateTerrain();
        KeyBoard.keyInitialization();
        MyMenu.subMenuInitialization();
        if (TestModule.testEnable) {
            TestModule.panelTestInitialize();
        }
    }
}
