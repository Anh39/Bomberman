package BackEnd;

public class SoundTrack {
    private static SoundPlayer soundtrack = new SoundPlayer("/Music/nhacnen.wav");
    public static void playSoundTrack() {
        VolumeControl.setVolume(soundtrack.getClip(),DefaultParameter.backgroundMusicVolume);
        soundtrack.playloop();
    }

    public static void off() {
        soundtrack.stop();
    }
    public static void updateMusic() {
        off();
        playSoundTrack();
    }
}
