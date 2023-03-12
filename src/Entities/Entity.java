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
    public JProgressBar bar;
    private int maxHeath;
    private int heath;
    private int damage;
    private int speed;
    private int range;
    private int heathRegen;
    private int regenDuration;
    // angle là góc quay, hiện tại chưa có ứng dụng
    private double angle;
    private String name;
    public Entity() {
        this.box = new JLabel();
        this.box.setBounds(50,50,50,50);
        this.box.setOpaque(false);
        this.bar = new JProgressBar();
        this.bar.setMinimum(0);
        this.bar.setMaximum(100);
        this.bar.setBounds(0,0,50,10);
        this.bar.setValue(50);
        this.bar.setStringPainted(true);
        this.bar.setForeground(Color.red);
        this.bar.setBackground(Color.white);
        this.maxHeath = 0;
        this.heath = 0;
        this.damage = 0;
        this.speed = 0;
        this.range = 0;
        this.heathRegen = 0;
        this.regenDuration = 0;
        this.angle = 0;
        this.name = "";
    }

    /**
     * Đặt mức mặc định
     */
    public void setDefault() {
        bar.setMaximum(DefaultParameter.entityMaxHeath);
        this.maxHeath = DefaultParameter.entityMaxHeath;
        this.heath = DefaultParameter.entityHeath;
        this.damage = DefaultParameter.entityDamage;
        this.speed = DefaultParameter.entitySpeed;
        this.range = DefaultParameter.entityRange;
        this.heathRegen = DefaultParameter.entityHeathRegen;
        this.regenDuration = DefaultParameter.entityRegenDuration;
        this.angle = DefaultParameter.entityAngle;
        this.name = DefaultParameter.entityName;
    }

    /**
     * Đặt mức 0 , dùng cho khởi tạo buff
     */
    public void setZero() {
        bar.setMaximum(0);
        bar.setVisible(false);
        this.heath = 0;
        this.damage = 0;
        this.speed = 0;
        this.range = 0;
        this.heathRegen = 0;
        this.regenDuration = 0;
        this.angle = 0;
        this.name = "Zero";
    }

    /**
     * Phương thức đặt lại vị trí của đối tượng
     * Dùng để rút gọn cho lệnh Entity.box.setLocation(int x,int y);
     */
    public void setLocation(Dimension dim) {
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        this.setLocation(x,y);
    }
    public void setLocation(int x, int y) {
        this.box.setLocation(x,y);
        //x -= this.box.getWidth()/2;
        y -= this.bar.getHeight() + this.box.getHeight()*0.1;
        this.bar.setLocation(x,y);
    }


    /**
     * Phương thức dùng để giảm máu
     * Dùng để rút gọn cho lệnh Entity.setHeath(Entity.getHeath()-damage);
     */
    public void reduceHeath(int damage) {
        this.heath -= damage;
    }
    public void calculateHeathBar() {
        this.bar.setValue(this.getHeath());
        this.bar.setString(String.valueOf(this.getHeath()));
    }
    //Getter,Setter

    public void setMaxHeath(int maxHeath) {
        this.maxHeath = maxHeath;
    }

    public int getMaxHeath() {
        return maxHeath;
    }

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

    public int getHeathRegen() {
        return heathRegen;
    }

    public void setHeathRegen(int heathRegen) {
        this.heathRegen = heathRegen;
    }

    public int getRegenDuration() {
        return regenDuration;
    }

    public void setRegenDuration(int regenDuration) {
        this.regenDuration = regenDuration;
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
