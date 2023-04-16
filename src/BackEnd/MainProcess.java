package BackEnd;

import Entities.*;
import Graphic.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Lớp trừu tượng để tính toán đa số các tác vụ trong game
 */
public abstract class MainProcess {
    // Khởi tạo các đối tượng global
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Buff> buffs = new ArrayList<>();

    public static Player player = New.player();
    // Dùng để tạo cooldown cho việc đặt bom của người chơi
    public static boolean playerBombPlaced = false;
    public static void updateDifficulty() {
        DefaultParameter.enemySpawn = DefaultParameter.enemySpawnD / DefaultParameter.difficulty;
        DefaultParameter.buffSpawn = DefaultParameter.buffSpawnD * DefaultParameter.difficulty;
        DefaultParameter.enemyMaxHeath = DefaultParameter.enemyMaxHeathD * DefaultParameter.difficulty;
        DefaultParameter.enemyHeath = DefaultParameter.enemyHeathD * DefaultParameter.difficulty;
        DefaultParameter.enemyDamage = DefaultParameter.enemyDamageD * DefaultParameter.difficulty;
    }
    // Projectile

    /**
     * Xừ lý thời gian tồn tại của projectile
     */
    public static void projectileDecay() {
        for (int i=0;i<projectiles.size();i++) {
            projectiles.get(i).reduceDuration();
            if (projectiles.get(i).getDuration() == 0) {
                if (projectiles.get(i).getName().equals("Bomb")) {
                    denoteBomb(i);
                }
                Graphic.panel.remove(projectiles.get(i).box);
                projectiles.remove(i);
                i--;
            }
        }
    }

    /**
     * Kích nổ bom, tạo các mảnh bom
     * @param index : vị trí của quả bom trong ArrayList<Projectile>
     */
    public static void denoteBomb(int index) {
        Projectile temp = projectiles.get(index);
        int x = temp.box.getX();
        int y = temp.box.getY();
        int width = temp.box.getWidth();
        int height = temp.box.getHeight();
        Projectile centerFrag = New.bombFragment(temp);
        centerFrag.setLocation(x,y);
        projectiles.add(centerFrag);
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
        SoundPlayer.soundboom(); // Sound
    }

    /**
     * Đặt bom tại vị trí của đối tượng
     * @param entity : đối tượng bất kỳ
     */
    public static void placeBomb(Entity entity) {
        if (entity instanceof Player) {
            if (playerBombPlaced) {
                return;
            }
            playerBombPlaced = true;
        }
        Projectile bomb = New.bomb(entity);
        bomb.box.setBounds(entity.box.getBounds());
        int x = entity.box.getX()/50;
        int y = entity.box.getY()/50;
        bomb.box.setLocation(x*50,y*50);
        projectiles.add(bomb);

        SoundPlayer.sounddatboom(); // Sound
    }


    //Terrain

    /**
     * Khởi tạo địa hình ngẫu nhiên
     */
    public static void generateTerrain() {
        int height = (Graphic.panel.getHeight()) / 50;
        int width = (Graphic.panel.getWidth()) / 50;
        for (int i=1;i<width-1;i++) {
            for (int j=1;j<height-1;j++) {
                Terrain grass = New.floor();
                grass.setLocation(i*grass.box.getWidth(),j*grass.box.getHeight());
                terrains.add(grass);
            }
        }
        for (int i=0;i<height;i++) {
            Terrain border1 = New.border();
            Terrain border2 = New.border();
            border1.setLocation(0,i*border1.box.getHeight());
            border2.setLocation(Graphic.panel.getWidth()-border2.box.getWidth(),i*border2.box.getHeight());
            terrains.add(border1);
            terrains.add(border2);
        }
        for (int i=0;i<width;i++) {
            Terrain border1 = New.border();
            Terrain border2 = New.border();
            border1.setLocation(i*border1.box.getWidth(),0);
            border2.setLocation(i*border2.box.getWidth(),Graphic.panel.getHeight()-border2.box.getHeight());
            terrains.add(border1);
            terrains.add(border2);
        }
        int treeNumber = height*width/DefaultParameter.treeRatio;
        for (int i=0;i<treeNumber;i++) {
            ArrayList<Integer> indexArr = new ArrayList<Integer>();
            for (int j=0;j<terrains.size();j++) {
                if (terrains.get(j).isPassable()) {
                    if (!terrains.get(j).isOverlapped()) {
                        indexArr.add(j);
                    }
                }
            }
            Random rand = new Random();
            int index = rand.nextInt(0,indexArr.size());
            Terrain tree = New.wall();
            terrains.get(index).setOverlapped(true);
            tree.setLocation(terrains.get(index).box.getX(),terrains.get(index).box.getY());
            terrains.add(tree);
            //System.out.println( "Generating map : " + (i+1)*100/treeNumber + "%");
        }
    }

