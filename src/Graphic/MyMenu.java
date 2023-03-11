package Graphic;

import BackEnd.MainProcess;
import Entities.New;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Lớp trừu tượng để xử lý phần menu
 */
public abstract class MyMenu {
    public static JButton newGame = New.newGameButton();
    public static JButton loadGame = New.loadGameButton();
    public static JButton configGame = New.configGameButton();
    public static JButton exitGame = New.exitGameButton();
    public static JTextArea tutorial = New.tutorial();
    public static JButton miniNewGameButton = New.miniNewGameButton();
    // Khoảng cách giữa các nút trong menu
    public static int yDistance = 50;

    /**
     * Phương thức để khởi tạo lớp
     */
    public static void mainMenuInitialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == newGame) {
                    System.out.println("New game button clicked");
                    removeMainMenu(Graphic.panel); // Xóa menu
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
        tutorial.setText("Press arrow key to move.\nPress Space to place Bomb.\nPress E to spawn enemies.\nPress Esc to open menu in game");
        newGame.addActionListener(actionListener);
        loadGame.addActionListener(actionListener);
        configGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);
        newGame.setBounds(100,100,200,75);
        loadGame.setBounds(newGame.getX(),newGame.getY()+newGame.getHeight()+yDistance,newGame.getWidth(),newGame.getHeight());
        configGame.setBounds(loadGame.getX(),loadGame.getY()+loadGame.getHeight()+yDistance,loadGame.getWidth(),loadGame.getHeight());
        exitGame.setBounds(configGame.getX(),configGame.getY()+configGame.getHeight()+yDistance,configGame.getWidth(),configGame.getHeight());
    }
    /**
     * Thêm menu vào màn hình
     * @param panel
     */
    public static void addMainMenu(JLayeredPane panel) {
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
    public static void removeMainMenu(JLayeredPane panel) {
        panel.remove(newGame);
        panel.remove(loadGame);
        panel.remove(configGame);
        panel.remove(exitGame);
        panel.remove(tutorial);
        panel.repaint();
    }

    public static void subMenuInitialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == miniNewGameButton) {
                    MainProcess.newGame();
                }
            }
        };
        miniNewGameButton.addActionListener(actionListener);
        miniNewGameButton.setBounds(0,0,100,50);
    }
    public static void addSubMenu() {
        Graphic.basePanel.add(miniNewGameButton);
        Graphic.basePanel.repaint();
    }
    public static void removeSubMenu() {
        Graphic.basePanel.remove(miniNewGameButton);
        Graphic.basePanel.repaint();
    }
}
