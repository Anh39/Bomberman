package Entities;

import BackEnd.DefaultParameter;
import Graphic.StatusBar;

import javax.swing.*;

/**
 * Lớp trừu tượng dùng để khởi tạo các đối tượng
 * Chủ yếu dùng để làm giản độ dài các đoạn code khác
 */

public abstract class New {
    // Hình ảnh để khởi tạo đối tượng
    public static ImageIcon newGameImg = new ImageIcon(New.class.getResource("/Images/newGameImage.png"));
    public static ImageIcon loadGameImg = new ImageIcon(New.class.getResource("/Images/loadGameImage.png"));
    public static ImageIcon configGameImg = new ImageIcon(New.class.getResource("/Images/configGameImage.png"));
    public static ImageIcon exitGameImg = new ImageIcon(New.class.getResource("/Images/exitGameImage.png"));
    public static ImageIcon miniNewGameImg = new ImageIcon(New.class.getResource("/Images/miniNewGameImage.png"));
    public static ImageIcon playerImg1 = new ImageIcon(New.class.getResource("/Images/Player1.png"));
    public static ImageIcon grassImg = new ImageIcon(New.class.getResource("/Images/Grass Terrain.png"));
    public static ImageIcon treeImg = new ImageIcon(New.class.getResource("/Images/Tree Terrain.png"));
    public static ImageIcon bombImg = new ImageIcon(New.class.getResource("/Images/Bomb.png"));
    public static ImageIcon bombFragmentImg = new ImageIcon(New.class.getResource("/Images/bombFragment.png"));
    public static ImageIcon slimeImg = new ImageIcon(New.class.getResource("/Images/Slime1.png"));
    //Player
    public static Player player() {
        Player player = new Player();
        player.setDefault();
        player.box.setIcon(playerImg1);
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
        return enemy;
    }
    //Terrain
    public static Terrain grass() {
        Terrain grass = new Terrain();
        grass.setDefault();
        grass.box.setIcon(grassImg);
        grass.setPassable(true);
        return grass;
    }
    public static void grass(Terrain tree) {
        tree.setDefault();
        tree.box.setIcon(grassImg);
        tree.setPassable(DefaultParameter.grassPassable);
    }
    public static Terrain tree() {
        Terrain tree = new Terrain();
        tree.setDefault();
        tree.box.setIcon(treeImg);
        tree.setPassable(DefaultParameter.treePassable);
        return tree;
    }
    //Projectile
    public static Projectile bomb(Entity entity) {
        Projectile bomb = new Projectile();
        bomb.setDefault();
        bomb.setDamage(entity.getDamage());
        bomb.setDecay(DefaultParameter.bombDecay);
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
        bombFragment.setDecay(DefaultParameter.bombFragmentDecay);
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
    public static JButton miniNewGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setIcon(miniNewGameImg);
        return result;
    }
    public static JButton newGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setIcon(newGameImg);
        return result;
    }
    public static JButton loadGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setIcon(loadGameImg);
        return result;
    }
    public static JButton configGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setIcon(configGameImg);
        return result;
    }
    public static JButton exitGameButton() {
        JButton result = new JButton();
        result.setFocusable(false);
        result.setIcon(exitGameImg);
        return result;
    }
    public static JTextArea tutorial() {
        JTextArea tutorial = new JTextArea();
        tutorial.setFocusable(false);
        tutorial.setBounds(500,300,500,200);
        tutorial.setFont(StatusBar.font);
        return tutorial;
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
}
