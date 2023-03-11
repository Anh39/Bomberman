package Entities;

/**
 * Kế thừa từ Entity
 * Đối tượng đạn/ bomb nói chung
 */

public class Projectile extends Entity{
    // Khả năng gây damage đến các đối tượng
    private boolean damageToEnemy = false;
    private boolean damageToPlayer = false;
    private boolean damageToTerrain = false;
    // Thời gian tồn tại của Projectile
    public int decay = 10; // = n*10, n = seconds
    public Projectile() {

    }

    public boolean isDamageToEnemy() {
        return damageToEnemy;
    }

    public boolean isDamageToPlayer() {
        return damageToPlayer;
    }

    public boolean isDamageToTerrain() {
        return damageToTerrain;
    }

    public void setDamageToEnemy(boolean damageToEnemy) {
        this.damageToEnemy = damageToEnemy;
    }

    public void setDamageToPlayer(boolean damageToPlayer) {
        this.damageToPlayer = damageToPlayer;
    }

    public void setDamageToTerrain(boolean damageToTerrain) {
        this.damageToTerrain = damageToTerrain;
    }
}
