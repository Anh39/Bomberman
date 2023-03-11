package Graphic;

import Entities.New;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lớp trừu tượng để xử lý phần menu
 */
public abstract class Menu{
    public static JButton newGame = New.newGameButton();
    public static JButton loadGame = New.loadGameButton();
    public static JButton configGame = New.configGameButton();
    public static JButton exitGame = New.exitGameButton();
    public static JTextArea tutorial = New.tutorial();
    // Khoảng cách giữa các nút trong menu
    public static int yDistance = 50;

    /**
     * Phương thức để khởi tạo lớp
     */
    public static void initialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == newGame) {
                    System.out.println("New game button clicked");
                    removeMenu(Graphic.panel); // Xóa menu
                    Render.rendering = true; // Bật render
                    Graphic.initialization(); // Khởi tạo graphic
                }
                else if (e.getSource() == loadGame) {
                    System.out.println("Load game button clicked");
                }
                else if (e.getSource() == configGame) {
                    System.out.println("Config game button clicked");
                }
                else if (e.getSource() == exitGame) {
                    System.out.println("Exit game button clicked");
                }
            }
        };
        tutorial.setText("Press arrow key to move.\nPress Space to place Bomb.\nPress E to spawn enemies.");
        tutorial.setFocusable(false);
        newGame.addActionListener(actionListener);
        newGame.setFocusable(false);
        loadGame.addActionListener(actionListener);
        loadGame.setFocusable(false);
        configGame.addActionListener(actionListener);
        configGame.setFocusable(false);
        exitGame.addActionListener(actionListener);
        exitGame.setFocusable(false);
        newGame.setBounds(100,100,200,75);
        loadGame.setBounds(newGame.getX(),newGame.getY()+newGame.getHeight()+yDistance,newGame.getWidth(),newGame.getHeight());
        configGame.setBounds(loadGame.getX(),loadGame.getY()+loadGame.getHeight()+yDistance,loadGame.getWidth(),loadGame.getHeight());
        exitGame.setBounds(configGame.getX(),configGame.getY()+configGame.getHeight()+yDistance,configGame.getWidth(),configGame.getHeight());
    }

    /**
     * Thêm menu vào màn hình
     * @param panel
     */
    public static void addMenu(MyPanel panel) {
        panel.add(newGame,Integer.valueOf(1));
        panel.add(loadGame,Integer.valueOf(1));
        panel.add(configGame,Integer.valueOf(1));
        panel.add(exitGame,Integer.valueOf(1));
        panel.add(tutorial,Integer.valueOf(1));
        panel.repaint();
    }

    /**
     * Xóa menu khỏi màn hình
     * @param panel
     */
    public static void removeMenu(MyPanel panel) {
        panel.remove(newGame);
        panel.remove(loadGame);
        panel.remove(configGame);
        panel.remove(exitGame);
        panel.remove(tutorial);
        panel.repaint();
    }
}
