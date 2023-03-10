package BackEnd;

import Entities.*;
import Graphic.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class MainProcess {
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static Player player = New.player();
    public static int renderTime = 0;
    public static int bombTime = 0;
    public static int damageTime = 0;
    public static int enemyMoveTime = 0;
    public static boolean playerBombPlaced = false;
    //Time
    public static void runTime() {
        renderTime++;
        bombTime++;
        damageTime++;
        enemyMoveTime++;
        if (renderTime > 10) {
            renderTime = 0;
            processDeath();
        }
        if (bombTime > 10) {
            bombTime = 0;
            playerBombPlaced = false;
            projectileDecay();
        }
        if (damageTime > 5) {
            Physics.processProjectileDamage(terrains,enemies,projectiles,player);
            damageTime = 0;
        }
        if (enemyMoveTime > 5) {
            enemyMoveTime = 0;
            for (Enemy enemy : enemies) {
                enemy.move();
            }
        }
        //Internal Cool down
        for (Enemy enemy : enemies) {
            if (enemy.getCooldown() > 300) {
                if (Physics.calculateDistance(enemy, player) < 300) {
                    placeBomb(enemy);
                    enemy.setCooldown(0);
                }
            }
            else {
                enemy.continueCooldown();
            }
        }

    }
    // Projectile
    public static void projectileDecay() {
        for (int i=0;i<projectiles.size();i++) {
            projectiles.get(i).decay--;
            if (projectiles.get(i).decay <= 0) {
                if (projectiles.get(i).getName().equals("Bomb")) {
                    denoteBomb(i);
                }
                Graphic.panel.remove(projectiles.get(i).box);
                projectiles.remove(i);
                i--;
            }
        }
    }
    public static void denoteBomb(int index) {
        Projectile temp = projectiles.get(index);
        int x = temp.box.getX();
        int y = temp.box.getY();
        int width = temp.box.getWidth();
        int height = temp.box.getHeight();
        for (int direction=0;direction<4;direction++) {
            for (int i = 1; i <= temp.getRange(); i++) {
                if (direction==0) {
                    Projectile bomFragment = New.bombFragment(temp);
                    bomFragment.box.setLocation(x-i*width,y);
                    projectiles.add(bomFragment);
                }
                else if (direction==1) {
                    Projectile bomFragment = New.bombFragment(temp);
                    bomFragment.box.setLocation(x+i*width,y);
                    projectiles.add(bomFragment);
                }
                else if (direction==2) {
                    Projectile bomFragment = New.bombFragment(temp);
                    bomFragment.box.setLocation(x,y-i*height);
                    projectiles.add(bomFragment);
                }
                else {
                    Projectile bomFragment = New.bombFragment(temp);
                    bomFragment.box.setLocation(x,y+i*height);
                    projectiles.add(bomFragment);
                }
            }
        }
    }
    public static void playerInput(ArrayList<KeyState> keyStates) {
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getState()) {
                if (keyStates.get(i).getKeyCode() == 32) {
                    placeBomb(player);
                }
                if (keyStates.get(i).getKeyCode() == 69) {
                    spawnEnemy();
                }
            }
        }
    }
    public static void placeBomb(Entity entity) {
        if (entity instanceof Player) {
            if (playerBombPlaced) {
                return;
            }
            playerBombPlaced = true;
        }
        Projectile bomb = New.bomb(entity);
        bomb.box.setBounds(entity.box.getBounds());
        projectiles.add(bomb);
        Graphic.panel.repaint();
    }
    //Player
    public static void spawnPlayer() {
        player.setLocation(getRandomCoordinates());
        for (int i=0;i<terrains.size();i++) {
            for (int j=0;j<enemies.size();j++) {
                if (terrains.get(i).isPassable()) {
                    if (Physics.checkIntersect(terrains.get(i).box, player.box) || Physics.checkIntersect(enemies.get(j).box,player.box)) {
                        player.setLocation(getRandomCoordinates());
                        i = 0;
                        j = 0;
                    }
                }
            }
        }
    }
    public static void playerMove(ArrayList<KeyState> keyStates) {
        player.move(keyStates,terrains);
    }
    //Enemy
    public static void spawnEnemy() {
        Enemy enemy = New.slime();
        enemy.setLocation(getRandomCoordinates());
        for(int i=0;i<terrains.size();i++) {
            if (!terrains.get(i).isPassable()) {
                if (Physics.checkIntersect(terrains.get(i).box, enemy.box)) {
                    enemy.setLocation(getRandomCoordinates());
                    i = 0;
                }
            }
        }
        enemies.add(enemy);
    }
    //Terrain
    public static void generateTerrain() {
        int size = 28*16;
        for (int i=0;i<size;i++) {
            Terrain temp;
            Random rand = new Random();
            if (rand.nextInt(0,3+1) != 0) {
                temp = New.grass();
            }
            else {
                temp = New.tree();
            }
            temp.setLocation(getRandomCoordinates());
            if (terrains.size()==0) {
                terrains.add(temp);
            }
            for (int j=0;j<terrains.size();j++) {
                if (Physics.checkIntersect(terrains.get(j).box,temp.box)) {
                    temp.setLocation(getRandomCoordinates());
                    j=0;
                }
            }
            terrains.add(temp);
        }
    }
    public static int getRandom(int from, int to, int distance) {
        Random rand = new Random();
        return rand.nextInt(from/distance,to/distance)*distance;
    }
    public static Dimension getRandomCoordinates() {
        Dimension dim = new Dimension();
        dim.height = getRandom(0,800,50);
        dim.width = getRandom(0,1400,50);
        return dim;
    }
    //Other
    public static void processDeath() {
        for (int i=0;i<terrains.size();i++) {
            if (terrains.get(i).getHeath() <= 0) {
                New.grass(terrains.get(i));
            }
        }
        for (int i=0;i<enemies.size();i++) {
            if (enemies.get(i).getHeath() <= 0) {
                Graphic.panel.remove(enemies.get(i).box);
                Graphic.panel.repaint();
                enemies.remove(i);
            }
        }
    }
}