package Entities;

import BackEnd.KeyState;
import BackEnd.Physics;

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
        this.box.setLocation(50,50);
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
        boolean con = (x+moveX>=0) && (y+moveY>=0) && (x+moveX<=1400-50) && (y+moveY<=800-50);
        if ((!Physics.checkIntersectTerrain(terrains,this,moveX,moveY)) && con) {
            this.box.setLocation(x + moveX, y + moveY);
        }
    }
}
