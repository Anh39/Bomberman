package Entities;

import BackEnd.DefaultParameter;
import BackEnd.KeyState;
import BackEnd.Physics;
import Graphic.Graphic;

import java.util.ArrayList;

/**
 * Kế thừ từ Entity
 * Đối tượng người chơi
 */
public class Player extends Entity{
    /**
     * Khởi tạo mặc định
     */
    public Player() {
        this.box.setLocation(0,0);
    }
    /**
     * Phương thức để di chuyển nhân vật
     * @param keyStates: Trạng thái bàn phím
     * @param terrains: Địa hình
     */
    public void move(ArrayList<KeyState> keyStates, ArrayList<Terrain> terrains) {
        int x = this.box.getX();
        int y = this.box.getY();
        int moveX = 0;
        int moveY = 0;
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getState()) {
                switch (keyStates.get(i).getKeyCode()) {
                    case 37:
                        moveX -= this.getSpeed();
                        break;
                    case 38:
                        moveY -= this.getSpeed();
                        break;
                    case 39:
                        moveX += this.getSpeed();
                        break;
                    case 40:
                        moveY += this.getSpeed();
                        break;
                    default:
                        break;
                }
            }
        }
        boolean con1 = x+moveX>=0;
        boolean con2 = y+moveY>=0;
        boolean con3 = x+moveX<=Graphic.panel.getWidth() -this.box.getWidth();
        boolean con4 = y+moveY<=Graphic.panel.getHeight()-this.box.getHeight();
        boolean con = con1 && con2 && con3 && con4;
        if ((!Physics.checkIntersectTerrain(terrains,this,moveX,moveY)) && con) {
            this.setLocation(x + moveX, y + moveY);
        }
    }

    @Override
    public void setDefault() {
        bar.setMaximum(DefaultParameter.playerMaxHeath);
        setMaxHeath(DefaultParameter.playerMaxHeath);
        setHeath(DefaultParameter.playerHeath);
        setDamage(DefaultParameter.playerDamage);
        setSpeed(DefaultParameter.playerSpeed);
        setRange(DefaultParameter.playerRange);
        setAngle(DefaultParameter.playerAngle);
    }
}
