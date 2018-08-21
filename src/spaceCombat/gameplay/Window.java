package spaceCombat.gameplay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spaceCombat.music.MusicPlayer;
import spaceCombat.tileMap.TileMapReader;

public class Window extends Application {

    private Pane root;
    private Boolean play = false;
    private List<Node> aliensL = new ArrayList<>();
    private List<Node> aliensR = new ArrayList<>();
    private List<Node> rightShots = new ArrayList<>();
    private List<Node> leftShots = new ArrayList<>();

    private static int tileSize = 32;
    private Node player;
    private ImageView astronautV;
    private AnimationTimer timer;

    private MusicPlayer music;
    private Rectangle r;
    private Boolean startWave = true;
    private WaveTimer thread;
    private MediaPlayer shotSound;
    private MediaPlayer laserSound;
    private ImageView uv;
    private Text fuelText;
    private Text laserBeamText;
    private Text time;
    private Text waveTime;
    private ImageView av;

    private Stopwatch stopwatch;

    private Boolean ufoLeft = true;
    private Boolean ufoRight = false;

    private Boolean isJumping = false;
    private Boolean isFalling = false;
    private Boolean hasShot = false;
    private Boolean addShot = false;
    private Boolean powershot = false;
    private Boolean decreasePowershot = true;
    private Boolean enablePowershot = false;
    private Boolean addLaser = false;
    private Boolean isPlayerDead = false;
    private Boolean menu = true;
    private MediaPlayer mediaPlayer;
    private Rectangle fuelMeasurer;
    private Node laser = initLaser();
    private Rectangle powershotMeasurer;
    private int position = 1;
    private ImageView imgV;

    private AnimationTimer menuTimer;

    public void run() {
        launch(Window.class);
    }

    public Pane createMenu() {
        root = new Pane();
        TileMapReader m = new TileMapReader(1);
        int[][] map = m.readMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < getMapHeight(map); j++) {
                if (map[i][j] == 0) {
                    Image img = new Image("images/realSky.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }

            }
        }
        String songPath = "music/Coldplay - Viva La Vida(8-bit).mp3";
        Media sound = new Media(new File(songPath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                mediaPlayer.play();

            }

        });
        mediaPlayer.play();

        Text gameTitle = new Text("Space Combat™");
        gameTitle.setFont(Font.font("Monospaced", 85));
        gameTitle.setFill(Color.WHITE);
        gameTitle.setX(330);
        gameTitle.setY(100);

        Text play = new Text("Play");
        play.setFont(Font.font("Monospaced", 50));
        play.setFill(Color.WHITE);
        play.setX(570);
        play.setY(220);

        Text sh = new Text("Shop");
        sh.setFont(Font.font("Monospaced", 50));
        sh.setFill(Color.WHITE);
        sh.setX(570);
        sh.setY(300);

        Text op = new Text("Help");
        op.setFont(Font.font("Monospaced", 50));
        op.setFill(Color.WHITE);
        op.setX(570);
        op.setY(380);

        Text q = new Text("Quit");
        q.setFont(Font.font("Monospaced", 50));
        q.setFill(Color.WHITE);
        q.setX(570);
        q.setY(460);

        Image img = new Image("images/mars.png");
        ImageView imgV = new ImageView(img);
        imgV.setX(800);
        imgV.setY(400);
        imgV.setFitWidth(200);
        imgV.setFitHeight(200);

        Image img2 = new Image("images/green planet.png");
        ImageView imgV2 = new ImageView(img2);
        imgV2.setX(270);
        imgV2.setY(380);
        imgV2.setFitWidth(150);
        imgV2.setFitHeight(150);

        Image img3 = new Image("images/moon.png");
        ImageView imgV3 = new ImageView(img3);
        imgV3.setX(50);
        imgV3.setY(100);
        imgV3.setFitWidth(150);
        imgV3.setFitHeight(150);

        Image img4 = new Image("images/saturn.png");
        ImageView imgV4 = new ImageView(img4);
        imgV4.setX(1000);
        imgV4.setY(200);
        imgV4.setFitWidth(80);
        imgV4.setFitHeight(80);

