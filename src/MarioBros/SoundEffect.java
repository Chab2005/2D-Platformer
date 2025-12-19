package MarioBros;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum SoundEffect {
    MUSIC_1_1("audios/1-1.wav"),
    JUMP("audios/smb_jump-small.wav"),
    MURLOC("audios/murloc.wav"),
    PAUSE("audios/smb_pause.wav"),
    BUMP("audios/smb_bump.wav"),
    DIE("audios/smb_mariodie.wav"),
    STOMP("audios/smb_stomp.wav"),
    WIN("audios/smb_stage_clear.wav"),;

    private String path;

    SoundEffect(String path) {
        this.path = path;
    }

    public void play(String sound) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream(sound)
            );
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playOnce() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream(path)
            );
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream(path)
            );
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.stop();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
