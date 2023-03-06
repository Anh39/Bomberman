package Graphics;

import Entities.New;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    public JButton newGame = New.newGameButton();
    public JButton loadGame = New.loadGameButton();
    public JButton configGame = New.configGameButton();
    public JButton exitGame = New.exitGameButton();
    public int yDistance = 50;
    public Menu(MyPanel panel) {
        initialization();
        panel.add(newGame);
        panel.add(loadGame);
        panel.add(configGame);
        panel.add(exitGame);
    }
    public void initialization() {
        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        configGame.addActionListener(this);
        exitGame.addActionListener(this);
        newGame.setBounds(100,100,200,75);
        loadGame.setBounds(newGame.getX(),newGame.getY()+newGame.getHeight()+yDistance,newGame.getWidth(),newGame.getHeight());
        configGame.setBounds(loadGame.getX(),loadGame.getY()+loadGame.getHeight()+yDistance,loadGame.getWidth(),loadGame.getHeight());
        exitGame.setBounds(configGame.getX(),configGame.getY()+configGame.getHeight()+yDistance,configGame.getWidth(),configGame.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            System.out.println("New game button clicked");
        }
        else if (e.getSource() == loadGame) {
            System.out.println("Load game button clicked");
        }
        else if (e.getSource() == configGame) {
            System.out.println("Config game button clicked");
        }
        else if (e.getSource() == exitGame) {
            System.out.println("Exit game button clicked");
        }
    }
}
