package Entities;

import java.awt.*;

public class Terrain extends Entity{
    private boolean passable;
    public Terrain() {
        passable = false;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }
}
