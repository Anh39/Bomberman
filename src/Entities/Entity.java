package Entities;

import javax.swing.*;
import java.awt.*;

public abstract class Entity {
    public JLabel box;
    private int heath;
    private int damage;
    private int speed;
    private int range;
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
    public void setDefault() {
        this.heath = 100;
        this.damage = 10;
        this.speed = 10;
        this.range = 1;
        this.angle = 0;
        this.name = "Default";
    }
    public void setZero() {
        this.heath = 0;
        this.damage = 0;
        this.speed = 0;
        this.range = 0;
        this.angle = 0;
        this.name = "Zero";
    }
    public void setLocation(Dimension dim) {
        this.box.setLocation((int)dim.getWidth(),(int)dim.getHeight());
    }
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
