package Entities;

/**
 * Kế thừa từ Entity
 * Đối tượng địa hình
 */

public class Terrain extends Entity{
    // Khả năng đi qua của địa hình . VD: cỏ thì đi qua được còn cây thì không
    // Note : Mỗi ô hiển thị trên màn hình đều chứa địa hình
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
