package Entities;

import BackEnd.DefaultParameter;

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
    private int duration = 10; // = n*10, n = seconds
    public Projectile() {

    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void reduceDuration() {
        if (duration > 0) {
            duration--;
        }
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

    @Override
    public void setDefault() {
        bar.setMaximum(DefaultParameter.projectileMaxHeath);
        setMaxHeath(DefaultParameter.projectileMaxHeath);
        setHeath(DefaultParameter.projectileHeath);
        setDamage(DefaultParameter.projectileDamage);
        setSpeed(DefaultParameter.projectileSpeed);
        setRange(DefaultParameter.projectileRange);
        setHeathRegen(DefaultParameter.projectileHeathRegen);
        setRegenDuration(DefaultParameter.projectileRegenDuration);
        setAngle(DefaultParameter.projectileAngle);
    }
}
