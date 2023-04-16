package Entities;

import BackEnd.DefaultParameter;
import BackEnd.MainProcess;
import BackEnd.Physics;
import Graphic.Graphic;

import java.util.Random;

/**
 * Kế thừa từ Entity.
 * Đối tượng kẻ địch
 */

public class Enemy extends Entity{
    int count = 0;
    int moveX = 0;
    int moveY = getSpeed();
    //Biến để quản lý render
    private int renderState = 0;
    //Chỉnh xem quái có thể đặt bom hay không
    private boolean canPlaceBomb = true;
    //cooldown ở đây là khoảng thời gian giữa 2 lần đặt bom
    private int cooldown;
    public Enemy() {
    }
    public int getRenderState() {
        return renderState;
    }

    public void setRenderState(int renderState) {
        this.renderState = renderState;
    }

    /**
     * Tăng trạng thái render
     */
    public void addRenderState() {
        renderState++;
        if (renderState > DefaultParameter.maxRenderStates) {
            renderState = 1;
        }
    }

    public boolean isCanPlaceBomb() {
        return canPlaceBomb;
    }

    public void setCanPlaceBomb(boolean canPlaceBomb) {
        this.canPlaceBomb = canPlaceBomb;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown;
    }

    /**
     * Giảm cooldown đi 1
     */
    public void reduceCooldown() {
        if (cooldown >0) {
            cooldown--;
        }
    }

    /**
     * Phương thức dùng để di chuyển kẻ địch
     * Tính theo 1 lần quét của MyClock (tầm 1 giây quét 1 lần)
     */
    public void move() {
        int x = this.box.getX(); // Tọa độ hiện tại
        int y = this.box.getY(); // Tọa độ hiện tại
        count++;

        if (count == 10) {
            Random rand = new Random();
            int direction = rand.nextInt(0, 1000);

            if (direction >= 0 && direction <= 250) {
                moveX = this.getSpeed();
                moveY = 0;
            } else if (direction >= 251 && direction <= 500) {
                moveX = -this.getSpeed();
                moveY = 0;
            } else if (direction >= 501 && direction <= 750) {
                moveX = 0;
                moveY = this.getSpeed();
            } else {
                moveX = 0;
                moveY = -this.getSpeed();
            }
            count = 0;
        }

        if ((!Physics.checkIntersectTerrain(MainProcess.terrains,this,moveX,moveY))
                && x+moveX>=0 && y+moveY>=0
                && x+moveX<=Graphic.panel.getWidth() -this.box.getWidth()
                && y+moveY<=Graphic.panel.getHeight()-this.box.getHeight()) { // Kiểm tra xem có giao với địa hình không
            x += moveX; y += moveY;
            this.setLocation(x, y);
        } else {
            Random rand = new Random();
            int direction = rand.nextInt(0, 1000);

            if (direction >= 0 && direction <= 250) {
                moveX = this.getSpeed();
                moveY = 0;
            } else if (direction >= 251 && direction <= 500) {
                moveX = -this.getSpeed();
                moveY = 0;
            } else if (direction >= 501 && direction <= 750) {
                moveX = 0;
                moveY = this.getSpeed();
            } else {
                moveX = 0;
                moveY = -this.getSpeed();
            }
        }
    }

    /**
     * Thiết lập mặc định
     */
    @Override
    public void setDefault() {
        bar.setMaximum(DefaultParameter.enemyMaxHeath);
        setCanPlaceBomb(DefaultParameter.canEnemyPlaceBomb);
        setMaxHeath(DefaultParameter.enemyMaxHeath);
        setHeath(DefaultParameter.enemyHeath);
        setDamage(DefaultParameter.enemyDamage);
        setSpeed(DefaultParameter.enemySpeed);
        setRange(DefaultParameter.enemyRange);
        setAngle(DefaultParameter.enemyAngle);
    }
}
