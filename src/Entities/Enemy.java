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
        int moveX = 0; // Tọa độ X di chuyển thêm
        int moveY = 0; // Tọa độ Y di chuyển thêm
        int x = this.box.getX(); // Tọa độ hiện tại
        int y = this.box.getY(); // Tọa độ hiện tại

        // Đoạn code xác định hướng đi
        // this.getSpeed() để lấy tốc độ của quái
        // Physic.calculateDistance(player,this) để lấy khoảng cách giữa người chơi và quái, trả về double , quái , người chơi là 50*50
        // người chơi là MainProcess.player , quái là this ; hitbox là thêm .box vào : MainProcess.player.box, this.box
        // this.box.getX , getY , getWidth,getHeigh để lấy tọa độ và kích thước
        // trục tọa độ :
        /*
        0---------------->x
        |
        |
        |
        |
        mũi tên xuống
        |y
        //trục y hướng xuống dưới
         */

        // Chủ yếu là tính cho t cái moveX và moveY
        // Bắt đầu
        Random rand = new Random();
        int direction = rand.nextInt(0,3+1);

        if (direction == 0) {
            moveX += this.getSpeed();
        }
        else if (direction == 1) {
            moveX -= this.getSpeed();
        }
        else if (direction == 2) {
            moveY += this.getSpeed();
        }
        else  {
            moveY -= this.getSpeed();
        }

        // Kết thúc
        boolean con1 = x+moveX>=0;
        boolean con2 = y+moveY>=0;
        boolean con3 = x+moveX<=Graphic.panel.getWidth() -this.box.getWidth();
        boolean con4 = y+moveY<=Graphic.panel.getHeight()-this.box.getHeight();
        boolean con = con1 && con2 && con3 && con4; // Kiểm tra xem ngoài khung hình chưa
        if ((!Physics.checkIntersectTerrain(MainProcess.terrains,this,moveX,moveY)) && con) { // Kiểm tra xem có giao với địa hình không
                this.setLocation(x + moveX, y + moveY); // Di chuyển quái
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
