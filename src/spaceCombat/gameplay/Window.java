package spaceCombat.gameplay;

import java.net.URL;
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
    private Boolean smth = true;
    private Boolean choosePlanet = false;
    private Boolean startWave = true;
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
    private Boolean end = false;
    private Boolean shop = false;
    private Boolean isBatBought = false;
    private Boolean isBuzzBought = false;
    private Boolean isDogeBought = false;

    private String selectedSkin = "doge";

    private Text notMoney;
    private Text cash;
    private Text bt;
    private Text but;
    private Text dt;
    private Text txt;
    private Text gameTitle;
    private Text playt;
    private Text sh;
    private Text q;
    private Text op;
    private Text fuelText;
    private Text laserBeamText;
    private Text time;
    private Text waveTime;

    private List<Node> aliensL = new ArrayList<>();
    private List<Node> aliensR = new ArrayList<>();
    private List<Node> rightShots = new ArrayList<>();
    private List<Node> leftShots = new ArrayList<>();

    private int position = 1;
    private int wave = 1;
    private static int tileSize = 32;
    private double aliensRandomness = 0.015;
    private double aliensSpeed = 2;
    private int ufoSpeed = 5;
    private int coins = 0;

    private Node player;
    private Node laser = initLaser();

    private AnimationTimer timer;
    private AnimationTimer menuTimer;

    private ImageView astronautV;
    private ImageView uv;
    private ImageView av;
    private ImageView aD;
    private ImageView eD;
    private ImageView sD;

    private ImageView imgV;
    ImageView imgV3;
    ImageView imgV2;
    ImageView imgV4;
    ImageView imgV1;

    private WaveTimer waveTimer;
    private Stopwatch stopwatch;

    private Rectangle fuelMeasurer;
    private Rectangle powershotMeasurer;
    private Rectangle r;
    private Rectangle boughtSkin;

    private Node fuelBar;
    private Node laserBar;

    private MusicPlayer music;
    private MediaPlayer shotSound;
    private MediaPlayer laserSound;
    private MediaPlayer mediaPlayer;
    private MediaPlayer gameOverSound;

    public void run() {
        launch(Window.class);
    }

    public Pane createMenu(int music) {

        if (root == null) {
            root = new Pane();
        } else {
            root.getChildren().clear();
        }

        if (timer != null) {
            timer.stop();

        }
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
        if (music == 1) {
            final URL songPath = getClass().getResource("/music/Coldplay - Viva La Vida(8-bit).mp3");
            Media sound = new Media(songPath.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(new Runnable() {

                @Override
                public void run() {
                    mediaPlayer.play();

                }

            });
            mediaPlayer.play();
        }
        gameTitle = new Text("Space Combat™");
        gameTitle.setFont(Font.font("Monospaced", 85));
        gameTitle.setFill(Color.WHITE);
        gameTitle.setX(330);
        gameTitle.setY(100);

        playt = new Text("Play");
        playt.setFont(Font.font("Monospaced", 50));
        playt.setFill(Color.WHITE);
        playt.setX(570);
        playt.setY(220);

        sh = new Text("Shop");
        sh.setFont(Font.font("Monospaced", 50));
        sh.setFill(Color.WHITE);
        sh.setX(570);
        sh.setY(300);

        op = new Text("Help");
        op.setFont(Font.font("Monospaced", 50));
        op.setFill(Color.WHITE);
        op.setX(570);
        op.setY(380);

        q = new Text("Quit");
        q.setFont(Font.font("Monospaced", 50));
        q.setFill(Color.WHITE);
        q.setX(570);
        q.setY(460);

        Image img = new Image("images/mars.png");
        imgV1 = new ImageView(img);
        imgV1.setX(800);
        imgV1.setY(400);
        imgV1.setFitWidth(200);
        imgV1.setFitHeight(200);

        Image img2 = new Image("images/green planet.png");
        imgV2 = new ImageView(img2);
        imgV2.setX(270);
        imgV2.setY(380);
        imgV2.setFitWidth(150);
        imgV2.setFitHeight(150);

        Image img3 = new Image("images/moon.png");
        imgV3 = new ImageView(img3);
        imgV3.setX(50);
        imgV3.setY(100);
        imgV3.setFitWidth(150);
        imgV3.setFitHeight(150);

        Image img4 = new Image("images/saturn.png");
        imgV4 = new ImageView(img4);
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
                    uv.setX(uv.getX() - ufoSpeed);
                }
                if (ufoRight) {
                    uv.setX(uv.getX() + ufoSpeed);
                }
            }

        };

        root.getChildren().add(imgV1);
        root.getChildren().add(imgV2);
        root.getChildren().add(imgV3);
        root.getChildren().add(imgV4);
        root.getChildren().add(av);
        root.getChildren().add(uv);
        root.getChildren().add(r);

        root.getChildren().add(gameTitle);
        root.getChildren().add(playt);
        root.getChildren().add(sh);
        root.getChildren().add(op);
        root.getChildren().add(q);

        menuTimer.start();
        return root;
    }

    public void choosePlanet() {
        menu = false;
        choosePlanet = true;

        root.getChildren().remove(gameTitle);
        root.getChildren().remove(playt);
        root.getChildren().remove(q);
        root.getChildren().remove(op);
        root.getChildren().remove(sh);
        root.getChildren().remove(av);
        root.getChildren().remove(r);

        Text t = new Text("Choose a planet");
        t.setFont(Font.font("Monospaced", 60));
        t.setFill(Color.WHITE);
        t.setX(380);
        t.setY(100);

        Text menu = new Text("Click \"x\" to return to menu");
        menu.setFont(Font.font("Monospaced", 30));
        menu.setFill(Color.WHITE);
        menu.setX(410);
        menu.setY(600);

        Image img = new Image("images/arrowD.png");
        aD = new ImageView(img);
        aD.setX(107);
        aD.setY(70);
        aD.setFitWidth(50);
        aD.setFitHeight(50);

        root.getChildren().add(t);
        root.getChildren().add(aD);
        root.getChildren().add(menu);
    }

    public Pane createGame(int planet) {
        root.getChildren().clear();
        mediaPlayer.stop();

        choosePlanet = false;

        music = new MusicPlayer();
        music.randomMusic();

        menu = false;

        if (planet == 2) {
            TileMapReader m = new TileMapReader(2);
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
        }
        if (planet == 3) {
            TileMapReader m = new TileMapReader(3);
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
                        Image img = new Image("images/moongr.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                    if (map[i][j] == 2) {
                        Image img = new Image("images/moonugr.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                }

            }
        }
        if (planet == 4) {
            TileMapReader m = new TileMapReader(4);
            int[][] map = m.readMap();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < getMapHeight(map); j++) {
                    if (map[i][j] == 0) {
                        Image img = new Image("images/greenSky.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                    if (map[i][j] == 1) {
                        Image img = new Image("images/greenGr.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                    if (map[i][j] == 2) {
                        Image img = new Image("images/greenCloud.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                }

            }
        }
        if (planet == 5) {
            TileMapReader m = new TileMapReader(5);
            int[][] map = m.readMap();

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < getMapHeight(map); j++) {
                    if (map[i][j] == 0) {
                        Image img = new Image("images/saturnSky.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                    if (map[i][j] == 1) {
                        Image img = new Image("images/saturnGround.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                    if (map[i][j] == 2) {
                        Image img = new Image("images/saturnCloud.png");
                        ImageView imgV = new ImageView(img);
                        imgV.setX(i * tileSize);
                        imgV.setY(j * tileSize);
                        imgV.setFitWidth(32);
                        imgV.setFitHeight(32);

                        root.getChildren().add(imgV);
                    }
                }

            }
        }
        player = initPlayer();
        fuelMeasurer = initFuelMeasurer();
        powershotMeasurer = initPowershotMeasurer();

        waveTime = new Text("");
        waveTime.setFont(Font.font("Monospaced", 55));
        waveTime.setFill(Color.WHITE);
        waveTime.setX(564);
        waveTime.setY(105);

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

        laserBar = initPowershotBar();
        fuelBar = initFuelBar();

        stopwatch = new Stopwatch(wave);
        stopwatch.start();

        root.getChildren().add(time);
        root.getChildren().add(waveTime);
        root.getChildren().add(player);
        root.getChildren().add(fuelText);
        root.getChildren().add(laserBeamText);
        root.getChildren().add(fuelBar);
        root.getChildren().add(fuelMeasurer);
        root.getChildren().add(laserBar);
        root.getChildren().add(powershotMeasurer);

        waveTimer = new WaveTimer();

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

                    play = stopwatch.getPlay();
                }
                if (!stopwatch.isAlive()) {
                    if (startWave) {

                        aliensSpeed += 0.6;
                        wave += 1;
                        aliensRandomness += 0.005;

                        waveTimer = new WaveTimer();
                        waveTimer.start();

                        startWave = false;
                    }
                    if (waveTimer.getSeconds() > 20) {
                        waveTime.setText("00:0" + (30 - waveTimer.getSeconds()));
                    } else {
                        waveTime.setText("00:" + (30 - waveTimer.getSeconds()));

                    }
                }
                if (waveTimer.getSeconds() == 30) {

                    stopwatch.setPlay(false);
                    startWave = true;
                    stopwatch = new Stopwatch(wave);
                    stopwatch.start();

                    waveTimer.stop();
                    waveTime.setText("");
                    waveTimer.setSeconds(0);
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
        Image astronaut = null;
        if (selectedSkin.equals("astr")) {
            astronaut = new Image("images/ar.png");
            astronautV = new ImageView(astronaut);
            astronautV.setFitWidth(170);
            astronautV.setFitHeight(170);
            astronautV.setX(300);
            astronautV.setY(360);
        }
        if (selectedSkin.equals("bat")) {
            astronaut = new Image("images/batmanr.png");
            astronautV = new ImageView(astronaut);
            astronautV.setFitWidth(170);
            astronautV.setFitHeight(170);
            astronautV.setX(300);
            astronautV.setY(347);
        }
        if (selectedSkin.equals("buzz")) {
            astronaut = new Image("images/buzzr.png");
            astronautV = new ImageView(astronaut);
            astronautV.setFitWidth(170);
            astronautV.setFitHeight(170);
            astronautV.setX(300);
            astronautV.setY(354);
        }
        if (selectedSkin.equals("doge")) {
            astronaut = new Image("images/doger.png");
            astronautV = new ImageView(astronaut);
            astronautV.setFitWidth(170);
            astronautV.setFitHeight(170);
            astronautV.setX(300);
            astronautV.setY(345);
        }

        return astronautV;
    }

    private Node initRightShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        if (selectedSkin.equals("doge")) {
            shotV.setY(player.getTranslateY() + 414);
        } else {
            shotV.setY(player.getTranslateY() + 394);

        }
        shotV.setX(player.getTranslateX() + 460);
        shotV.setFitWidth(50);
        shotV.setFitHeight(35);
        root.getChildren().add(shotV);

        return shotV;
    }

    private Node initLeftShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        if (selectedSkin.equals("doge")) {
            shotV.setY(player.getTranslateY() + 414);
        } else {
            shotV.setY(player.getTranslateY() + 394);

        }
        shotV.setX(player.getTranslateX() + 260);
        shotV.setFitWidth(50);
        shotV.setFitHeight(35);
        root.getChildren().add(shotV);

        return shotV;
    }

    @Override
    public void start(Stage stage) throws Exception {

        boughtSkin = new Rectangle(190, 5, Color.GREENYELLOW);
        boughtSkin.setTranslateX(70);
        boughtSkin.setTranslateY(440);

        Scene scene = new Scene(createMenu(1));
        stage.setScene(scene);
        stage.setMaxWidth(1300);
        stage.setMaxHeight(656);
        stage.setTitle("Space Combat™");
        stage.setResizable(false);
        stage.getIcons().add(new Image("images/SpaceCombatIcon.png"));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case RIGHT:
                if (!menu && !choosePlanet && !shop) {
                    if (!isPlayerDead) {
                        moveRight();

                    }
                }
                if (choosePlanet && aD.getX() == 876) {
                    aD.setX(1015);
                    aD.setY(160);
                }
                if (choosePlanet && aD.getX() == 321) {
                    aD.setX(876);
                    aD.setY(360);
                }
                if (choosePlanet && aD.getX() == 107) {
                    aD.setX(321);
                    aD.setY(350);
                }
                if (end) {
                    eD.setX(824);
                }
                if (shop&&sD.getX()!=1110) {
                    
                    sD.setX(sD.getX() + 320);
                }
                break;
            case LEFT:
                if (end) {
                    eD.setX(470);
                }
                if (!menu && !choosePlanet && !shop) {
                    if (!isPlayerDead) {
                        moveLeft();
                    }
                }
                if (choosePlanet && aD.getX() == 321) {
                    aD.setX(107);
                    aD.setY(70);
                }
                if (choosePlanet && aD.getX() == 876) {
                    aD.setX(321);
                    aD.setY(350);
                }
                if (choosePlanet && aD.getX() == 1015) {
                    aD.setX(876);
                    aD.setY(360);
                }
                if (shop&&sD.getX()!=150) {
                    sD.setX(sD.getX() - 320);
                }
                break;
            case UP:
                if (!menu && !choosePlanet) {
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
                if (!menu && !choosePlanet) {
                    if (!isPlayerDead) {
                        if (!isJumping && !isFalling && !root.getChildren().contains(imgV)) {

                            if (selectedSkin.equals("astr")) {
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
                            if (selectedSkin.equals("bat")) {
                                if (position == 1) {

                                    Image img = new Image("images/MLG_Glasses.png");
                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 340);
                                    imgV.setY(player.getTranslateY() + 353);
                                    imgV.setFitWidth(80);
                                    imgV.setFitHeight(50);

                                    root.getChildren().add(imgV);
                                }
                                if (position == 0) {

                                    Image img = new Image("images/MLG_Glasses2.png");
                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 352);
                                    imgV.setY(player.getTranslateY() + 353);
                                    imgV.setFitWidth(80);
                                    imgV.setFitHeight(50);

                                    root.getChildren().add(imgV);
                                }
                            }
                            if (selectedSkin.equals("buzz")) {
                                if (position == 1) {

                                    Image img = new Image("images/MLG_Glasses.png");
                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 362);
                                    imgV.setY(player.getTranslateY() + 363);
                                    imgV.setFitWidth(40);
                                    imgV.setFitHeight(25);

                                    root.getChildren().add(imgV);
                                }
                                if (position == 0) {

                                    Image img = new Image("images/MLG_Glasses2.png");
                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 367);
                                    imgV.setY(player.getTranslateY() + 364);
                                    imgV.setFitWidth(40);
                                    imgV.setFitHeight(25);

                                    root.getChildren().add(imgV);
                                }
                            }
                            if (selectedSkin.equals("doge")) {
                                if (position == 1) {
                                    Image img = new Image("images/MLG_Glasses2.png");

                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 344);
                                    imgV.setY(player.getTranslateY() + 355);
                                    imgV.setFitWidth(80);
                                    imgV.setFitHeight(50);

                                    root.getChildren().add(imgV);
                                }
                                if (position == 0) {

                                    Image img = new Image("images/MLG_Glasses.png");
                                    imgV = new ImageView(img);

                                    imgV.setX(player.getTranslateX() + 345);
                                    imgV.setY(player.getTranslateY() + 355);
                                    imgV.setFitWidth(80);
                                    imgV.setFitHeight(50);

                                    root.getChildren().add(imgV);
                                }
                            }
                        }
                    }
                }
                break;

            case SPACE:
                if (!menu && !choosePlanet && !shop) {
                    if (!isPlayerDead) {

                        shot();
                    }
                }
                break;
            case E:
                if (!menu && !choosePlanet) {
                    if (!isPlayerDead) {
                        if (powershot) {
                            laserShot();
                        }
                    }
                }
                break;
            case ENTER:
                if (shop && sD.getX() == 150) {
                    selectedSkin = "astr";
                    boughtSkin.setTranslateX(70);
                    notMoney.setText("");
                }
                if (shop && sD.getX() == 470) {

                    if (!isBatBought) {
                        if (coins < 500) {
                            notMoney.setText("Not enough money!");
                        }
                        if (coins >= 500) {
                            notMoney.setText("");
                            selectedSkin = "bat";
                            bt.setText("Unlocked");
                            bt.setX(bt.getX() + 20);
                            boughtSkin.setTranslateX(400);
                            isBatBought = true;
                            coins -= 500;
                            cash.setText(coins + " coins");

                            final URL songPath = getClass().getResource("/music/cash.mp3");
                            Media sound = new Media(songPath.toString());
                            MediaPlayer cash = new MediaPlayer(sound);
                            cash.play();
                        }
                    }
                    if (isBatBought) {
                        notMoney.setText("");
                        selectedSkin = "bat";
                        boughtSkin.setTranslateX(400);
                    }
                }
                if (shop && sD.getX() == 790) {

                    if (!isBuzzBought) {
                        if (coins < 1000) {
                            notMoney.setText("Not enough money!");
                        }
                        if (coins >= 1000) {
                            notMoney.setText("");
                            selectedSkin = "buzz";
                            but.setText("Unlocked");
                            but.setX(but.getX() + 20);
                            boughtSkin.setTranslateX(720);
                            isBuzzBought = true;
                            coins -= 1000;
                            cash.setText(coins + " coins");

                            final URL songPath = getClass().getResource("/music/cash.mp3");
                            Media sound = new Media(songPath.toString());
                            MediaPlayer cash = new MediaPlayer(sound);
                            cash.play();
                        }
                    }
                    if (isBuzzBought) {
                        notMoney.setText("");
                        selectedSkin = "buzz";
                        boughtSkin.setTranslateX(720);
                    }
                }
                if (shop && sD.getX() == 1110) {

                    if (!isDogeBought) {
                        if (coins < 2000) {
                            notMoney.setText("Not enough money!");
                        }
                        if (coins >= 2000) {
                            notMoney.setText("");
                            selectedSkin = "doge";
                            dt.setText("Unlocked");
                            dt.setX(dt.getX() + 20);
                            boughtSkin.setTranslateX(1040);
                            isDogeBought = true;
                            coins -= 2000;
                            cash.setText(coins + " coins");

                            final URL songPath = getClass().getResource("/music/cash.mp3");
                            Media sound = new Media(songPath.toString());
                            MediaPlayer cash = new MediaPlayer(sound);
                            cash.play();
                        }
                    }
                    if (isBuzzBought) {
                        notMoney.setText("");
                        selectedSkin = "doge";
                        boughtSkin.setTranslateX(1040);
                    }
                }

                if (choosePlanet && aD.getX() == 876) {

                    createGame(2);
                }
                if (choosePlanet && aD.getX() == 107) {

                    createGame(3);
                }
                if (choosePlanet && aD.getX() == 321) {

                    createGame(4);
                }
                if (choosePlanet && aD.getX() == 1015) {

                    createGame(5);
                }
                if (menu) {

                    if (av.getY() == 185) {
                        choosePlanet();
                    }
                    if (av.getY() == 265) {
                        shop();
                    }
                    if (av.getY() == 425) {
                        System.exit(0);
                    }
                }
                if (end && eD.getX() == 824) {
                    System.exit(0);
                }
                if (end && eD.getX() == 470) {
                    play = false;
                    smth = true;
                    choosePlanet = false;
                    startWave = true;
                    ufoLeft = true;
                    ufoRight = false;
                    isJumping = false;
                    isFalling = false;
                    hasShot = false;
                    addShot = false;
                    powershot = false;
                    decreasePowershot = true;
                    enablePowershot = false;
                    addLaser = false;
                    isPlayerDead = false;
                    menu = true;
                    end = false;

                    aD.setX(107);
                    av.setX(185);
                    eD.setX(470);

                    aliensL = new ArrayList<>();
                    aliensR = new ArrayList<>();
                    rightShots = new ArrayList<>();
                    leftShots = new ArrayList<>();

                    position = 1;
                    wave = 1;
                    tileSize = 32;
                    aliensRandomness = 0.015;
                    aliensSpeed = 2;
                    ufoSpeed = 5;

                    createMenu(1);
                }

                break;

            case Y:
                music.changeMusic();
                break;
            case U:
                music.stopMusic();
                break;
            case X:
                if (shop || choosePlanet) {

                    shop = false;
                    choosePlanet = false;

                    menuTimer.stop();

                    play = false;
                    smth = true;
                    choosePlanet = false;
                    startWave = true;
                    ufoLeft = true;
                    ufoRight = false;
                    isJumping = false;
                    isFalling = false;
                    hasShot = false;
                    addShot = false;
                    powershot = false;
                    decreasePowershot = true;
                    enablePowershot = false;
                    addLaser = false;
                    isPlayerDead = false;
                    menu = true;
                    end = false;

                    av.setX(185);

                    aliensL = new ArrayList<>();
                    aliensR = new ArrayList<>();
                    rightShots = new ArrayList<>();
                    leftShots = new ArrayList<>();

                    position = 1;
                    wave = 1;
                    tileSize = 32;
                    aliensRandomness = 0.015;
                    aliensSpeed = 2;
                    ufoSpeed = 5;

                    createMenu(0);
                }
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
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/afr.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanfr.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzfr.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/dogefr.png"));

            }
        } else {
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/ar.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanr.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzr.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/doger.png"));

            }
        }

    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 50);
        position = 0;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/afl.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanfl.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzfl.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/dogefl.png"));

            }
        } else {
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/al.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanl.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzl.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/dogefl.png"));

            }
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
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/afr.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanfr.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzfr.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/dogefr.png"));

            }
        } else if (position == 0) {
            if (selectedSkin.equals("astr")) {
                astronautV.setImage(new Image("images/afl.png"));
            }
            if (selectedSkin.equals("bat")) {
                astronautV.setImage(new Image("images/batmanfl.png"));

            }
            if (selectedSkin.equals("buzz")) {
                astronautV.setImage(new Image("images/buzzfl.png"));

            }
            if (selectedSkin.equals("doge")) {
                astronautV.setImage(new Image("images/dogefl.png"));

            }
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
                if (selectedSkin.equals("astr")) {
                    astronautV.setImage(new Image("images/ar.png"));
                }
                if (selectedSkin.equals("bat")) {
                    astronautV.setImage(new Image("images/batmanr.png"));

                }
                if (selectedSkin.equals("buzz")) {
                    astronautV.setImage(new Image("images/buzzr.png"));

                }
                if (selectedSkin.equals("doge")) {
                    astronautV.setImage(new Image("images/doger.png"));

                }
            } else if (position == 0) {
                if (selectedSkin.equals("astr")) {
                    astronautV.setImage(new Image("images/al.png"));
                }
                if (selectedSkin.equals("bat")) {
                    astronautV.setImage(new Image("images/batmanl.png"));

                }
                if (selectedSkin.equals("buzz")) {
                    astronautV.setImage(new Image("images/buzzl.png"));

                }
                if (selectedSkin.equals("doge")) {
                    astronautV.setImage(new Image("images/dogel.png"));

                }
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
                alienL.setTranslateX(alienL.getTranslateX() + aliensSpeed);
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
                alienR.setTranslateX(alienR.getTranslateX() - aliensSpeed);
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

        if (play && !end) {
            if (Math.random() < aliensRandomness) {
                aliensL.add(initAlienL());
            }
            if (Math.random() < aliensRandomness) {
                aliensR.add(initAlienR());
            }
        }
        if (isPlayerDead) {
            if (smth) {
                music.stopMusic();
                gameOver();

            }
            smth = false;
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
            if (selectedSkin.equals("doge")) {
                laser.setTranslateY(player.getTranslateY() + 430);

                if (position == 1) {
                    laser.setTranslateX(player.getTranslateX() + 448);
                }
                if (position == 0) {
                    laser.setTranslateX(player.getTranslateX() - 677);
                }
            } else {
                laser.setTranslateY(player.getTranslateY() + 410);
                if (position == 1) {
                    laser.setTranslateX(player.getTranslateX() + 468);
                }
                if (position == 0) {
                    laser.setTranslateX(player.getTranslateX() - 697);
                }
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

            final URL songPath = getClass().getResource("/music/Laser gun sound effect.mp3");
            Media sound = new Media(songPath.toString());
            shotSound = new MediaPlayer(sound);
            shotSound.play();

            hasShot = true;
            addShot = true;
        }
    }

    private void laserShot() {
        final URL songPath = getClass().getResource("/music/Laser beam.mp3");
        Media sound = new Media(songPath.toString());
        laserSound = new MediaPlayer(sound);
        laserSound.setAutoPlay(true);
        laserSound.play();

        decreasePowershot = true;
        enablePowershot = true;
        addLaser = true;

    }

    public void gameOver() {

        final URL songPath = getClass().getResource("/music/pacman.mp3");
        Media sound = new Media(songPath.toString());
        gameOverSound = new MediaPlayer(sound);
        gameOverSound.play();

        menuTimer.stop();

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        waveTimer.stop();
        stopwatch.stop();

        end = true;
        play = false;

        int tempCoins = ((wave - 1) * 100);
        coins += ((wave - 1) * 100);

        root.getChildren().remove(waveTime);
        root.getChildren().remove(time);
        root.getChildren().remove(fuelMeasurer);
        root.getChildren().remove(powershotMeasurer);
        root.getChildren().remove(fuelText);
        root.getChildren().remove(laserBeamText);
        root.getChildren().remove(fuelBar);
        root.getChildren().remove(laserBar);

        Text t = new Text("Game Over");
        t.setFont(Font.font("Monospaced", 75));
        t.setFill(Color.WHITE);
        t.setX(460);
        t.setY(100);

        txt = new Text("You survived until wave " + (wave - 1));

        txt.setFont(Font.font("Monospaced", 45));
        txt.setFill(Color.WHITE);
        txt.setX(330);
        txt.setY(220);

        Text txt2 = new Text(tempCoins + " coins earned");

        txt2.setFont(Font.font("Monospaced", 45));
        txt2.setFill(Color.WHITE);
        txt2.setX(440);
        txt2.setY(290);

        Image img = new Image("images/arrowD.png");
        eD = new ImageView(img);
        eD.setX(470);
        eD.setY(320);
        eD.setFitWidth(50);
        eD.setFitHeight(50);

        Text menu = new Text("Back to menu");
        menu.setFont(Font.font("Monospaced", 40));
        menu.setFill(Color.WHITE);
        menu.setX(350);
        menu.setY(400);

        Text quit = new Text("Quit");
        quit.setFont(Font.font("Monospaced", 40));
        quit.setFill(Color.WHITE);
        quit.setX(800);
        quit.setY(400);

        root.getChildren().add(txt);
        root.getChildren().add(txt2);
        root.getChildren().add(t);
        root.getChildren().add(eD);
        root.getChildren().add(menu);
        root.getChildren().add(quit);

    }

    public void shop() {

        shop = true;
        menu = false;

        root.getChildren().remove(gameTitle);
        root.getChildren().remove(playt);
        root.getChildren().remove(q);
        root.getChildren().remove(op);
        root.getChildren().remove(sh);
        root.getChildren().remove(av);
        root.getChildren().remove(r);
        root.getChildren().remove(imgV1);
        root.getChildren().remove(imgV2);
        root.getChildren().remove(imgV3);
        root.getChildren().remove(imgV4);

        Text text = new Text("Shop");
        text.setFont(Font.font("Monospaced", 65));
        text.setFill(Color.WHITE);
        text.setX(570);
        text.setY(100);

        Text menu = new Text("Click \"x\" to return to menu");
        menu.setFont(Font.font("Monospaced", 30));
        menu.setFill(Color.WHITE);
        menu.setX(410);
        menu.setY(600);

        cash = new Text(coins + " coins");
        cash.setFont(Font.font("Monospaced", 40));
        cash.setFill(Color.WHITE);
        cash.setX(1000);
        cash.setY(100);

        Image img = new Image("images/arrowD.png");
        sD = new ImageView(img);
        sD.setX(150);
        sD.setY(150);
        sD.setFitWidth(50);
        sD.setFitHeight(50);

        Image imgA = new Image("images/ar.png");
        ImageView a = new ImageView(imgA);
        a.setX(80);
        a.setY(250);
        a.setFitWidth(200);
        a.setFitHeight(200);

        Image imgB = new Image("images/batmanr.png");
        ImageView b = new ImageView(imgB);
        b.setX(400);
        b.setY(240);
        b.setFitWidth(200);
        b.setFitHeight(200);

        Image imgBu = new Image("images/buzzr.png");
        ImageView bu = new ImageView(imgBu);
        bu.setX(720);
        bu.setY(250);
        bu.setFitWidth(200);
        bu.setFitHeight(200);
        
        Image imgD = new Image("images/doger.png");
        ImageView d = new ImageView(imgD);
        d.setX(1040);
        d.setY(237);
        d.setFitWidth(200);
        d.setFitHeight(200);

        Text at = new Text("Unlocked");
        at.setFont(Font.font("Monospaced", 25));
        at.setFill(Color.WHITE);
        at.setX(110);
        at.setY(480);

        if (!isBatBought) {
            bt = new Text("500 coins");
            bt.setFont(Font.font("Monospaced", 25));
            bt.setFill(Color.WHITE);
            bt.setX(420);
            bt.setY(480);
        }
        if (!isBuzzBought) {
            but = new Text("1000 coins");
            but.setFont(Font.font("Monospaced", 25));
            but.setFill(Color.WHITE);
            but.setX(740);
            but.setY(480);
        }
        if (!isDogeBought) {
            dt = new Text("2000 coins");
            dt.setFont(Font.font("Monospaced", 25));
            dt.setFill(Color.WHITE);
            dt.setX(1060);
            dt.setY(480);
        }

        notMoney = new Text("");
        notMoney.setX(940);
        notMoney.setY(50);
        notMoney.setFont(Font.font("Monospaced", 30));
        notMoney.setFill(Color.WHITE);

        root.getChildren().add(text);
        root.getChildren().add(sD);
        root.getChildren().add(a);
        root.getChildren().add(b);
        root.getChildren().add(bu);
        root.getChildren().add(d);

        root.getChildren().add(boughtSkin);

        root.getChildren().add(at);
        root.getChildren().add(menu);
        root.getChildren().add(bt);
        root.getChildren().add(but);
        root.getChildren().add(dt);
        root.getChildren().add(notMoney);

        root.getChildren().add(cash);
    }
}
