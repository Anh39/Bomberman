package Graphic;

import BackEnd.MainProcess;
import Entities.Enemy;
import Entities.Player;
import Entities.Projectile;
import Entities.Terrain;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Render {
    ArrayList<ImageIcon> playerImg = new ArrayList<ImageIcon>();
    ArrayList<ImageIcon> terrainImg = new ArrayList<ImageIcon>();
    ArrayList<ImageIcon> enemyImg = new ArrayList<ImageIcon>();
    public static boolean rendering = false;
    public static void render(ArrayList<Terrain> terrains, ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles, Player player) {
        if (!rendering) {
            return;
        }
        StatusBar.textField.setText("HP : " + MainProcess.player.getHeath());
        if (Graphic.panel == null) {
            System.out.println("Panel is null");
        }
        for (Terrain terrain : terrains) {
            if (terrain.box != null && !Graphic.panel.isAncestorOf(terrain.box)) {
                Graphic.panel.add(terrain.box,Integer.valueOf(1));
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.box != null && !Graphic.panel.isAncestorOf(enemy.box)) {
                Graphic.panel.add(enemy.box,Integer.valueOf(5));
            }
        }
        for (Projectile projectile : projectiles) {
            if (projectile.box != null && !Graphic.panel.isAncestorOf(projectile.box)) {
                Graphic.panel.add(projectile.box,Integer.valueOf(5));
            }
        }
        if (!Graphic.panel.isAncestorOf(player.box)) {
            Graphic.panel.add(player.box, Integer.valueOf(7));
        }
        Graphic.panel.repaint();
    }
}
