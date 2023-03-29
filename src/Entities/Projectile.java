package Entities;

import BackEnd.DefaultParameter;

/**
 * Kế thừa từ Entity
 * Đối tượng đạn/ bomb nói chung
 */

public class Projectile extends Entity{
    private int renderState = 0;
    // Khả năng gây damage đến các đối tượng
    private boolean damageToEnemy = false;
    private boolean damageToPlayer = false;
    private boolean damageToTerrain = false;
    // Thời gian tồn tại của Projectile
    private int duration = 10; // = n*10, n = seconds
    private int maxDuration = duration;
    public Projectile() {

    }
    public int getRenderState() {
        return renderState;
    }

    public void setRenderState(int renderState) {
        this.renderState = renderState;
    }

    public void addRenderState() {
        renderState++;
        if (renderState > DefaultParameter.maxRenderStates) {
            renderState = 1;
        }
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
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
        setAngle(DefaultParameter.projectileAngle);
    }
}
