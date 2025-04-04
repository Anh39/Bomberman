package Entities;

import BackEnd.DefaultParameter;
import BackEnd.KeyState;
import BackEnd.Physics;
import Graphic.Graphic;
import Graphic.AdventureMode;

import java.awt.*;
import java.util.ArrayList;

/**
 * Kế thừ từ Entity
 * Đối tượng người chơi
 */
public class Player extends Entity{
    public boolean invincible = false;
    public int invincibleTime = 0;
    public int speedTime = 0;
    // Điểm số của người chơi
    private int score;
    /**
     * Khởi tạo mặc định
     */
    public Player() {
        this.box.setLocation(0,0);
    }

    /**
     * Lấy trạng thái di chuyển hiện tại của người chơi
     * @param keyStates
     * @return
     */
    public Dimension getMove(ArrayList<KeyState> keyStates) {
        int moveX = 0;
        int moveY = 0;
        Dimension dim = new Dimension();
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getState()) {
                switch (keyStates.get(i).getKeyCode()) {
                    case 37:
                         moveX -= this.getSpeed();
                         this.setAngle(3*PI/2);
                        break;
                    case 38:
                        moveY -= this.getSpeed();
                        this.setAngle(0);
                        break;
                    case 39:
                        moveX += this.getSpeed();
                        this.setAngle(PI/2);
                        break;
                    case 40:
                        moveY += this.getSpeed();
                        this.setAngle(PI);
                        break;
                    default:
                        break;
                }
            }
        }
        dim.setSize(moveX,moveY);
        return dim;
    }
    /**
     * Phương thức để di chuyển nhân vật theo trạng thái hiện tại ( Lấy từ getMove bên trên)
     * @param terrains: Địa hình
     */
    public void move(int moveX,int moveY, ArrayList<Terrain> terrains) {
        int x = this.box.getX();
        int y = this.box.getY();
        boolean con1 = x+moveX>=0;
        boolean con2 = y+moveY>=0;
        boolean con3 = x+moveX<=Graphic.panel.getWidth() -this.box.getWidth();
        boolean con4 = y+moveY<=Graphic.panel.getHeight()-this.box.getHeight();
        boolean con = con1 && con2 && con3 && con4;
        if ((!Physics.checkIntersectTerrain(terrains,this,moveX,moveY)) && con) {
            this.setLocation(x + moveX, y + moveY);
            if (DefaultParameter.adventureMode) {
                AdventureMode.movePanel(moveX, moveY);
            }
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public void addScore() {
        score ++;
    }
    public void addScore(int amount) {
        score += amount;
    }

    /**
     * Thiết lập mặc định
     */
    @Override
    public void setDefault() {
        bar.setMaximum(DefaultParameter.playerMaxHeath);
        setMaxHeath(DefaultParameter.playerMaxHeath);
        setHeath(DefaultParameter.playerHeath);
        setDamage(DefaultParameter.playerDamage);
        setSpeed(DefaultParameter.playerSpeed);
        setRange(DefaultParameter.playerRange);
        setAngle(DefaultParameter.playerAngle);
        setScore(DefaultParameter.playerScore);
    }
}
