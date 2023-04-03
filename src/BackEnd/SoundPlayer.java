package BackEnd;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String path) {
        try {
            path = SoundPlayer.class.getResource(path).toString();
            path = path.substring(6,path.length());
            File file = new File(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Input Sound Error");
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
        SoundPlayer sound = new SoundPlayer("/Music/boom.wav");
        sound.play();
    }

    public static void soundnen() {
        SoundPlayer sound = new SoundPlayer("/Music/nhacnen.wav");
        sound.playloop();
    }

    public static void sounddefeat() {
        SoundPlayer sound = new SoundPlayer("/Music/defeat.wav");
        sound.play();
    }

    public static void soundwin() {
        SoundPlayer sound = new SoundPlayer("/Music/victory.wav");
        sound.play();
    }

    public static void sounddatboom() {
        SoundPlayer sound = new SoundPlayer("/Music/datboom.wav");
        sound.play();
    }
}

// Nguồn : https://tiengdong.com/
