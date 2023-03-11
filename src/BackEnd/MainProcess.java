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
    public static Player player = New.player();
    // dùng để tạo cooldown cho việc đặt bom của người chơi
    public static boolean playerBombPlaced = false;
    // Projectile

    /**
     * Xừ lý thời gian tồn tại của bom/đạn
     */
    public static void projectileDecay() {
        for (int i=0;i<projectiles.size();i++) {
            projectiles.get(i).reduceDecay();
            if (projectiles.get(i).getDecay() == 0) {
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
     * Xử lý đầu vào từ bàn phím
     * @param keyStates : trạng thái của bàn phím
     */
    public static void playerInput(ArrayList<KeyState> keyStates) {
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getState()) {
                int key = keyStates.get(i).getKeyCode();
                if (key == 32) {
                    placeBomb(player);
                }
                if (key == 69) {
                    spawnEnemy();
                }
                if (key == 37 || key == 38 || key == 39 || key == 40) {
                    MyClock.playerMove(keyStates);
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
    //Terrain

    /**
     * Khởi tạo địa hình ngẫu nhiên
     */
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
        dim.height = getRandom(0,800,50);
        dim.width = getRandom(0,1400,50);
        return dim;
    }
    //Other

    /**
     * Phương thức để loại bỏ các đối tượng đã chết (Heath<=0)
     */
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