    /**
     * Phương thức lấy ngẫu nhiên 1 trục tọa độ và làm tròn theo distance
     * @param from
     * @param to
     * @param distance
     * @return
     */
    public static int getRandom(int from, int to, int distance) {
        Random rand = new Random();
        return rand.nextInt(from/distance+1,to/distance-1)*distance;
    }

    /**
     * Phương thức lấy ngẫu nhiên 2 trục tọa độ
     * @return trả về dưới dạng Dimenson, Dimenson.getWidth() để lấy tọa độ x, Dimenson.getHeight() để lấy tọa độ y.
     */
    public static Dimension getRandomCoordinates() {
        Dimension dim = new Dimension();
        dim.height = getRandom(0,Graphic.panel.getHeight(),DefaultParameter.labelHeight);
        dim.width = getRandom(0,Graphic.panel.getWidth(),DefaultParameter.labelWidth);
        return dim;
    }

    /**
     * Phương thức lấy về tọa độ không bị chiếm
     * @return
     */
    public static Dimension getAvailableCoordinates() {
        Entity dummy = New.floor();
        dummy.setLocation(getRandomCoordinates());
        for(int i=0;i<MainProcess.terrains.size();i++) {
            if (!MainProcess.terrains.get(i).isPassable()) {
                if (Physics.checkIntersect(MainProcess.terrains.get(i).box, dummy.box)) {
                    dummy.setLocation(MainProcess.getRandomCoordinates());
                    i = 0;
                }
            }
        }
        return new Dimension(dummy.box.getX(),dummy.box.getY());
    }
    //Other

    /**
     * Phương thức để loại bỏ các đối tượng đã chết (Heath<=0)
     */
    public static void processDeath() {
        for (int i=0;i<terrains.size();i++) {
            if (terrains.get(i).getHeath() <= 0) {
                Graphic.panel.remove(terrains.get(i).box);
                Graphic.panel.remove(terrains.get(i).bar);
                terrains.remove(i);
            }
        }
        for (int i=0;i<enemies.size();i++) {
            if (enemies.get(i).getHeath() <= 0) {
                Graphic.panel.remove(enemies.get(i).box);
                Graphic.panel.remove(enemies.get(i).bar);
                enemies.remove(i);
                player.addScore();
            }
        }
        for (int i=0;i<buffs.size();i++) {
            if (buffs.get(i).isReceived()) {
                if (buffs.get(i).getBuffDuration() == 0) {
                    buffs.get(i).removeBuff();
                    buffs.remove(i);
                }
                else if (buffs.get(i).getBuffDuration() < 0) {
                    buffs.remove(i);
                }
            }
        }
        if (player.getHeath()<=0) {
            Graphic.gameOver.setVisible(true);
        }
    }

    /**
     * Xử lý thanh trạng thái (Thanh HP)
     */
    public static void processStatusBar() {
        for (Terrain terrain : terrains) {
            terrain.calculateHeathBar();
        }
        for (Enemy enemy : enemies) {
            enemy.calculateHeathBar();
        }
        player.calculateHeathBar();
    }

    /**
     * Khởi tạo game mới
     */
    public static void newGame() {
        clearMap();
        terrains.clear();
        enemies.clear();
        projectiles.clear();
        buffs.clear();
        player.setDefault();
        generateTerrain();
        SpawnManager.spawnPlayer();
        Graphic.panel.setVisible(true);
        Graphic.gameOver.setVisible(false);
    }
    /**
     * Xóa các đối tượng khỏi màn hình (hình ảnh và health bar)
     */
    public static void clearMap() {
        for (Terrain terrain : terrains) {
            Graphic.panel.remove(terrain.box);
            Graphic.panel.remove(terrain.bar);
        }
        for (Enemy enemy : enemies) {
            Graphic.panel.remove(enemy.box);
            Graphic.panel.remove(enemy.bar);
        }
        for (Projectile projectile : projectiles) {
            Graphic.panel.remove(projectile.box);
            Graphic.panel.remove(projectile.bar);
        }
        for (Buff buff : buffs) {
            Graphic.panel.remove(buff.box);
            Graphic.panel.remove(buff.bar);
        }
        Graphic.panel.remove(player.box);
        Graphic.panel.remove(player.bar);
    }
    public static void changeEnemy() {
        for (Enemy enemy : MainProcess.enemies) {
            enemy.setCanPlaceBomb(DefaultParameter.canEnemyPlaceBomb);

        }
    }
}