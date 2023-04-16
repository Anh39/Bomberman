package Graphic;

import BackEnd.*;
import Entities.New;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    public static JButton configGame = New.menuButton();
    public static JButton exitGame = New.menuButton();
    public static JTextArea tutorial = New.tutorial();
    /**
     * Menu phụ ( Khi vào game)
     */
    public static JButton miniNewGameButton = New.miniMenuButton();
    public static JButton miniConfigGameButton = New.miniMenuButton();
    public static JButton miniExitGameButton = New.miniMenuButton();
    public static JButton newGameButton2 = New.miniMenuButton();
    /**
     * Setting
     */
    public static JLabel difficulty = New.slider();
    public static JLabel backgroundMusicSetting = New.slider();
    public static JLabel soundEffectSetting = New.slider();
    public static JLabel systemEffectSetting = New.slider();
    public static JLabel adModeX = New.slider();
    public static JLabel adModeY = New.slider();
    public static JLabel canEnemyPlaceBomb = New.checkBox();
    public static JLabel intersectDamage = New.checkBox();
    public static JLabel enemyDamageToPlayer = New.checkBox();
    public static JLabel enemyDamageToTerrain = New.checkBox();
    public static JLabel enemyDamageToEnemy = New.checkBox();
    public static JLabel playerDamageToPlayer = New.checkBox();
    public static JLabel playerDamageToTerrain = New.checkBox();
    public static JLabel playerDamageToEnemy = New.checkBox();
    public static JLabel canBombCauseDamage = New.checkBox();
    public static JLabel adventureMode = New.checkBox();
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
                    SoundPlayer.soundClick();
                    System.out.println("New game button clicked");
                    hideMainMenu(); // Xóa menu
                    Render.rendering = true; // Bật render
                    Graphic.initialization(); // Khởi tạo graphic
                    MainProcess.newGame();
                }
                else if (e.getSource() == configGame) {
                    SoundPlayer.soundClick();
                    System.out.println("Config game button clicked");
                    if (Graphic.settingPanel.isVisible()) {
                        Graphic.settingPanel.setVisible(false);
                    } else {
                        Graphic.settingPanel.setVisible(true);
                    }
                }
                else if (e.getSource() == exitGame) {
                    SoundPlayer.soundClick();
                    System.out.println("Exit game button clicked");
                    System.exit(0);
                }
            }
        };
        tutorial.setText("Press arrow key to move.\nPress Space to place Bomb.\nPress E to spawn enemies.\nPress Esc to open menu in game.\nPress Q to spawn buff.\nPress F to hide status panel.");
        newGame.addActionListener(actionListener);
        configGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);
        newGame.setText("New Game");
        configGame.setText("Config");
        exitGame.setText("Exit");
        newGame.setBounds(0,0,200,75);
        configGame.setBounds(0,0,200,75);
        exitGame.setBounds(0,0,200,75);
        newGame.setLocation(DefaultParameter.panelWidth-newGame.getWidth()-100,100);
        configGame.setLocation(newGame.getX(),newGame.getY()+yDistance+newGame.getHeight());
        exitGame.setLocation(configGame.getX(),configGame.getY()+yDistance+configGame.getHeight());
        tutorial.setLocation(configGame.getX()-tutorial.getWidth()-100,configGame.getY());

    }
    /**
     * Thêm menu chính vào màn hình
     * @param panel
     */
    public static void addMainMenu(JLayeredPane panel) {
        panel.add(newGame,Integer.valueOf(1));
        panel.add(configGame,Integer.valueOf(1));
        panel.add(exitGame,Integer.valueOf(1));
        panel.add(tutorial,Integer.valueOf(1));
    }
    public static void showMainMenu() {
        newGame.setVisible(true);
        configGame.setVisible(true);
        exitGame.setVisible(true);
        tutorial.setVisible(true);
        Graphic.menuBackground.setVisible(true);
    }
    /**
     * Xóa menu chính khỏi màn hình
     */
    public static void hideMainMenu() {
        newGame.setVisible(false);
        configGame.setVisible(false);
        exitGame.setVisible(false);
        tutorial.setVisible(false);
        Graphic.menuBackground.setVisible(false);
    }

    /**
     * Phương thức khởi tạo menu phụ
     */
    public static void subMenuInitialization() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == miniNewGameButton) {
                    SoundPlayer.soundClick();
                    MainProcess.newGame();
                }
                else if (e.getSource() == miniConfigGameButton) {
                    SoundPlayer.soundClick();
                    if (Graphic.settingPanel.isVisible()) {
                        Graphic.settingPanel.setVisible(false);
                    } else {
                        Graphic.settingPanel.setVisible(true);
                    }
                }
                else if (e.getSource() == miniExitGameButton) {
                    SoundPlayer.soundClick();
                    System.exit(0);
                }
                else if (e.getSource() == newGameButton2) {
                    SoundPlayer.soundClick();
                    MainProcess.newGame();
                }
            }
        };
        Graphic.menuPanel.add(miniNewGameButton);
        Graphic.menuPanel.add(miniConfigGameButton);
        Graphic.menuPanel.add(miniExitGameButton);
        Graphic.menuPanel.add(newGameButton2);
        miniNewGameButton.addActionListener(actionListener);
        miniConfigGameButton.addActionListener(actionListener);
        miniExitGameButton.addActionListener(actionListener);
        newGameButton2.addActionListener(actionListener);
        miniNewGameButton.setText("New Game");
        miniConfigGameButton.setText("Config");
        miniExitGameButton.setText("Exit");
        newGameButton2.setText("New Game");
        miniNewGameButton.setLocation(0,0);
        miniConfigGameButton.setLocation(miniNewGameButton.getX()+miniNewGameButton.getWidth(),miniNewGameButton.getY());
        miniExitGameButton.setLocation(miniConfigGameButton.getX()+miniConfigGameButton.getWidth(),miniConfigGameButton.getY());

        Graphic.gameOver.add(newGameButton2,Integer.valueOf(40));
        newGameButton2.setLocation(DefaultParameter.panelWidth/2,DefaultParameter.panelHeight/2);
    }

    /**
     * Phương thức khởi tạo save load menu
     */
    public static void settingMenuInitialization() {
        /**
         * Sound section
         */
        Graphic.settingPanel.setVisible(false);
        Graphic.settingPanel.add(backgroundMusicSetting);
        Graphic.settingPanel.add(soundEffectSetting);
        Graphic.settingPanel.add(systemEffectSetting);
        backgroundMusicSetting.setLocation(50,50);
        soundEffectSetting.setLocation(50,100);
        systemEffectSetting.setLocation(50,150);
        JSlider musicSlider = (JSlider) backgroundMusicSetting.getComponent(0);
        JSlider soundSlider = (JSlider) soundEffectSetting.getComponent(0);
        JSlider systemSlider = (JSlider) systemEffectSetting.getComponent(0);
        musicSlider.setValue((int)DefaultParameter.backgroundMusicVolume);
        soundSlider.setValue((int)DefaultParameter.soundEffectVolume);
        systemSlider.setValue((int)DefaultParameter.systemEffectVolume);
        backgroundMusicSetting.setText("Music Volume    : " + musicSlider.getValue());
        soundEffectSetting.setText("Sound Volume   : " + soundSlider.getValue());
        systemEffectSetting.setText("System Volume : " + systemSlider.getValue());
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() == musicSlider) {
                    backgroundMusicSetting.setText("Music Volume    : " + musicSlider.getValue());
                    DefaultParameter.backgroundMusicVolume = musicSlider.getValue();
                    SoundTrack.updateMusic();
                } else if (e.getSource() == soundSlider) {
                    soundEffectSetting.setText("Sound Volume   : " + soundSlider.getValue());
                    DefaultParameter.soundEffectVolume = soundSlider.getValue();
                } else if (e.getSource() == systemSlider) {
                    systemEffectSetting.setText("System Volume : " + systemSlider.getValue());
                    DefaultParameter.systemEffectVolume = systemSlider.getValue();
                }
            }
        };
        musicSlider.addChangeListener(changeListener);
        soundSlider.addChangeListener(changeListener);
        systemSlider.addChangeListener(changeListener);
        /**
         * Other section
         */
        Graphic.settingPanel.add(canBombCauseDamage);
        Graphic.settingPanel.add(adventureMode);
        canBombCauseDamage.setLocation(50,400);
        adventureMode.setLocation(400,400);
        canBombCauseDamage.setText("Bomb can inflict damage");
        adventureMode.setText("Adventure mode");
        JCheckBox canBombCauseDamageCheckBox = (JCheckBox) canBombCauseDamage.getComponent(0);
        JCheckBox adventureModeCheckBox = (JCheckBox) adventureMode.getComponent(0);
        canBombCauseDamageCheckBox.setSelected(DefaultParameter.canBombCauseDamage);
        adventureModeCheckBox.setSelected(DefaultParameter.adventureMode);

        Graphic.settingPanel.add(canEnemyPlaceBomb);
        canEnemyPlaceBomb.setLocation(50,200);
        canEnemyPlaceBomb.setText("Can enemy place bomb ");
        JCheckBox canEnemyPlaceBombCheckBox = (JCheckBox) canEnemyPlaceBomb.getComponent(0);
        canEnemyPlaceBombCheckBox.setSelected(DefaultParameter.canEnemyPlaceBomb);

        Graphic.settingPanel.add(intersectDamage);
        intersectDamage.setLocation(400,200);
        intersectDamage.setText("Intersect damage ");
        JCheckBox intersectDamageCheckBox = (JCheckBox) intersectDamage.getComponent(0);
        intersectDamageCheckBox.setSelected(DefaultParameter.intersectDamage);

        Graphic.settingPanel.add(enemyDamageToEnemy);
        Graphic.settingPanel.add(enemyDamageToPlayer);
        Graphic.settingPanel.add(enemyDamageToTerrain);
        Graphic.settingPanel.add(playerDamageToEnemy);
        Graphic.settingPanel.add(playerDamageToPlayer);
        Graphic.settingPanel.add(playerDamageToTerrain);
        enemyDamageToEnemy.setLocation(50,250);
        enemyDamageToPlayer.setLocation(50,300);
        enemyDamageToTerrain.setLocation(50,350);
        playerDamageToEnemy.setLocation(400,250);
        playerDamageToPlayer.setLocation(400,300);
        playerDamageToTerrain.setLocation(400,350);
        enemyDamageToEnemy.setText("Enemy damage to enemy");
        enemyDamageToPlayer.setText("Enemy damage to player");
        enemyDamageToTerrain.setText("Enemy damage to terrain");
        playerDamageToEnemy.setText("Player damage to enemy");
        playerDamageToPlayer.setText("Player damage to player");
        playerDamageToTerrain.setText("Player damage to terrain");
        JCheckBox enemyDamageToEnemyCheckBox = (JCheckBox) enemyDamageToEnemy.getComponent(0);
        JCheckBox enemyDamageToPlayerCheckBox = (JCheckBox) enemyDamageToPlayer.getComponent(0);
        JCheckBox enemyDamageToTerrainCheckBox = (JCheckBox) enemyDamageToTerrain.getComponent(0);
        JCheckBox playerDamageToEnemyCheckBox = (JCheckBox) playerDamageToEnemy.getComponent(0);
        JCheckBox playerDamageToPlayerCheckBox = (JCheckBox) playerDamageToPlayer.getComponent(0);
        JCheckBox playerDamageToTerrainCheckBox = (JCheckBox) playerDamageToTerrain.getComponent(0);
        enemyDamageToEnemyCheckBox.setSelected(DefaultParameter.enemyDamageToEnemy);
        enemyDamageToPlayerCheckBox.setSelected(DefaultParameter.enemyDamageToPlayer);
        enemyDamageToTerrainCheckBox.setSelected(DefaultParameter.enemyDamageToTerrain);
        playerDamageToEnemyCheckBox.setSelected(DefaultParameter.playerDamageToEnemy);
        playerDamageToPlayerCheckBox.setSelected(DefaultParameter.playerDamageToPlayer);
        playerDamageToTerrainCheckBox.setSelected(DefaultParameter.playerDamageToTerrain);


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == canEnemyPlaceBombCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.canEnemyPlaceBomb = canEnemyPlaceBombCheckBox.isSelected();
                    MainProcess.changeEnemy();
                } else if (e.getSource() == intersectDamageCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.intersectDamage = intersectDamageCheckBox.isSelected();
                } else if (e.getSource() == enemyDamageToEnemyCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.enemyDamageToEnemy = enemyDamageToEnemyCheckBox.isSelected();
                } else if (e.getSource() == enemyDamageToPlayerCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.enemyDamageToPlayer = enemyDamageToPlayerCheckBox.isSelected();
                } else if (e.getSource() == enemyDamageToTerrainCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.enemyDamageToTerrain = enemyDamageToTerrainCheckBox.isSelected();
                } else if (e.getSource() == playerDamageToEnemyCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.playerDamageToEnemy = playerDamageToEnemyCheckBox.isSelected();
                } else if (e.getSource() == playerDamageToPlayerCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.playerDamageToPlayer = playerDamageToPlayerCheckBox.isSelected();
                } else if (e.getSource() == playerDamageToTerrainCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.playerDamageToPlayer = playerDamageToPlayerCheckBox.isSelected();
                } else if (e.getSource() == canBombCauseDamageCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.canBombCauseDamage = canBombCauseDamageCheckBox.isSelected();
                } else if (e.getSource() == adventureModeCheckBox) {
                    SoundPlayer.soundClick();
                    DefaultParameter.adventureMode = adventureModeCheckBox.isSelected();
                }
            }
        };
        canEnemyPlaceBombCheckBox.addActionListener(actionListener);
        intersectDamageCheckBox.addActionListener(actionListener);
        enemyDamageToEnemyCheckBox.addActionListener(actionListener);
        enemyDamageToPlayerCheckBox.addActionListener(actionListener);
        enemyDamageToTerrainCheckBox.addActionListener(actionListener);
        playerDamageToEnemyCheckBox.addActionListener(actionListener);
        playerDamageToPlayerCheckBox.addActionListener(actionListener);
        playerDamageToTerrainCheckBox.addActionListener(actionListener);
        canBombCauseDamageCheckBox.addActionListener(actionListener);
        adventureModeCheckBox.addActionListener(actionListener);
        /**
         * Graphic
         */
        Graphic.settingPanel.add(difficulty);
        Graphic.settingPanel.add(adModeX);
        Graphic.settingPanel.add(adModeY);
        adModeX.setLocation(50,450);
        adModeY.setLocation(50,500);
        difficulty.setLocation(50,550);
        JSlider adModeXSlider = (JSlider) adModeX.getComponent(0);
        JSlider adModeYSlider = (JSlider) adModeY.getComponent(0);
        JSlider difficultySlider = (JSlider) difficulty.getComponent(0);
        adModeXSlider.setMinimum(1);
        adModeXSlider.setMaximum(10);
        adModeYSlider.setMinimum(1);
        adModeYSlider.setMaximum(10);
        difficultySlider.setMinimum(1);
        difficultySlider.setMaximum(10);
        adModeXSlider.setValue(DefaultParameter.adventureModeX);
        adModeYSlider.setValue(DefaultParameter.adventureModeY);
        difficultySlider.setValue(DefaultParameter.difficulty);
        adModeX.setText("Adventure map width : " + adModeXSlider.getValue());
        adModeY.setText("Adventure map height : " + adModeYSlider.getValue());
        difficulty.setText("Difficulty : " + difficultySlider.getValue());
        ChangeListener changeListener1 = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() == adModeXSlider) {
                    adModeX.setText("Adventure map width : " + adModeXSlider.getValue());
                    DefaultParameter.adventureModeX = adModeXSlider.getValue();
                } else if (e.getSource() == adModeYSlider) {
                    adModeY.setText("Adventure map height : " + adModeYSlider.getValue());
                    DefaultParameter.adventureModeY = adModeYSlider.getValue();
                } else if (e.getSource() == difficultySlider) {
                    difficulty.setText("Difficulty : " + difficultySlider.getValue());
                    DefaultParameter.difficulty = difficultySlider.getValue();
                    MainProcess.updateDifficulty();

                }
            }
        };
        adModeXSlider.addChangeListener(changeListener1);
        adModeYSlider.addChangeListener(changeListener1);
        difficultySlider.addChangeListener(changeListener1);
    }
}
