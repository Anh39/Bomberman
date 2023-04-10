package Graphic;

import BackEnd.KeyState;
import BackEnd.MainProcess;
import BackEnd.MyClock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public abstract class KeyBoard {
    // Lưu trạng thái bàn phím
    public static ArrayList<KeyState> keyStates = new ArrayList<>();

    /**
     * Khởi tạo các trạng thái của bàn phím
     */
    public static void keyInitialization() {
        KeyState leftKey = new KeyState(37);
        KeyState upKey = new KeyState(38);
        KeyState rightKey = new KeyState(39);
        KeyState downKey = new KeyState(40);
        KeyState spaceKey = new KeyState(32);
        KeyState eKey = new KeyState(69);
        KeyState qKey = new KeyState(81);
        KeyState fKey = new KeyState(70);
        KeyState escKey = new KeyState(27);
        keyStates.add(leftKey);
        keyStates.add(rightKey);
        keyStates.add(upKey);
        keyStates.add(downKey);
        keyStates.add(spaceKey);
        keyStates.add(eKey);
        keyStates.add(qKey);
        keyStates.add(fKey);
        keyStates.add(escKey);
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
                playerInput(keyStates);
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
        Graphic.frame.addKeyListener(keyListener);
    }
    /**
     * Xử lý đầu vào từ bàn phím
     * @param keyStates : trạng thái của bàn phím
     */
    public static void playerInput(ArrayList<KeyState> keyStates) {
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getState()) {
                int key = keyStates.get(i).getKeyCode();
                if (key == 32) {
                    MainProcess.placeBomb(MainProcess.player);
                }
                if (key == 69) {
                    MainProcess.spawnEnemy();
                }
                if (key == 37 || key == 38 || key == 39 || key == 40) {
                    MyClock.playerMove(keyStates);
                }
                if (key == 81) {
                    MainProcess.spawnBuff();
                }
                if (key == 70) {
                    if (Graphic.statusPanel.isVisible()) {
                        Graphic.statusPanel.setVisible(false);
                    }
                    else {
                        Graphic.statusPanel.setVisible(true);
                    }
                }
                if (key == 27) {
                    if (Graphic.menuPanel.isVisible()) {
                        Graphic.menuPanel.setVisible(false);
                    }
                    else {
                        Graphic.menuPanel.setVisible(true);
                    }
                }
            }
        }
    }
}
