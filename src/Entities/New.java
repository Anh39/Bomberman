package Entities;

import BackEnd.DefaultParameter;
import Graphic.StatusBar;

import javax.swing.*;
import java.awt.*;

/**
 * Lớp trừu tượng dùng để khởi tạo các đối tượng
 * Chủ yếu dùng để làm giản độ dài các đoạn code khác
 */

public abstract class New {
    // Hình ảnh để khởi tạo đối tượng
    private static final Font font = new Font("Arial", Font.PLAIN,30);
    private static final Font miniFont = new Font("Arial", Font.PLAIN,20);
    //public static ImageIcon playerImg1 = new ImageIcon(New.class.getResource("/Images/Player1.png"));
    public static ImageIcon grassImg = new ImageIcon(New.class.getResource("/Images/Grass Terrain.png"));
    public static ImageIcon treeImg = new ImageIcon(New.class.getResource("/Images/Tree Terrain.png"));
    public static ImageIcon bombImg = new ImageIcon(New.class.getResource("/Images/Bomb.png"));
    public static ImageIcon bombFragmentImg = new ImageIcon(New.class.getResource("/Images/bombFragment.png"));
    public static ImageIcon slimeImg = new ImageIcon(New.class.getResource("/Images/Slime1.png"));
    public static ImageIcon menuBackGround = new ImageIcon(New.class.getResource("/Images/Menu Background.jpg"));
    public static ImageIcon testImg = new ImageIcon(New.class.getResource("/Images/Test.png"));
    //Player
    public static Player player() {
        Player player = new Player();
        player.setDefault();
        //player.box.setIcon(playerImg1);
        player.setRange(DefaultParameter.playerRange);
        return player;
    }
    //Enemy
    public static Enemy slime() {
        Enemy enemy = new Enemy();
        enemy.setDefault();
        enemy.setCooldown(0);
        enemy.box.setIcon(slimeImg);
        enemy.setRange(DefaultParameter.entityRange);
        enemy.setSpeed(50);
        return enemy;
    }
    //Terrain
    public static Terrain grass() {
        Terrain grass = new Terrain();
        grass.setDefault();
        grass.box.setIcon(grassImg);
        grass.setPassable(DefaultParameter.grassPassable);
        grass.setOverlapped(false);
        return grass;
    }
    public static Terrain tree() {
        Terrain tree = new Terrain();
        tree.setDefault();
        tree.box.setIcon(treeImg);
        tree.setPassable(DefaultParameter.treePassable);
        tree.setOverlapped(false);
        return tree;
    }
    //Projectile
    public static Projectile bomb(Entity entity) {
        Projectile bomb = new Projectile();
        bomb.setDefault();
        bomb.setDamage(entity.getDamage());
        bomb.setDuration(DefaultParameter.bombDecay);
        bomb.box.setIcon(bombImg);
        bomb.setName("Bomb");
        bomb.setRange(entity.getRange());
        if (entity instanceof Player) {
            bomb.setDamageToTerrain(DefaultParameter.playerDamageToTerrain);
            bomb.setDamageToEnemy(DefaultParameter.playerDamageToEnemy);
            bomb.setDamageToPlayer(DefaultParameter.playerDamageToPlayer);
        }
        else if (entity instanceof Enemy) {
            bomb.setDamageToTerrain(DefaultParameter.enemyDamageToTerrain);
            bomb.setDamageToEnemy(DefaultParameter.enemyDamageToEnemy);
            bomb.setDamageToPlayer(DefaultParameter.enemyDamageToPlayer);
        }
        return bomb;
    }
    public static Projectile bombFragment(Projectile source) {
        Projectile bombFragment = new Projectile();
        bombFragment.setDefault();
        bombFragment.setDuration(DefaultParameter.bombFragmentDecay);
        bombFragment.setDamage(source.getDamage());
        bombFragment.box.setIcon(bombFragmentImg);
        bombFragment.setName("Bomb Fragment");
        bombFragment.setDamageToTerrain(source.isDamageToTerrain());
        bombFragment.setDamageToPlayer(source.isDamageToPlayer());
        bombFragment.setDamageToEnemy(source.isDamageToEnemy());
        bombFragment.setRange(source.getRange());
        return bombFragment;
    }
    //MyMenu
    public static JLabel menuBackground() {
        JLabel menu = new JLabel();
        menu.setIcon(menuBackGround);
        menu.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        return menu;
    }
    public static JButton miniNewGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("New Game");
        return result;
    }
    public static JButton miniSaveGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Save Game");
        return result;
    }
    public static JButton miniLoadGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Load Game");
        return result;
    }
    public static JButton miniConfigGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Config");
        return result;
    }
    public static JButton miniExitGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Exit");
        return result;
    }
    public static JButton newGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(font);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("New Game");
        return result;
    }
    public static JButton loadGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(font);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Load Game");
        return result;
    }
    public static JButton configGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(font);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Config");
        return result;
    }
    public static JButton exitGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(font);
        result.setBackground(DefaultParameter.menuColor);
        result.setText("Exit");
        return result;
    }
    public static JTextArea tutorial() {
        JTextArea tutorial = new JTextArea();
        tutorial.setOpaque(false);
        tutorial.setFocusable(false);
        tutorial.setBounds(0,0,300,200);
        tutorial.setFont(StatusBar.font);
        return tutorial;
    }
    public static JTextField statusField() {
        JTextField status = new JTextField();
        status.setOpaque(false);
        status.setFocusable(false);
        status.setBounds(0,0,250,50);
        status.setFont(miniFont);
        return status;
    }
    // Buff
    // Làm theo mẫu dưới đây. <buffName> là tên của buff, bỏ dấu <> đi
    /*
    public static Buff <buffName>() {
        Buff <buffName> = new Buff();
        <buffName>.setZero();
        //Chỉnh các thông số
        //Vd:
        <buffName>.setHeath(10); // Là công thêm 10 máu
        //
        <buffName>.setName("Buff name");
        return <buffName>;
    }
     */
    public static Buff Health() {
        Buff moreHealth = new Buff();
        moreHealth.setHeath(moreHealth.getHeath() + 50);
        moreHealth.setName("More Health");
        return moreHealth;
    }

    public static Buff StrongerBomb() {
        Buff strongerBomb =  new Buff();
        strongerBomb.setZero();
        strongerBomb.setRange(strongerBomb.getRange() + 1);
        return strongerBomb;
    }
    public static Buff MoreSpeed() {
        Buff faster =  new Buff();
        faster.setZero();
        faster.setSpeed(faster.getSpeed() + 10);
        return faster;
    }
}
