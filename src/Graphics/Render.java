package Graphics;

import Graphics.Menu;
import Graphics.MyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Render extends JFrame implements KeyListener, ActionListener {
    public static int time = 0;
    public static int renderTime = 0;
    public Timer timer ;
    public JFrame frame = new JFrame();
    public MyPanel panel = new MyPanel();
    public Menu menu = new Menu(panel);
    ArrayList<ImageIcon> playerImg = new ArrayList<ImageIcon>();
    ArrayList<ImageIcon> terrainImg = new ArrayList<ImageIcon>();
    ArrayList<ImageIcon> enemyImg = new ArrayList<ImageIcon>();
    public Render() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        timer = new Timer(10,this);
        timer.start();
    }
    public static void animation() {

    }
    public static void runTime() {
        renderTime++;
        if (renderTime > 10) {
            renderTime = 0;

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        runTime();
        Process.runTime();
        //System.out.println(time);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