        Image a = new Image("images/arrow.png");
        av = new ImageView(a);
        av.setX(450);
        av.setY(185);
        av.setFitWidth(50);
        av.setFitHeight(50);

        Image u = new Image("images/ufo.png");
        uv = new ImageView(u);
        uv.setX(1301);
        uv.setY(500);
        uv.setFitWidth(180);
        uv.setFitHeight(90);

        r = new Rectangle(150, 5, Color.WHITE);
        r.setTranslateX(555);
        r.setTranslateY(235);

        menuTimer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                if (uv.getX() > 1400) {
                    ufoLeft = true;
                    ufoRight = false;
                }
                if (uv.getX() < -300) {
                    ufoLeft = false;
                    ufoRight = true;
                }
                if (ufoLeft) {
                    uv.setX(uv.getX() - 5);
                }
                if (ufoRight) {
                    uv.setX(uv.getX() + 5);
                }
            }

        };

        root.getChildren().add(imgV);
        root.getChildren().add(imgV2);
        root.getChildren().add(imgV3);
        root.getChildren().add(imgV4);
        root.getChildren().add(av);
        root.getChildren().add(uv);
        root.getChildren().add(r);

        root.getChildren().add(gameTitle);
        root.getChildren().add(play);
        root.getChildren().add(sh);
        root.getChildren().add(op);
        root.getChildren().add(q);

        menuTimer.start();
        return root;
    }

    public Pane createGame() {
        mediaPlayer.stop();

        TileMapReader m = new TileMapReader(2);

        music = new MusicPlayer();
        music.randomMusic();

        menu = false;
        root.getChildren().clear();
        int[][] map = m.readMap();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < getMapHeight(map); j++) {
                if (map[i][j] == 0) {
                    Image img = new Image("images/realSky.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }
                if (map[i][j] == 1) {
                    Image img = new Image("images/Mars ground.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }
                if (map[i][j] == 2) {
                    Image img = new Image("images/Mars upper ground.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }
            }

        }
        player = initPlayer();
        fuelMeasurer = initFuelMeasurer();
        powershotMeasurer = initPowershotMeasurer();

        waveTime = new Text("");
        waveTime.setFont(Font.font("Monospaced", 45));
        waveTime.setFill(Color.WHITE);
        waveTime.setX(490);
        waveTime.setY(57);

        time = new Text("");
        time.setFont(Font.font("Monospaced", 85));
        time.setFill(Color.WHITE);
        time.setX(620);
        time.setY(150);

        fuelText = new Text("FUEL");
        fuelText.setFont(Font.font("Monospaced", 45));
        fuelText.setFill(Color.WHITE);
        fuelText.setX(30);
        fuelText.setY(55);

        laserBeamText = new Text("LASER BEAM");
        laserBeamText.setFont(Font.font("Monospaced", 45));
        laserBeamText.setFill(Color.WHITE);
        laserBeamText.setX(750);
        laserBeamText.setY(55);

        stopwatch = new Stopwatch();
        stopwatch.start();

        root.getChildren().add(time);
        root.getChildren().add(waveTime);
        root.getChildren().add(player);
        root.getChildren().add(fuelText);
        root.getChildren().add(laserBeamText);
        root.getChildren().add(initFuelBar());
        root.getChildren().add(fuelMeasurer);
        root.getChildren().add(initPowershotBar());
        root.getChildren().add(powershotMeasurer);

        thread = new WaveTimer();

        timer = new AnimationTimer() {

            @Override
            public void handle(long a) {
                onUpdate();
                shotMechanics();
                jumpingMechanics();

                if (!stopwatch.isInterrupted()) {
                    time.setText(stopwatch.getMainText().getText());
                    time.setX(stopwatch.getMainText().getX());
                    time.setFont(stopwatch.getMainText().getFont());

                    if (stopwatch.getPlay()) {
                        play = true;

                    }
                }
                if (!stopwatch.isAlive()) {
                    if (startWave) {
                        thread.start();
                        startWave = false;
                    }
                    if (thread.getSeconds() > 20) {
                        waveTime.setText("00:0" + (30 - thread.getSeconds()));
                    } else {
                        waveTime.setText("00:" + (30 - thread.getSeconds()));

                    }
                }

            }
        };

        timer.start();

        return root;
    }

    private Node initAlienL() {
        Image alien = new Image("images/AlienL.png");
        ImageView alienV = new ImageView(alien);
        alienV.setFitHeight(160);
        alienV.setFitWidth(80);
        alienV.setTranslateX(-50);
        alienV.setTranslateY(355);

        root.getChildren().add(alienV);

        return alienV;
    }

    private Node initAlienR() {
        Image alien = new Image("images/AlienR.png");
        ImageView alienV = new ImageView(alien);
        alienV.setFitHeight(160);
        alienV.setFitWidth(80);
        alienV.setTranslateX(1300);
        alienV.setTranslateY(355);

        root.getChildren().add(alienV);

        return alienV;
    }

    private Node initPowershotBar() {
        Rectangle fuelBar = new Rectangle(200, 33, Color.WHITE);

        fuelBar.setX(1035);
        fuelBar.setY(25);

        return fuelBar;
    }

    private Rectangle initPowershotMeasurer() {
        Rectangle fuelMeasurer = new Rectangle(0, 17, Color.RED);

        fuelMeasurer.setX(1045);
        fuelMeasurer.setY(33);

        return fuelMeasurer;
    }

    private Node initFuelBar() {
        Rectangle fuelBar = new Rectangle(200, 33, Color.WHITE);

        fuelBar.setX(150);
        fuelBar.setY(25);

        return fuelBar;
    }

    private Rectangle initFuelMeasurer() {
        Rectangle fuelMeasurer = new Rectangle(180, 17, Color.LIMEGREEN);

        fuelMeasurer.setX(160);
        fuelMeasurer.setY(33);

        return fuelMeasurer;
    }

    private Node initPlayer() {
        Image astronaut = new Image("images/ar.png");

        astronautV = new ImageView(astronaut);
        astronautV.setX(300);
        astronautV.setY(360);
        astronautV.setFitWidth(170);
        astronautV.setFitHeight(170);

        return astronautV;
    }

    private Node initRightShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        shotV.setX(player.getTranslateX() + 460);
        shotV.setY(player.getTranslateY() + 394);
        shotV.setFitWidth(50);
        shotV.setFitHeight(35);
        root.getChildren().add(shotV);

        return shotV;
    }

    private Node initLeftShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        shotV.setX(player.getTranslateX() + 260);
        shotV.setY(player.getTranslateY() + 394);
        shotV.setFitWidth(50);
        shotV.setFitHeight(35);
        root.getChildren().add(shotV);

        return shotV;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createMenu());
        stage.setScene(scene);
        stage.setMaxWidth(1300);
        stage.setMaxHeight(700);
        stage.setTitle("Space Combat™");
        stage.getIcons().add(new Image("images/SpaceCombatIcon.png"));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case RIGHT:
                if (!menu) {
                    if (!isPlayerDead) {
                        moveRight();

                    }
                }
                break;
            case LEFT:
                if (!menu) {
                    if (!isPlayerDead) {
                        moveLeft();
                    }
                }
                break;
            case UP:
                if (!menu) {
                    if (!isPlayerDead) {
                        jump();
                    }
                }
                if (av.getY() != 185) {
                    av.setY(av.getY() - 80);

                }
                if (r.getTranslateY() != 235) {
                    r.setTranslateY(r.getTranslateY() - 80);
                }
                break;
            case DOWN:

                if (av.getY() != 425) {
                    av.setY(av.getY() + 80);
                }
                if (r.getTranslateY() != 475) {
                    r.setTranslateY(r.getTranslateY() + 80);
                }
                break;
            case W:
                if (!menu) {
                    if (!isPlayerDead) {
                        if (!isJumping && !isFalling && !root.getChildren().contains(imgV)) {

                            if (position == 1) {

                                Image img = new Image("images/MLG_Glasses.png");
                                imgV = new ImageView(img);

                                imgV.setX(player.getTranslateX() + 337);
                                imgV.setY(player.getTranslateY() + 360);
                                imgV.setFitWidth(80);
                                imgV.setFitHeight(50);

                                root.getChildren().add(imgV);
                            }
                            if (position == 0) {

                                Image img = new Image("images/MLG_Glasses2.png");
                                imgV = new ImageView(img);

                                imgV.setX(player.getTranslateX() + 350);
                                imgV.setY(player.getTranslateY() + 360);
                                imgV.setFitWidth(80);
                                imgV.setFitHeight(50);

                                root.getChildren().add(imgV);
                            }
                        }
                    }
                }
                break;

            case SPACE:
                if (!menu) {
                    if (!isPlayerDead) {

                        shot();
                    }
                }
                break;
            case E:
                if (!menu) {
                    if (!isPlayerDead) {
                        if (powershot) {
                            laserShot();
                        }
                    }
                }
                break;
            case ENTER:
                if (menu) {

                    if (av.getY() == 185) {
                        createGame();
                    }
                    if (av.getY() == 425) {
                        System.exit(0);
                    }
                }
                break;
            default:
            }
        });

        stage.show();

    }

    private int getMapHeight(int[][] map) {
        return map[0].length;
    }

    private void moveRight() {
        player.setTranslateX(player.getTranslateX() + 50);
        position = 1;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            astronautV.setImage(new Image("images/afr.png"));
        } else {
            astronautV.setImage(new Image("images/ar.png"));

        }

    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 50);
        position = 0;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            astronautV.setImage(new Image("images/afl.png"));
        } else {
            astronautV.setImage(new Image("images/al.png"));

        }
    }

    private void jump() {

        if (!isFalling) {
            isJumping = true;
        }

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }
        if (position == 1) {
            astronautV.setImage(new Image("images/afr.png"));

        } else if (position == 0) {
            astronautV.setImage(new Image("images/afl.png"));

        }
    }

    private void jumpingMechanics() {

        if (isJumping) {
            fuelMeasurer.setWidth(fuelMeasurer.getWidth() - 3);
            player.setTranslateY(player.getTranslateY() - 5);
        }
        if (player.getTranslateY() < -300) {
            isFalling = true;
            isJumping = false;
        }
        if (player.getTranslateY() == 0) {
            isFalling = false;
            isJumping = false;

            if (position == 1) {
                astronautV.setImage(new Image("images/ar.png"));

            } else if (position == 0) {
                astronautV.setImage(new Image("images/al.png"));

            }

        }
        if (isFalling) {
            fuelMeasurer.setWidth(fuelMeasurer.getWidth() + 3);
            player.setTranslateY(player.getTranslateY() + 5);
        }

    }

    private Rectangle initLaser() {

        Rectangle laser = new Rectangle(1000, 8, Color.RED);

        return laser;
    }

    private void onUpdate() {
        try {
            for (Node shot : rightShots) {
                shot.setTranslateX(shot.getTranslateX() + 10);
                for (int i = 0; i < aliensL.size(); i++) {
                    if (shot.getBoundsInParent().intersects(aliensL.get(i).getBoundsInParent())) {
                        root.getChildren().remove(aliensL.get(i));
                        root.getChildren().remove(shot);
                        rightShots.remove(shot);
                        aliensL.remove(aliensL.get(i));
                        if (powershotMeasurer.getWidth() <= 175) {
                            powershotMeasurer.setWidth(powershotMeasurer.getWidth() + 5);

                        }
                        if (powershotMeasurer.getWidth() >= 175) {
                            powershot = true;

                        }
                    }
                }
                for (int i = 0; i < aliensR.size(); i++) {
                    if (shot.getBoundsInParent().intersects(aliensR.get(i).getBoundsInParent())) {
                        root.getChildren().remove(aliensR.get(i));
                        root.getChildren().remove(shot);
                        rightShots.remove(shot);
                        aliensR.remove(aliensR.get(i));
                        if (powershotMeasurer.getWidth() <= 175) {
                            powershotMeasurer.setWidth(powershotMeasurer.getWidth() + 5);

                        }
                        if (powershotMeasurer.getWidth() >= 175) {
                            powershot = true;

                        }
                    }
                }
            }

            for (Node shot : leftShots) {
                shot.setTranslateX(shot.getTranslateX() - 10);
                for (int i = 0; i < aliensL.size(); i++) {
                    if (shot.getBoundsInParent().intersects(aliensL.get(i).getBoundsInParent())) {
                        root.getChildren().remove(aliensL.get(i));
                        root.getChildren().remove(shot);
                        leftShots.remove(shot);
                        aliensL.remove(aliensL.get(i));
                        if (powershotMeasurer.getWidth() <= 175) {
                            powershotMeasurer.setWidth(powershotMeasurer.getWidth() + 5);

                        }
                        if (powershotMeasurer.getWidth() >= 175) {
                            powershot = true;

                        }
                    }
                }
                for (int i = 0; i < aliensR.size(); i++) {
                    if (shot.getBoundsInParent().intersects(aliensR.get(i).getBoundsInParent())) {
                        root.getChildren().remove(aliensR.get(i));
                        root.getChildren().remove(shot);
                        leftShots.remove(shot);
                        aliensR.remove(aliensR.get(i));
                        if (powershotMeasurer.getWidth() <= 175) {
                            powershotMeasurer.setWidth(powershotMeasurer.getWidth() + 5);

                        }
                        if (powershotMeasurer.getWidth() >= 175) {
                            powershot = true;

                        }
                    }
                }
            }

            for (Node alienL : aliensL) {
                alienL.setTranslateX(alienL.getTranslateX() + 3);
                if (root.getChildren().contains(laser)) {
                    if (laser.getBoundsInParent().intersects(alienL.getBoundsInParent())) {
                        root.getChildren().remove(alienL);
                        aliensL.remove(alienL);
                    }
                }

                if (player.getBoundsInParent().intersects(alienL.getBoundsInParent())) {
                    root.getChildren().remove(player);
                    isPlayerDead = true;
                }
            }
            for (Node alienR : aliensR) {
                alienR.setTranslateX(alienR.getTranslateX() - 3);
                if (root.getChildren().contains(laser)) {
                    if (laser.getBoundsInParent().intersects(alienR.getBoundsInParent())) {
                        root.getChildren().remove(alienR);
                        aliensR.remove(alienR);
                    }
                }
                if (player.getBoundsInParent().intersects(alienR.getBoundsInParent())) {
                    root.getChildren().remove(player);
                    isPlayerDead = true;
                }
            }
        } catch (Exception e) {

        }

        if (play) {
            if (Math.random() < 0.020) {
                aliensL.add(initAlienL());
            }
            if (Math.random() < 0.020) {
                aliensR.add(initAlienR());
            }
        }
    }

    public void setPlay(Boolean play) {
        this.play = play;
    }

    private void shotMechanics() {

        if (enablePowershot) {
            if (addLaser) {
                addLaser = false;
                try {
                    root.getChildren().add(laser);
                } catch (Exception e) {

                }
            }

            laser.setTranslateY(player.getTranslateY() + 410);
            if (position == 1) {
                laser.setTranslateX(player.getTranslateX() + 470);
            }
            if (position == 0) {
                laser.setTranslateX(player.getTranslateX() - 700);
            }

        }

        if (decreasePowershot && powershotMeasurer.getWidth() != 0) {
            powershotMeasurer.setWidth(powershotMeasurer.getWidth() - 3);
        }
        if (powershotMeasurer.getWidth() <= 0) {
            if (laserSound != null) {
                laserSound.stop();
            }
            decreasePowershot = false;
            enablePowershot = false;
            powershot = false;
            if (root.getChildren().contains(laser)) {
                root.getChildren().remove(laser);
            }
        }

        if (hasShot) {
            if (position == 1) {

                if (addShot) {
                    rightShots.add(initRightShot());
                    addShot = false;
                }

            }
            if (position == 0) {
                if (addShot) {
                    leftShots.add(initLeftShot());
                    addShot = false;
                }

            }
        }
    }

    private void shot() {
        if (!enablePowershot) {

            Media sound = new Media(new File("music/Laser gun sound effect.mp3").toURI().toString());
            shotSound = new MediaPlayer(sound);
            shotSound.play();

            hasShot = true;
            addShot = true;
        }
    }

    private void laserShot() {
        Media sound = new Media(new File("music/Laser beam.mp3").toURI().toString());
        laserSound = new MediaPlayer(sound);
        laserSound.setAutoPlay(true);
        laserSound.play();

        decreasePowershot = true;
        enablePowershot = true;
        addLaser = true;

    }

}
