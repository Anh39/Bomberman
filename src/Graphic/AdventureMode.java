package Graphic;

import BackEnd.*;

/**
 * Lớp thử nghiệm
 * Note: Chưa áp dụng trong code
 */
public abstract class AdventureMode {
    public static void movePanel(int moveX,int moveY) {
        int x = Graphic.panel.getX();
        int y = Graphic.panel.getY();
        Graphic.panel.setLocation(x-moveX,y-moveY);
    }
    public static void panelTestInitialize() {
        int x = MainProcess.player.box.getX();
        int y = MainProcess.player.box.getY();
        Graphic.panel.setLocation(DefaultParameter.panelWidth/2 - x,DefaultParameter.panelHeight/2 - y);
    }
}
