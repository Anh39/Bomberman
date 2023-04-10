package Graphic;

import BackEnd.DefaultParameter;
import BackEnd.MainProcess;
import Entities.New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Lớp trừu tượng để xử lý phần menu
 */
public abstract class MyMenu {
    /**
     * Menu chính (Title game)
     */
    public static JButton newGame = New.menuButton();
    public static JButton loadGame = New.menuButton();
    public static JButton configGame = New.menuButton();
    public static JButton exitGame = New.menuButton();
    public static JTextArea tutorial = New.tutorial();
    /**
     * Menu phụ ( Khi vào game)
     */
    public static JButton miniNewGameButton = New.miniMenuButton();
    public static JButton miniSaveGameButton = New.miniMenuButton();
    public static JButton miniLoadGameButton = New.miniMenuButton();
    public static JButton miniConfigGameButton = New.miniMenuButton();
    public static JButton miniExitGameButton = New.miniMenuButton();
    /**
     * Đồ họa của menu save / load
     */
    // Lưu đồ họa của các file save
    public static ArrayList<JButton> datas = new ArrayList<>();
    // Nút X màu đỏ để thoát
    public static JButton exitSaveLoad = New.miniMenuButton();
    public static JTextArea saveText = New.statusField();
    public static JTextArea loadText = New.statusField();
    // Khoảng cách giữa các nút trong menu
    public static int yDistance = 50;
    // Khoảng cách giữa các file save
    public static int yDataDistance = 10;
    // Kiểm tra xem menu hiện tại là save hay load
    public static boolean isSaving = false;

    /**
     * Phương thức để khởi tạo menu chính
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
                    isSaving = false;
                    changeSaveLoad();
                    Graphic.saveLoadPanel.setVisible(true);
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
        tutorial.setText("Press arrow key to move.\nPress Space to place Bomb.\nPress E to spawn enemies.\nPress Esc to open menu in game.\nPress Q to spawn buff.\nPress F to hide status panel.");
        newGame.addActionListener(actionListener);
        loadGame.addActionListener(actionListener);
        configGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);
        newGame.setText("New Game");
        loadGame.setText("Load Game");
        configGame.setText("Config");
        exitGame.setText("Exit");
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
     * Thêm menu chính vào màn hình
     * @param panel
     */
    public static void addMainMenu(JLayeredPane panel) {
        panel.add(newGame,Integer.valueOf(1));
        panel.add(loadGame,Integer.valueOf(1));
        panel.add(configGame,Integer.valueOf(1));
        panel.add(exitGame,Integer.valueOf(1));
        panel.add(tutorial,Integer.valueOf(1));
    }

    /**
     * Xóa menu chính khỏi màn hình
     * @param panel
     */
    public static void removeMainMenu(JLayeredPane panel) {
        panel.remove(newGame);
        panel.remove(loadGame);
        panel.remove(configGame);
        panel.remove(exitGame);
        panel.remove(tutorial);
    }

    /**
     * Phương thức khởi tạo menu phụ
     */
    public static void subMenuInitialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == miniNewGameButton) {
                    MainProcess.newGame();
                }
                else if (e.getSource() == miniSaveGameButton) {
                    isSaving = true;
                    changeSaveLoad();
                    Graphic.saveLoadPanel.setVisible(true);
                }
                else if (e.getSource() == miniLoadGameButton) {
                    isSaving = false;
                    changeSaveLoad();
                    Graphic.saveLoadPanel.setVisible(true);
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
        miniNewGameButton.setText("New Game");
        miniSaveGameButton.setText("Save Game");
        miniLoadGameButton.setText("Load Game");
        miniConfigGameButton.setText("Config");
        miniExitGameButton.setText("Exit");
        miniNewGameButton.setLocation(0,0);
        miniSaveGameButton.setLocation(miniNewGameButton.getX()+miniNewGameButton.getWidth(),miniNewGameButton.getY());
        miniLoadGameButton.setLocation(miniSaveGameButton.getX()+miniSaveGameButton.getWidth(),miniSaveGameButton.getY());
        miniConfigGameButton.setLocation(miniLoadGameButton.getX()+miniLoadGameButton.getWidth(),miniLoadGameButton.getY());
        miniExitGameButton.setLocation(miniConfigGameButton.getX()+miniConfigGameButton.getWidth(),miniConfigGameButton.getY());

    }

    /**
     * Phương thức khởi tạo save load menu
     */
    public static void saveLoadMenuInitialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitSaveLoad) {
                    Graphic.saveLoadPanel.setVisible(false);
                }
                for (int i=0;i<datas.size();i++) {
                    if (e.getSource() == datas.get(i)) {
                        System.out.println("Save : " + (i+1) + " Clicked");
                    }
                }
            }
        };

        for (int i=0;i<DefaultParameter.maxSaveData;i++) {
            datas.add(New.miniMenuButton());
            if (datas.size() == 1) {
                datas.get(i).setLocation(50,50);
            }
            else {
                datas.get(i).setLocation(datas.get(i-1).getX(),datas.get(i-1).getY()+datas.get(i-1).getHeight()+yDataDistance);
            }
            datas.get(i).setText("Save " + (i+1));
            datas.get(i).addActionListener(actionListener);
            Graphic.saveLoadPanel.add(datas.get(i));
        }

        int size = datas.size();
        Graphic.saveLoadPanel.setBounds(0,0,100+datas.get(0).getWidth(),size*datas.get(0).getHeight()+(size-1)*yDataDistance+100);
        Graphic.saveLoadPanel.setVisible(false);

        saveText.setText("Save");
        loadText.setText("Load");
        Graphic.saveLoadPanel.add(saveText);
        saveText.setBounds(Graphic.saveLoadPanel.getWidth()/2-50,0,Graphic.saveLoadPanel.getWidth()/2,50);
        Graphic.saveLoadPanel.add(loadText);
        loadText.setBounds(Graphic.saveLoadPanel.getWidth()/2-50,0,Graphic.saveLoadPanel.getWidth()/2,50);

        Font font = new Font("Arial",Font.PLAIN,10);
        exitSaveLoad.setFont(font);
        exitSaveLoad.setBounds(0,0,40,40);
        exitSaveLoad.setBackground(Color.red);
        exitSaveLoad.setText("X");
        exitSaveLoad.setLocation(Graphic.saveLoadPanel.getWidth()-40,Graphic.saveLoadPanel.getY());
        exitSaveLoad.addActionListener(actionListener);

        Graphic.saveLoadPanel.add(exitSaveLoad,Integer.valueOf(1));

        changeSaveLoad();
    }

    /**
     * Chuyển đổi giữa save và load menu
     */
    public static void changeSaveLoad() {
        if (isSaving) {
            saveText.setVisible(true);
            loadText.setVisible(false);
        }
        else {
            saveText.setVisible(false);
            loadText.setVisible(true);
        }
    }
}
