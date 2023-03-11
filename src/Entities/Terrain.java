package Entities;

import BackEnd.DefaultParameter;

/**
 * Kế thừa từ Entity
 * Đối tượng địa hình
 */

public class Terrain extends Entity{
    // Khả năng đi qua của địa hình . VD: cỏ thì đi qua được còn cây thì không
    // Note : Mỗi ô hiển thị trên màn hình đều chứa địa hình
    private boolean passable;
    public Terrain() {
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    @Override
    public void setDefault() {
        bar.setMaximum(DefaultParameter.terrainMaxHeath);
        setMaxHeath(DefaultParameter.terrainMaxHeath);
        setHeath(DefaultParameter.terrainHeath);
        setDamage(DefaultParameter.terrainDamage);
        setSpeed(DefaultParameter.terrainSpeed);
        setRange(DefaultParameter.terrainRange);
        setAngle(DefaultParameter.terrainAngle);
        setPassable(DefaultParameter.terrainPassable);
    }
}
