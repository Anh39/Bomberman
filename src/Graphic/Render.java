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
    static ArrayList<ImageIcon> projectileImg = new ArrayList<ImageIcon>();
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
    public static void render(ArrayList<Terrain> terrains, ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles,ArrayList<Buff> buffs, Player player) {
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
        for (Buff buff : buffs) {
            if (buff.box != null && !Graphic.panel.isAncestorOf(buff.box)) {
                if (!buff.isReceived()) {
                    Graphic.panel.add(buff.box, Integer.valueOf(3));
                }
            }
        }
        for (Enemy enemy : enemies) {
            enemyAnimation(enemy);
            if (enemy.box != null && !Graphic.panel.isAncestorOf(enemy.box)) {
                Graphic.panel.add(enemy.box,Integer.valueOf(5));
                Graphic.panel.add(enemy.bar, Integer.valueOf(10));
            }
        }
        for (Projectile projectile : projectiles) {
            projectileAnimation(projectile);
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
        int index = 0;
        if (player.getAngle() >= 0 - view && player.getAngle() <= PI/2 - view) {
            index += MyClock.renderState%6;
        }
        else if (player.getAngle() >= PI/2 - view && player.getAngle() <= PI - view) {
            index += MyClock.renderState%6 + 6;
        }
        else if (player.getAngle() >= PI - view && player.getAngle() <= PI*3/2 - view) {
            index += MyClock.renderState%6 + 6*2;
        }
        else {
            index += MyClock.renderState%6 + 6*3;
        }
        player.box.setIcon(playerImg.get(index));
    }
    private static void enemyAnimation(Enemy enemy) {
        enemy.addRenderState();
        int index = enemy.getRenderState() % 4;
        enemy.box.setIcon(enemyImg.get(index));
    }
    private static void projectileAnimation(Projectile projectile) {
        projectile.addRenderState();
        int index = (int)(3*projectile.getDuration()/projectile.getMaxDuration());
        if (index > 2) {
            index = 2;
        } else if (index < 0) {
            index = 0;
        }
        if (projectile.getName().equals("Bomb")) {
            index += 0;
        }
        /*else if (projectile.getName().equals("Bomb Fragment")) {
            index += 3;
        }*/
        projectile.box.setIcon(projectileImg.get(index));
    }
    public static void initialize() {
        playerImageInitialize();
        enemyImageInitialize();
        projectileImageInitialize();
    }
    private static void playerImageInitialize() {
        String temp = "/Images/Player/player";
        for (int i=1;i<=4;i++) {
            for (int j=1;j<=6;j++) {
                playerImg.add(new ImageIcon(Render.class.getResource(temp + i + j + ".png")));
            }
        }
    }
    private static void enemyImageInitialize() {
        String temp = "/Images/Enemy/Slime/slime";
        for (int i=1;i<=4;i++) {
            enemyImg.add(new ImageIcon(Render.class.getResource(temp + i + ".png")));
        }
    }
    private static void projectileImageInitialize() {
        String temp = "/Images/Bomb/";
        for (int i=1;i<=3;i++) {
            projectileImg.add(new ImageIcon(Render.class.getResource(temp + "Bomb" + i + ".png")));
        }
        /*for (int i=1;i<=2;i++) {
            projectileImg.add(new ImageIcon(Render.class.getResource(temp + "BombFragment" + i + ".png")));
        }*/
    }
}
