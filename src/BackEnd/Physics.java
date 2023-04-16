package BackEnd;
import Entities.*;
import Graphic.Graphic;

import javax.swing.*;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * Lớp trừu tượng dùng để tính toán vật lý cho game
 */
public abstract class Physics {
    /**
     * Tính khoảng cách giữa 2 đối tượng
     * @param entity1 : đối tượng
     * @param entity2 : đối tượng
     * @return khoảng cách dưới dạnh int
     */
    public static int calculateDistance(Entity entity1, Entity entity2) {
        int x1 = entity1.box.getX();
        int y1 = entity1.box.getY();
        int x2 = entity2.box.getX();
        int y2 = entity2.box.getY();
        int width1 = entity1.box.getWidth();
        int height1 = entity1.box.getHeight();
        int width2 = entity2.box.getWidth();
        int height2 = entity2.box.getHeight();
        x1 += width1/2;
        x2 += width2/2;
        y1 += height1/2;
        y2 += height2/2;
        return (int)Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }

    /**
     * Kiểm tra xem hitbox của 2 đối tượng hiện tại có giao nhau không
     * @param label1 : hitbox của đối tượng
     * @param label2 : hitbox của đối tượng
     * @return true nếu giao nhau, false nếu không giao nhau
     */
    public static boolean checkIntersect(JLabel label1 , JLabel label2) {
        Area area1 = new Area(label1.getBounds());
        Area area2 = new Area(label2.getBounds());
        return area1.intersects(area2.getBounds2D());
    }

    /**
     * Kiêm tra xem vị trí hiện tại của đối tượng có giao với địa hình không đi qua được hay không
     * @param terrains : địa hình
     * @param entity : đối tượng
     * @param moveX : chiều ngang sẽ đi
     * @param moveY : chiều dọc sẽ đi
     * @return true nếu giao nhau, false nếu không giao nhau
     */
    public static boolean checkIntersectTerrain(ArrayList<Terrain> terrains, Entity entity,int moveX,int moveY) {
        JLabel temp = new JLabel();
        temp.setBounds(entity.box.getBounds());
        temp.setLocation(entity.box.getX()+moveX,entity.box.getY()+moveY);
        for (int i=0;i<terrains.size();i++) {
            if ((!terrains.get(i).isPassable()) && checkIntersect(terrains.get(i).box, temp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Xử lý khi nhặt buff
     * @param buffs
     * @param player
     */
    public static void processBuffContact(ArrayList<Buff> buffs, Player player) {
        for (Buff buff : buffs) {
            if (!buff.isReceived()) {
                if (checkIntersect(player.box, buff.box)) {
                    if (buff.getName().equals("More Speed") && MainProcess.player.getSpeed() == 2*DefaultParameter.playerSpeed ) {
                        break;
                    }
                    buff.setReceived(true);
                    buff.activeBuff(player);
                    Graphic.panel.remove(buff.box);
                }
            }
        }
    }
    /**
     * Xử lý sát thương gây ra bời bom/đạn (Projectile)
     * @param terrains : địa hình
     * @param enemies : kẻ địch
     * @param projectiles : bom/đạn
     * @param player : nhân vật
     */
    public static void processProjectileDamage(ArrayList<Terrain> terrains,ArrayList<Enemy> enemies,ArrayList<Projectile> projectiles,Player player) {
        for(Projectile projectile : projectiles) {
            if (!(projectile.getName().equals("Bomb") && !DefaultParameter.canBombCauseDamage)) {
                if (projectile.isDamageToTerrain()) {
                    for (Terrain terrain : terrains) {
                        if (terrain.getName().equals("Wall")) {
                            if (checkIntersect(projectile.box, terrain.box)) {
                                terrain.reduceHeath(projectile.getDamage());
                            }
                        }
                    }
                }
                if (projectile.isDamageToEnemy()) {
                    for (Enemy enemy : enemies) {
                        if (checkIntersect(projectile.box, enemy.box)) {
                            enemy.reduceHeath(projectile.getDamage());
                        }
                    }
                }
                if (projectile.isDamageToPlayer()) {
                    if (checkIntersect(projectile.box, player.box)) {
                        if (!player.invincible) {
                            player.reduceHeath(projectile.getDamage());
                        }
                    }
                }
            }
        }
    }

    /**
     * Xử lý sát thương gây ra khi va chạm
     * @param enemies
     * @param player
     */
    public static void processIntersectDamage(ArrayList<Enemy> enemies,Player player) {
        for (Enemy enemy : enemies) {
            if (checkIntersect(enemy.box,player.box)) {
                if (!player.invincible) {
                    player.reduceHeath(enemy.getDamage());
                }
            }
        }
    }
}
