package Entities;

import BackEnd.DefaultParameter;

import javax.swing.*;
import java.awt.*;


/**
 * Lớp trừu tượng để làm lớp cha cho một số đối tượng khác trong chương trình.
 */
public abstract class Entity {
    // Dùng JLabel box để làm hitbox cho đối tượng
    public JLabel box;
    private int heath;
    private int damage;
    private int speed;
    private int range;
    // angle là góc quay, hiện tại chưa có ứng dụng
    private double angle;
    private String name;
    public Entity() {
        this.box = new JLabel();
        this.box.setBounds(50,50,50,50);
        this.box.setOpaque(false);
        this.heath = 0;
        this.damage = 0;
        this.speed = 0;
        this.range = 0;
        this.angle = 0;
        this.name = "";
    }

    /**
     * Đặt mức mặc định
     */
    public void setDefault() {
        this.heath = DefaultParameter.entityHeath;
        this.damage = DefaultParameter.entityDamage;
        this.speed = DefaultParameter.entitySpeed;
        this.range = DefaultParameter.entityRange;
        this.angle = DefaultParameter.entityAngle;
        this.name = DefaultParameter.entityName;
    }

    /**
     * Đặt mức 0 , dùng cho khởi tạo buff
     */
    public void setZero() {
        this.heath = 0;
        this.damage = 0;
        this.speed = 0;
        this.range = 0;
        this.angle = 0;
        this.name = "Zero";
    }

    /**
     * Phương thức đặt lại vị trí của đối tượng
     * Dùng để rút gọn cho lệnh Entity.box.setLocation(int x,int y);
     */
    public void setLocation(Dimension dim) {
        this.box.setLocation((int)dim.getWidth(),(int)dim.getHeight());
    }

    /**
     * Phương thức dùng để giảm máu
     * Dùng để rút gọn cho lệnh Entity.setHeath(Entity.getHeath()-damage);
     */
    public void reduceHeath(int damage) {
        this.heath -= damage;
    }
    //Getter,Setter
    public void setHeath(int heath) {
        this.heath = heath;
    }

    public int getHeath() {
        return heath;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
