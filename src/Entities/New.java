package Entities;

import BackEnd.DefaultParameter;
import Graphic.MyPanel;
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
    private static  final Font fontS = new Font("Arial",Font.PLAIN,15);
    private static final Font miniFont = new Font("Arial", Font.PLAIN,20);
    public static ImageIcon grassImg = new ImageIcon(New.class.getResource("/Images/Terrain/Grass Terrain.png"));
    public static ImageIcon treeImg = new ImageIcon(New.class.getResource("/Images/Terrain/Tree Terrain.png"));
    public static ImageIcon menuBackGround = new ImageIcon(New.class.getResource("/Images/Other/Menu Background.jpg"));
    public static ImageIcon gameOverImg = new ImageIcon(New.class.getResource("/Images/Other/GameOver.png"));
    public static ImageIcon heathRegenBuff = new ImageIcon(New.class.getResource("/Images/Buff/Regen Heath Buff.png"));
    //Player
    public static Player player() {
        Player player = new Player();
        player.setDefault();
        player.setRange(DefaultParameter.playerRange);
        return player;
    }
    //Enemy
    public static Enemy slime() {
        Enemy enemy = new Enemy();
        enemy.setDefault();
        enemy.setCooldown(0);
        enemy.setRange(DefaultParameter.entityRange);
        enemy.setSpeed(DefaultParameter.enemySpeed);
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
        bomb.setMaxDuration(DefaultParameter.bombDecay);
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
        bombFragment.setMaxDuration(DefaultParameter.bombFragmentDecay);
        bombFragment.setDamage(source.getDamage());
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
        menu.setOpaque(true);
        menu.setLayout(null);
        menu.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        return menu;
    }
    public static JButton miniMenuButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(miniFont);
        result.setBounds(0,0,150,50);
        result.setBackground(DefaultParameter.menuColor);
        return result;
    }
    public static JButton menuButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setFont(font);
        result.setBackground(DefaultParameter.menuColor);
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
    public static JTextArea statusField() {
        JTextArea status = new JTextArea();
        status.setOpaque(false);
        status.setFocusable(false);
        status.setBounds(0,0,250,50);
        status.setFont(miniFont);
        return status;
    }
    public static JLabel slider() {
        JLabel label = new JLabel();
        label.setBounds(0,0,750,50);
        JSlider slider = new JSlider(0,100,50);
        slider.setBounds(200,0,500,50);
        slider.setFocusable(false);
        label.add(slider);
        label.setFont(fontS);
        slider.setBackground(DefaultParameter.menuColor);
        return label;
    }
    public static JLabel checkBox() {
        JLabel label = new JLabel();
        label.setBounds(0,0,300,50);
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(250,0,50,50);
        checkBox.setFocusable(false);
        label.add(checkBox);
        label.setFont(fontS);
        checkBox.setBackground(DefaultParameter.menuColor);
        return label;
    }
    public static MyPanel gameOver() {
        MyPanel panel = new MyPanel();
        panel.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        panel.setPreferredSize(new Dimension(DefaultParameter.panelWidth,DefaultParameter.panelHeight));
        JLabel label = new JLabel();
        label.setBounds(0,0,DefaultParameter.panelWidth,DefaultParameter.panelHeight);
        label.setIcon(gameOverImg);
        panel.add(label);
        panel.setLayout(null);
        return panel;
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
        moreHealth.box.setIcon(heathRegenBuff);
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
