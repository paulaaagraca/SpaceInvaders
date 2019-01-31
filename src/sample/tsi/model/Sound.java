package sample.tsi.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {

    public File menuSound; //silence (?)

    Media gameSound = new Media(this.getClass().getResource("song.mp3").toExternalForm());
    MediaPlayer soundplayer = new MediaPlayer(gameSound);


    public Sound() {
        soundplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                soundplayer.seek(Duration.ZERO);
                soundplayer.play();
            }
        });
    }


    public void playsound() {
        soundplayer.play();
    }

    public void pausesound() {
        soundplayer.pause();
    }

    public void stopsound(){
        soundplayer.stop();
    }
}
