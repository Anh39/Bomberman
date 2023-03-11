package Graphic;

import BackEnd.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Lớp trừu tượng xứ lý đồ họa nói chung
 */
public abstract class Graphic {
    // Lưu trạng thái bàn phím
    public static ArrayList<KeyState> keyStates = new ArrayList<>();
    // Khung hình , cửa số chính
    public static JFrame frame = new JFrame();
    // Bảng để đặt vào cửa sổ
    public static MyPanel panel = new MyPanel();

    /**
     * Khởi tạo
     */
    public static void startGraphic() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        Menu.initialization(); // Khởi tạo menu
        Menu.addMenu(panel); // Ban đầu thêm menu
    }

    /**
     * Khởi tạo game sau khi đóng menu chính
     */
    public static void initialization() {
        MyClock.startClock();
        MainProcess.generateTerrain();
        keyInitialization();
        StatusBar.initialization();
    }

    // Key Event
    /**
     * Khởi tạo trạng thái bàn phím
     */
    private static void keyInitialization() {
        KeyState leftKey = new KeyState(37);
        KeyState upKey = new KeyState(38);
        KeyState rightKey = new KeyState(39);
        KeyState downKey = new KeyState(40);
        KeyState spaceKey = new KeyState(32);
        KeyState eKey = new KeyState(69);
        keyStates.add(leftKey);
        keyStates.add(rightKey);
        keyStates.add(upKey);
        keyStates.add(downKey);
        keyStates.add(spaceKey);
        keyStates.add(eKey);
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            /**
             * Phương thức mặc định của bàn phím
             * @param e the event to be processed
             */
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                for (int i = 0; i < keyStates.size(); i++) {
                    if (keyStates.get(i).getKeyCode() == e.getKeyCode()) {
                        keyStates.get(i).setState(true);
                    }
                }
                MainProcess.playerInput(keyStates);
            }
            /**
             * Phương thức mặc định của bàn phím
             * @param e the event to be processed
             */
            @Override
            public void keyReleased(KeyEvent e) {
                for (int i = 0; i < keyStates.size(); i++) {
                    if (keyStates.get(i).getKeyCode() == e.getKeyCode()) {
                        keyStates.get(i).setState(false);
                    }
                }
            }
        };
        frame.addKeyListener(keyListener);
    }
}
