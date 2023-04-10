package Graphic;

import BackEnd.*;
import Entities.*;

/**
 * Lớp thử nghiệm
 * Note: Chưa áp dụng trong code
 */
public abstract class TestModule {
    public static final boolean testEnable = false;
    public static void movePanel(int moveX,int moveY) {
        int x = Graphic.panel.getX();
        int y = Graphic.panel.getY();
        Graphic.panel.setLocation(x-moveX,y-moveY);
    }
    public static void panelTestInitialize() {
        Graphic.panel.setLocation(-400,-400);
        MainProcess.player.setLocation(800,600);
    }
}
