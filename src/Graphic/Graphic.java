package Graphic;

import BackEnd.*;
import Entities.Enemy;
import Entities.Player;
import Entities.Projectile;
import Entities.Terrain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Graphic implements KeyListener, ActionListener {
    public static ArrayList<KeyState> keyStates = new ArrayList<>();
    public static int time = 0;
    public static int renderTime = 0;
    public static Timer timer ;
    public static JFrame frame = new JFrame();
    public static MyPanel panel = new MyPanel();
    public Graphic() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        addMenu();
        timer = new Timer(10,this);
        timer.start();
    }
    public static void initialization() {
        MainProcess.generateTerrain();
        keyInitialization();
        StatusBar.initialization();
    }
    public static void runTime() {
        renderTime++;
        if (renderTime > 10) {
            renderTime = 0;
            Render.render(MainProcess.terrains,MainProcess.enemies,MainProcess.projectiles,MainProcess.player);
        }
    }
    public static void addMenu() {
        Menu.initialization();
        Menu.add(panel);
    }
    public static void removeMenu() {
        Menu.remove(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        this.runTime();
        MainProcess.runTime();
    }
    // Key Event
    private static void keyInitialization() {
        KeyState leftKey = new KeyState(37);
        KeyState upKey = new KeyState(38);
        KeyState rightKey = new KeyState(39);
        KeyState downKey = new KeyState(40);
        KeyState spaceKey = new KeyState(32);
        KeyState eKey = new KeyState(69);
        keyStates.add(leftKey);
        keyStates.add(rightKey);
        keyStates.add(upKey);
        keyStates.add(downKey);
        keyStates.add(spaceKey);
        keyStates.add(eKey);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getKeyCode() == e.getKeyCode()) {
                keyStates.get(i).setState(true);
            }
        }
        MainProcess.playerMove(keyStates);
        MainProcess.playerInput(keyStates);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        for (int i=0;i<keyStates.size();i++) {
            if (keyStates.get(i).getKeyCode() == e.getKeyCode()) {
                keyStates.get(i).setState(false);
            }
        }

    }
}
