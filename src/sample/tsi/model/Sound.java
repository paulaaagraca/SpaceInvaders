package sample.tsi.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import java.io.File;

public class Sound {
    public File crashSound;

    public File menuSound; //silence (?)

    Media gameSound = new Media(this.getClass().getResource("sample/stuff/music.mp3").toExternalForm());
    MediaPlayer soundplayer = new MediaPlayer(gameSound);

    public void playsound(){
        soundplayer.play();
    }

    public void pausesound(){
        soundplayer.pause();
    }
}
