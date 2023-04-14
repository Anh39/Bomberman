package BackEnd;
import javax.sound.sampled.*;

public class VolumeControl {
    public static void increaseVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float volume = gainControl.getValue();
        float newVolume = volume +1.0f;
        if (newVolume > gainControl.getMaximum()) {
            newVolume = gainControl.getMaximum();
        }
        gainControl.setValue(newVolume);
    }
    public static void decreaseVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float volume = gainControl.getValue();
        float newVolume = volume - 1.0f;
        if (newVolume < gainControl.getMinimum()) {
            newVolume = gainControl.getMinimum();
        }
        gainControl.setValue(newVolume);
    }
    public static void setVolume(Clip clip, float volume) {
        volume /= 100;
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}