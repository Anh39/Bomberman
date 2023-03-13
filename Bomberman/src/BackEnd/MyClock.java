package BackEnd;

import Entities.Enemy;
import Graphic.Render;
import Graphic.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Lớp dùng để xử lý thời gian trong game
 */
public abstract class MyClock{
    public static int time = 0; // thời gian gốc , chưa dùng gì
    public static int renderDelay = 0; // Thời gian render
    public static int bombDelay = 0; // Thời gian xử lý bom
    public static int damageDelay = 0; // Thời gian xử lý sát thương của bom/đạn (Projectile)
    public static int enemyMoveDelay = 0; // Thời gian kẻ địch di chuyển
    public static int playerMoveDelay = 0; // THời gian người chơi di chuyển
    public static boolean playerMoveAvailable = false; // Biến dùng cho việc xử lý di chuyển của người chơi
    public static boolean isPlayerMove = false; // Biến dùng cho việc xử lý di chuyển của người chơi
    public static int playerMovedCount = 0; // Biến dùng cho việc xử lý di chuyển của người chơi
    private static Dimension playerMoveDim = new Dimension();
    public static int renderStateDelay = 0;
    public static int renderState = 1;

    /**
     * Bắt đầu bộ tính giờ
     */
    public static void startClock() {
        Timer timer; // bộ đo thời gian
        ActionListener actionListener = new ActionListener() {
            /**
             * Phần mặc định của bộ tính giờ
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                runTime();
            }
        };
        timer = new Timer(DefaultParameter.baseClockDelay, actionListener);
        timer.start();
    }

    /**
     * Xứ lý thời giian
     */
    public static void runTime() {
        time++;
        renderDelay++;
        bombDelay++;
        damageDelay++;
        enemyMoveDelay++;
        playerMoveDelay++;
        renderDelay++;
        renderStateDelay++;
        if (isPlayerMove) {
            //System.out.println(playerMovedCount);
            if (playerMovedCount == DefaultParameter.playerMoveCount) {
                playerMovedCount = 0;
                isPlayerMove = false;
            }
            else if (playerMoveAvailable) {
                MainProcess.player.move((int) playerMoveDim.getWidth(),(int) playerMoveDim.getHeight(),MainProcess.terrains);
                playerMovedCount++;
            }
        }
        if (playerMoveDelay > DefaultParameter.playerMoveDelay) {
            playerMoveAvailable= true;
            playerMoveDelay = 0;
        }
        if (renderDelay > DefaultParameter.renderDelay) {
            renderDelay = 0;
            StatusBar.updateStatusPanel();
            MainProcess.processDeath();
            MainProcess.processStatusBar();
            Render.render(MainProcess.terrains,MainProcess.enemies,MainProcess.projectiles,MainProcess.player);
        }
        if (bombDelay > DefaultParameter.bombDelay) {
            bombDelay = 0;
            MainProcess.playerBombPlaced = false;
            MainProcess.projectileDecay();
        }
        if (damageDelay > DefaultParameter.damageDelay) {
            Physics.processProjectileDamage(MainProcess.terrains,MainProcess.enemies,MainProcess.projectiles,MainProcess.player);
            damageDelay = 0;
        }
        if (enemyMoveDelay > DefaultParameter.enemyMoveDelay) {
            enemyMoveDelay = 0;
            for (Enemy enemy : MainProcess.enemies) {
                enemy.move();
            }
        }
        if (renderStateDelay > DefaultParameter.renderStateDelay) {
            renderStateDelay = 0;
            renderState++;
            if (renderState > DefaultParameter.maxRenderStates) {
                renderState = 1;
            }
        }
        //Internal Cool down
        for (Enemy enemy : MainProcess.enemies) {
            if (enemy.getCooldown() == 0) {
                if (Physics.calculateDistance(enemy, MainProcess.player) <DefaultParameter.enemySightDistance) {
                    MainProcess.placeBomb(enemy);
                    enemy.setCooldown(DefaultParameter.enemyCooldown);
                }
            }
            else {
                enemy.reduceCooldown();
            }
        }

    }

    /**
     * Xử lý di chuyển cho người chơi, hiện vẫn hơi lỗi
     * @param keyStates
     */
    public static void playerMove(ArrayList<KeyState> keyStates) {
            if (!isPlayerMove) {
                playerMoveDim = MainProcess.player.getMove(keyStates);
                isPlayerMove = true;
            }
    }


}
