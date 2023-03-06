package Process;

import Entities.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Process {
    ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    Player player = New.player();
    public static int renderTime = 0;
    public static void runTime() {
        renderTime++;
        if (renderTime > 10) {
            renderTime = 0;

        }
    }
    public static int getRandom(int from, int to, int distance) {
        Random rand = new Random();
        return rand.nextInt(from/distance,to/distance)*distance;
    }
    public static Dimension getRandomCoordinates() {
        Dimension dim = new Dimension();
        dim.height = getRandom(0,800,50);
        dim.width = getRandom(0,1400,50);
        return dim;
    }
}
