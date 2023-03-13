package Entities;

/**
 * Đối tượng Buff
 */
public class Buff extends Entity{
    private int buffDuration;
    private boolean received;
    public Entity receiver;
    public Buff() {
        this.buffDuration = 1;
        this.received = false;
    }

    public void setBuffDuration(int buffDuration) {
        this.buffDuration = buffDuration;
    }

    public int getBuffDuration() {
        return buffDuration;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public boolean isReceived() {
        return received;
    }

    public void activeBuff(Entity entity) {
        receiver = entity;
        entity.addMaxHeath(this.getMaxHeath());
        entity.addHeath(this.getHeath());
        entity.addDamage(this.getDamage());
        entity.addSpeed(this.getSpeed());
        entity.addRange(this.getRange());
    }
    public void removeBuff() {
        receiver.addMaxHeath(-this.getMaxHeath());
        receiver.addHeath(-this.getHeath());
        receiver.addDamage(-this.getDamage());
        receiver.addSpeed(-this.getSpeed());
        receiver.addRange(-this.getRange());
    }
}
