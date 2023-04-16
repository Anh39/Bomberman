package BackEnd;

import Entities.Buff;
import Entities.Enemy;
import Entities.New;
import Entities.Terrain;
import Graphic.Graphic;

import java.util.Random;

public abstract class SpawnManager {
    private static int enemyTime = 0;
    private static int buffTime = 0;
    public static void runTime() {
        enemyTime++;
        buffTime++;
        if (enemyTime > DefaultParameter.enemySpawn) {
            enemyTime=0;
            spawnEnemy();
        }
        if (buffTime > DefaultParameter.buffSpawn) {
            buffTime=0;
            spawnBuff();
        }
    }
    /**
     * Sinh ra kẻ địch tại vị trí ngẫu nhiên không giao với (địa hình không đi qua được)
     */
    public static void spawnEnemy() {
        Enemy enemy = New.slime();
        enemy.setLocation(MainProcess.getRandomCoordinates());
        for(int i=0;i<MainProcess.terrains.size();i++) {
            if (!MainProcess.terrains.get(i).isPassable()) {
                if (Physics.checkIntersect(MainProcess.terrains.get(i).box, enemy.box)) {
                    enemy.setLocation(MainProcess.getRandomCoordinates());
                    i = 0;
                }
            }
        }
        MainProcess.enemies.add(enemy);
    }

    /**
     * Sinh ra buff
     */
    public static void spawnBuff() {
        Random rand = new Random();
        int buffNumber = rand.nextInt(1, DefaultParameter.maxNumberOfBuffs + 1);
        // Làm giống vậy , dùng else if (buffNumber == 2 , 3 ,4 , ...)
        if (buffNumber == 1) {
            Buff buff = New.Health();
            MainProcess.buffs.add(buff);
        }
        //
        MainProcess.buffs.get(MainProcess.buffs.size() - 1).setLocation(MainProcess.getAvailableCoordinates());
    }
    /**
     * Dùng để khởi tạo người chơi để không dính vào cây, kẻ địch
     * Note : hiện tại chưa dùng
     */
    public static void spawnPlayer() {
        MainProcess.player.setLocation(MainProcess.getAvailableCoordinates());
        spawnEnemy();
    }
}
