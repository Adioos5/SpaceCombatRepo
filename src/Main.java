import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main {

    public static void main(String[] args) {
        JFXPanel panel = new JFXPanel();
        
        String songPath = "A-Ha - Take On Me(8-bit).mp3";
        Media sound = new Media(new File(songPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.play();

            }
        });

        mediaPlayer.play();
        
        Window window = new Window();
        window.run();
        
    }

}
