package Entities;

import javax.swing.*;

public class New {
    public static ImageIcon newGameImg = new ImageIcon("src/Images/newGameImage.png");
    public static ImageIcon loadGameImg = new ImageIcon("src/Images/loadGameImage.png");
    public static ImageIcon configGameImg = new ImageIcon("src/Images/configGameImage.png");
    public static ImageIcon exitGameImg = new ImageIcon("src/Images/exitGameImage.png");
    public static Player player() {
        Player player = new Player();
        return player;
    }
    public static Enemy enemy() {
        Enemy enemy = new Enemy();
        return enemy;
    }
    public static Terrain grass() {
        Terrain grass = new Terrain();
        return grass;
    }
    public static Terrain tree() {
        Terrain tree = new Terrain();
        return tree;
    }
    public static JButton newGameButton() {
        JButton result = new JButton();
        result.setIcon(newGameImg);
        return result;
    }
    public static JButton loadGameButton() {
        JButton result = new JButton();
        result.setIcon(loadGameImg);
        return result;
    }
    public static JButton configGameButton() {
        JButton result = new JButton();
        result.setIcon(configGameImg);
        return result;
    }
    public static JButton exitGameButton() {
        JButton result = new JButton();
        result.setIcon(exitGameImg);
        return result;
    }
}
