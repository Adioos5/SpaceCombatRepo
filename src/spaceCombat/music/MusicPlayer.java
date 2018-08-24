package spaceCombat.music;

import java.io.File;
import java.net.URL;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    
    public void randomMusic() {
        int number = (int) (Math.random() * 10);
       
        if (number == 1 || number == 2) {
            playMusic("A-Ha - Take On Me(8-bit)");
        }
        if (number == 3 || number == 4) {
            playMusic("Toto - Africa(8-bit)");
        }
        if (number == 5 || number == 6) {
            playMusic("Blue (Da Ba Dee)(8-bit)");
        }
        if (number == 7 || number == 8) {
            playMusic("Mike Posner - I Took A Pill In Ibiza(8-bit)");
        }
        if (number == 9 || number == 0) {
            playMusic("Daft Punk - Get Lucky(8-bit)");
        }
    }

    private void playMusic(String songTitle) {

        if (songTitle.equals("A-Ha - Take On Me(8-bit)")) {

            final URL songPath = getClass().getResource("/music/A-Ha - Take On Me(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Toto - Africa(8-bit)")) {

            final URL songPath = getClass().getResource("/music/Toto - Africa(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Blue (Da Ba Dee)(8-bit)")) {

            final URL songPath = getClass().getResource("/music/Blue (Da Ba Dee)(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Mike Posner - I Took A Pill In Ibiza(8-bit)")) {

            final URL songPath = getClass().getResource("/music/Mike Posner - I Took A Pill In Ibiza(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Daft Punk - Get Lucky(8-bit)")) {

            final URL songPath = getClass().getResource("/music/Daft Punk - Get Lucky(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
    }
    public void stopMusic() {
        mediaPlayer.stop();
    }
}