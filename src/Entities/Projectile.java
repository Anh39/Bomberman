package Entities;

public class Projectile extends Entity{
    private boolean damageToEnemy = false;
    private boolean damageToPlayer = false;
    private boolean damageToTerrain = false;
    public int decay = 10; // n*10, n = seconds
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
