package Graphic;

import BackEnd.DefaultParameter;
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
    public static JButton miniSaveGameButton = New.miniSaveGameButton();
    public static JButton miniLoadGameButton = New.miniLoadGameButton();
    public static JButton miniConfigGameButton = New.miniConfigGameButton();
    public static JButton miniExitGameButton = New.miniExitGameButton();
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
                    System.exit(0);
                }
            }
        };
        tutorial.setText("Press arrow key to move.\nPress Space to place Bomb.\nPress E to spawn enemies.\nPress Esc to open menu in game");
        newGame.addActionListener(actionListener);
        loadGame.addActionListener(actionListener);
        configGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);
        newGame.setBounds(0,0,200,75);
        loadGame.setBounds(0,0,200,75);
        configGame.setBounds(0,0,200,75);
        exitGame.setBounds(0,0,200,75);
        newGame.setLocation(DefaultParameter.panelWidth-newGame.getWidth()-100,100);
        loadGame.setLocation(newGame.getX(),newGame.getY()+yDistance+newGame.getHeight());
        configGame.setLocation(loadGame.getX(),loadGame.getY()+yDistance+loadGame.getHeight());
        exitGame.setLocation(configGame.getX(),configGame.getY()+yDistance+configGame.getHeight());
        tutorial.setLocation(configGame.getX()-tutorial.getWidth()-100,configGame.getY());

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
                else if (e.getSource() == miniSaveGameButton) {
                }
                else if (e.getSource() == miniLoadGameButton) {
                }
                else if (e.getSource() == miniConfigGameButton) {
                }
                else if (e.getSource() == miniExitGameButton) {
                    System.exit(0);
                }
            }
        };
        Graphic.menuPanel.add(miniNewGameButton);
        Graphic.menuPanel.add(miniSaveGameButton);
        Graphic.menuPanel.add(miniLoadGameButton);
        Graphic.menuPanel.add(miniConfigGameButton);
        Graphic.menuPanel.add(miniExitGameButton);
        miniNewGameButton.addActionListener(actionListener);
        miniSaveGameButton.addActionListener(actionListener);
        miniLoadGameButton.addActionListener(actionListener);
        miniConfigGameButton.addActionListener(actionListener);
        miniExitGameButton.addActionListener(actionListener);
        miniNewGameButton.setLocation(0,0);
        miniSaveGameButton.setLocation(miniNewGameButton.getX()+miniNewGameButton.getWidth(),miniNewGameButton.getY());
        miniLoadGameButton.setLocation(miniSaveGameButton.getX()+miniSaveGameButton.getWidth(),miniSaveGameButton.getY());
        miniConfigGameButton.setLocation(miniLoadGameButton.getX()+miniLoadGameButton.getWidth(),miniLoadGameButton.getY());
        miniExitGameButton.setLocation(miniConfigGameButton.getX()+miniConfigGameButton.getWidth(),miniConfigGameButton.getY());

    }
    public static void addSubMenu() {
        Graphic.menuPanel.repaint();
    }
    public static void removeSubMenu() {
        Graphic.menuPanel.repaint();
    }
}
