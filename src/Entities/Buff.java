package Entities;

/**
 * Đối tượng Buff
 */
public class Buff extends Entity{
    private int buffDuration; // Thời gian tồn tại của buff (Hết thời gian thì buff biến mất)
    private boolean received; // Kiểm tra xem người chơi đã tương tác với buff chưa (Bắt đầu tính thời gian tồn tại)
    public Entity receiver; // Lưu tham chiếu đến đối tượng tương tác với buff
    public Buff() {
        this.buffDuration = 1;
        this.received = false;
    }

    /**
     * Đặt thời gian tồn tại hiện tại của buff
     * @param buffDuration
     */
    public void setBuffDuration(int buffDuration) {
        this.buffDuration = buffDuration;
    }

    /**
     * Lấy về thời gian tồn tại hiện tại của buff
     * @return
     */

    public int getBuffDuration() {
        return buffDuration;
    }

    /**
     * Gán đối tượng nhận buff
     * @param received
     */
    public void setReceived(boolean received) {
        this.received = received;
    }

    /**
     * Kiểm tra xem buff đã nhận được chưa
     * @return
     */
    public boolean isReceived() {
        return received;
    }

    /**
     * Kích hoạt buff
     * @param entity
     */
    public void activeBuff(Entity entity) {
        receiver = entity;
        entity.addMaxHeath(this.getMaxHeath());
        entity.addHeath(this.getHeath());
        entity.addDamage(this.getDamage());
        entity.addSpeed(this.getSpeed());
        entity.addRange(this.getRange());
    }

    /**
     * Hủy buff
     */
    public void removeBuff() {
        receiver.addMaxHeath(-this.getMaxHeath());
        receiver.addHeath(-this.getHeath());
        receiver.addDamage(-this.getDamage());
        receiver.addSpeed(-this.getSpeed());
        receiver.addRange(-this.getRange());
    }
}
