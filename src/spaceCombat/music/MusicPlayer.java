package spaceCombat.music;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

    public void randomMusic() {
        int number = (int) (Math.random() * 10);
        System.out.println(number);
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
            playMusic("Smash Mouth - All star(8-bit)");
        }
        if (number == 9 || number == 0) {
            playMusic("Daft Punk - Get Lucky(8-bit)");
        }
    }

    private void playMusic(String songTitle) {

        if (songTitle.equals("A-Ha - Take On Me(8-bit)")) {

            String songPath = "music/A-Ha - Take On Me(8-bit).mp3";
            Media sound = new Media(new File(songPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Toto - Africa(8-bit)")) {

            String songPath = "music/Toto - Africa(8-bit).mp3";
            Media sound = new Media(new File(songPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Blue (Da Ba Dee)(8-bit)")) {

            String songPath = "music/Blue (Da Ba Dee)(8-bit).mp3";
            Media sound = new Media(new File(songPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Smash Mouth - All star(8-bit)")) {

            String songPath = "music/Smash Mouth - All star(8-bit).mp3";
            Media sound = new Media(new File(songPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
        if (songTitle.equals("Daft Punk - Get Lucky(8-bit)")) {

            String songPath = "music/Daft Punk - Get Lucky(8-bit).mp3";
            Media sound = new Media(new File(songPath).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    randomMusic();

                }
            });

            mediaPlayer.play();
        }
    }
}