package BackEnd;
import Entities.*;

import javax.swing.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public abstract class Physics {
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
    public static boolean checkIntersect(JLabel label1 , JLabel label2) {
        Area area1 = new Area(label1.getBounds());
        Area area2 = new Area(label2.getBounds());
        return area1.intersects(area2.getBounds2D());
    }
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
    public static void processProjectileDamage(ArrayList<Terrain> terrains,ArrayList<Enemy> enemies,ArrayList<Projectile> projectiles,Player player) {
        for(Projectile projectile : projectiles) {
            if (projectile.isDamageToTerrain()) {
                for (Terrain terrain : terrains) {
                    if (checkIntersect(projectile.box,terrain.box) && !terrain.isPassable()) {
                        terrain.reduceHeath(projectile.getDamage());
                    }
                }
            }
            if (projectile.isDamageToEnemy()) {
                for (Enemy enemy : enemies) {
                    if (checkIntersect(projectile.box,enemy.box)) {
                        enemy.reduceHeath(projectile.getDamage());
                    }
                }
            }
            if (projectile.isDamageToPlayer()) {
                if (checkIntersect(projectile.box,player.box)) {
                    player.reduceHeath(projectile.getDamage());
                }
            }
        }
    }
}
