package BackEnd;

import Entities.Enemy;
import Graphic.Graphic;
import Graphic.Render;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Lớp dùng để xử lý thời gian trong game
 * Note : Chỉ được khởi tạo duy nhất 1 lần (Do implements ActionListener nên không làm thành abstract được)
 */
public class MyClock implements ActionListener {
    public static int time = 0; // thời gian gốc , chưa dùng gì
    public static Timer timer ; // bộ đo thời gian
    public static int renderTime = 0; // Thời gian render
    public static int bombTime = 0; // Thời gian xử lý bom
    public static int damageTime = 0; // Thời gian xử lý sát thương của bom/đạn (Projectile)
    public static int enemyMoveTime = 0; // Thời gian kẻ địch di chuyển
    public static int playerMoveTime = 0; // THời gian người chơi di chuyển
    public static boolean playerMoveAvailable = false; // Biến dùng cho việc xử lý di chuyển của người chơi
    public static boolean isPlayerMove = false; // Biến dùng cho việc xử lý di chuyển của người chơi
    public static int playerMovedCount = 0; // Biến dùng cho việc xử lý di chuyển của người chơi
    private static ArrayList<KeyState> localKeyState; // Biến dùng cho việc xử lý di chuyển của người chơi

    /**
     * Bắt đầu bộ tính giờ
     */
    public MyClock() {
        timer = new Timer(10, this);
        timer.start();
    }

    /**
     * Xứ lý thời giian
     */
    public static void runTime() {
        time++;
        renderTime++;
        bombTime++;
        damageTime++;
        enemyMoveTime++;
        playerMoveTime++;
        if (playerMoveTime > 1) {
            playerMoveAvailable= true;
            playerMoveTime = 0;
        }
        if (renderTime > 10) {
            renderTime = 0;
            MainProcess.processDeath();
            Render.render(MainProcess.terrains,MainProcess.enemies,MainProcess.projectiles,MainProcess.player);
        }
        if (bombTime > 10) {
            bombTime = 0;
            MainProcess.playerBombPlaced = false;
            MainProcess.projectileDecay();
        }
        if (damageTime > 5) {
            Physics.processProjectileDamage(MainProcess.terrains,MainProcess.enemies,MainProcess.projectiles,MainProcess.player);
            damageTime = 0;
        }
        if (enemyMoveTime > 5) {
            enemyMoveTime = 0;
            for (Enemy enemy : MainProcess.enemies) {
                enemy.move();
            }
        }
        //Internal Cool down
        for (Enemy enemy : MainProcess.enemies) {
            if (enemy.getCooldown() > 300) {
                if (Physics.calculateDistance(enemy, MainProcess.player) < 300) {
                    MainProcess.placeBomb(enemy);
                    enemy.setCooldown(0);
                }
            }
            else {
                enemy.continueCooldown();
            }
        }

    }

    /**
     * Xử lý di chuyển cho người chơi, hiện vẫn hơi lỗi
     * @param keyStates
     */
    public static void playerMove(ArrayList<KeyState> keyStates) {
            if (!isPlayerMove) {
                localKeyState = keyStates;
                isPlayerMove = true;
            }
    }

    /**
     * Phần mặc định của bộ tính giờ
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        runTime();
        if (isPlayerMove) {
            if (playerMovedCount == 5) {
                playerMovedCount = 0;
                isPlayerMove = false;
            }
            else if (playerMoveAvailable) {
                MainProcess.player.move(localKeyState, MainProcess.terrains);
                playerMovedCount++;
            }
        }
    }
}
