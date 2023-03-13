package BackEnd;

import Entities.*;
import Graphic.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Lớp trừu tượng để tính toán đa số các tác vụ trong game
 */
public abstract class MainProcess {
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Buff> buffs = new ArrayList<>();

    public static Player player = New.player();
    // dùng để tạo cooldown cho việc đặt bom của người chơi
    public static boolean playerBombPlaced = false;
    // Projectile

    /**
     * Xừ lý thời gian tồn tại của bom/đạn
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
        projectiles.add(bomb);
        Graphic.panel.repaint();
    }
    //Player

    /**
     * Dùng để khởi tạo người chơi để không dính vào cây, kẻ địch
     * Note : hiện tại chưa dùng
     */
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
    //Enemy

    /**
     * Tạo ra kẻ địch tại vị trí ngẫu nhiên không giao với địa hình không đi qua được
     */
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
    public static void spawnBuff() {
        Random rand = new Random();
        int buffNumber = rand.nextInt(1,DefaultParameter.maxNumberOfBuffs+1);
        // Làm giống vậy , dùng else if (buffNumber == 2 , 3 ,4 , ...)
        if (buffNumber == 1) {
            Buff buff = New.Health();
            buffs.add(buff);
        }
        //
        buffs.get(buffs.size()-1).setLocation(getAvailableCoordinates());
    }
    //Terrain

    /**
     * Khởi tạo địa hình ngẫu nhiên
     */
    public static void generateTerrain() {
        int height = (Graphic.panel.getHeight()) / 50;
        int width = (Graphic.panel.getWidth()) / 50;
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                Terrain grass = New.grass();
                grass.setLocation(i*grass.box.getWidth(),j*grass.box.getHeight());
                terrains.add(grass);
            }
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
            Terrain tree = New.tree();
            tree.setLocation(terrains.get(index).box.getX(),terrains.get(index).box.getY());
            terrains.add(tree);
            System.out.println( "Generating map : " + (i+1)*100/treeNumber + "%");
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
        return rand.nextInt(from/distance,to/distance)*distance;
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
    public static Dimension getAvailableCoordinates() {
        ArrayList<Integer> indexArr = new ArrayList<Integer>();
        for (int i=0;i<terrains.size();i++) {
            if (terrains.get(i).isPassable()) {
                if (!terrains.get(i).isOverlapped()) {
                    indexArr.add(i);
                }
            }
        }
        Random rand = new Random();
        int index = rand.nextInt(0,indexArr.size());
        Dimension dim = new Dimension(terrains.get(index).box.getX(),terrains.get(index).box.getY());
        return dim;
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
                Graphic.panel.repaint();
                terrains.remove(i);
            }
        }
        for (int i=0;i<enemies.size();i++) {
            if (enemies.get(i).getHeath() <= 0) {
                Graphic.panel.remove(enemies.get(i).box);
                Graphic.panel.remove(enemies.get(i).bar);
                Graphic.panel.repaint();
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
    }
    public static void processStatusBar() {
        for (Terrain terrain : terrains) {
            terrain.calculateHeathBar();
        }
        for (Enemy enemy : enemies) {
            enemy.calculateHeathBar();
        }
        player.calculateHeathBar();
    }
    public static void newGame() {
        clearMap();
        terrains.clear();
        enemies.clear();
        projectiles.clear();
        buffs.clear();
        player = New.player();
        generateTerrain();
    }
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
}