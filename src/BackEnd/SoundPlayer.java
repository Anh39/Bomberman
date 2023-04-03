package BackEnd;


import Entities.New;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String path) {
        try {
            File file = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }

    public void play() {
        if (clip != null) {
            stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void stop() {
        if (clip.isRunning()) clip.stop();
    }

    public void close() {
        clip.close();
    }

    public void playloop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void soundboom() {
        SoundPlayer sound = new SoundPlayer("\\Music\\boom.wav");
        System.out.println("AAA");
        sound.play();
    }

    public static void soundnen() {
        SoundPlayer sound = new SoundPlayer("C:\\Users\\hungt\\IdeaProjects\\Bomberman (2)\\Bomberman\\src\\Music\\nhacnen.wav");
        sound.playloop();
    }

    public static void sounddefeat() {
        SoundPlayer sound = new SoundPlayer("C:\\Users\\hungt\\IdeaProjects\\Bomberman (2)\\Bomberman\\src\\Music\\defeat.wav");
        sound.play();
    }

    public static void soundwin() {
        SoundPlayer sound = new SoundPlayer("C:\\Users\\hungt\\IdeaProjects\\Bomberman (2)\\Bomberman\\src\\Music\\victory.wav");
        sound.play();
    }

    public static void sounddatboom() {
        SoundPlayer sound = new SoundPlayer("C:\\Users\\hungt\\IdeaProjects\\Bomberman (2)\\Bomberman\\src\\Music\\Music\\datboom.wav");
        sound.play();
    }
}

// Nguồn : https://tiengdong.com/
