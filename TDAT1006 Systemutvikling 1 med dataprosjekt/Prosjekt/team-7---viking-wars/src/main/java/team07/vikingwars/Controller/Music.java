package team07.vikingwars.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Class for ingame music.
 */
public class Music {

    private String path = "/team07/vikingwars/Resources/soundtrack.mp3";
    private MediaPlayer mediaPlayer;

    /**
     * Method for playing music. The mediaplayer creates its own thread.
     */
    public void playMusic() throws Exception {

        System.out.println("Play Music");
        Media media = new Media(getClass().getResource(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Closing the app will call this method.
     */
    public void stopMusic() {

        mediaPlayer.stop();
    }
}
