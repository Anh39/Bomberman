package Graphic;

import BackEnd.MainProcess;
import BackEnd.MyClock;
import Entities.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Lớp trừu tượng xử lý Render
 */
public abstract class Render {
    // Hình ảnh cho việc render. Hiện chưa code
    static ArrayList<ImageIcon> playerImg = new ArrayList<ImageIcon>();
    static ArrayList<ImageIcon> terrainImg = new ArrayList<ImageIcon>();
    static ArrayList<ImageIcon> enemyImg = new ArrayList<ImageIcon>();
    private static final double PI = Math.PI;
    public static boolean rendering = false;

    /**
     * Phương thức xử lý render chính
     * @param terrains : địa hình
     * @param enemies : kẻ địch
     * @param projectiles : bom/đạn
     * @param player : người chơi
     */
    public static void render(ArrayList<Terrain> terrains, ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles, Player player) {
        // Dùng để kiểm soát render hay không
        if (!rendering) {
            return;
        }
        // Kiểm tra xem bảng được khởi tạo chưa
        if (Graphic.panel == null) {
            System.out.println("Panel is null");
            return;
        }
        for (Terrain terrain : terrains) {
            if (terrain.box != null && !Graphic.panel.isAncestorOf(terrain.box)) {
                if (terrain.isPassable()) {
                    Graphic.panel.add(terrain.box, Integer.valueOf(1));
                }
                else {
                    Graphic.panel.add(terrain.box,Integer.valueOf(2));
                }
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.box != null && !Graphic.panel.isAncestorOf(enemy.box)) {
                Graphic.panel.add(enemy.box,Integer.valueOf(5));
                Graphic.panel.add(enemy.bar, Integer.valueOf(10));
            }
        }
        for (Projectile projectile : projectiles) {
            if (projectile.box != null && !Graphic.panel.isAncestorOf(projectile.box)) {
                Graphic.panel.add(projectile.box,Integer.valueOf(5));
            }
        }
        if (!Graphic.panel.isAncestorOf(player.box)) {
            Graphic.panel.add(player.box, Integer.valueOf(7));
            Graphic.panel.add(player.bar, Integer.valueOf(10));
        }
        playerAnimation(player);
        Graphic.panel.repaint();
    }
    private static void playerAnimation(Player player) {
        //System.out.println(player.getAngle());
        //System.out.println(MyClock.renderState);
        double view = PI/4;
        if (player.getAngle() >= 0 - view && player.getAngle() <= PI/2 - view) {
            if (MyClock.renderState == 1) {
                player.box.setIcon(playerImg.get(1-1));
            }
        }
        else if (player.getAngle() >= PI/2 - view && player.getAngle() <= PI - view) {
            if (MyClock.renderState == 1) {
                player.box.setIcon(playerImg.get(2-1));
            }
        }
        else if (player.getAngle() >= PI - view && player.getAngle() <= PI*3/2 - view) {
            if (MyClock.renderState == 1) {
                player.box.setIcon(playerImg.get(3-1));
            }
        }
        else {
            if (MyClock.renderState == 1) {
                player.box.setIcon(playerImg.get(4-1));
            }
        }
        Graphic.panel.repaint();
    }
    public static void playerImageInitialize() {
        ImageIcon playerImg1 = new ImageIcon(Render.class.getResource("/Images/Player1.png"));
        ImageIcon playerImg2 = new ImageIcon(Render.class.getResource("/Images/Player2.png"));
        ImageIcon playerImg3 = new ImageIcon(Render.class.getResource("/Images/Player3.png"));
        ImageIcon playerImg4 = new ImageIcon(Render.class.getResource("/Images/Player4.png"));
        playerImg.add(playerImg1);
        playerImg.add(playerImg2);
        playerImg.add(playerImg3);
        playerImg.add(playerImg4);
    }
}
