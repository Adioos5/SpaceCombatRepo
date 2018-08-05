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
import javafx.stage.Stage;
import spaceCombat.music.MusicPlayer;
import spaceCombat.tileMap.TileMapReader;

public class Window extends Application {

    private Pane root;

    private List<Node> aliensL = new ArrayList<>();
    private List<Node> aliensR = new ArrayList<>();
    private List<Node> rightShots = new ArrayList<>();
    private List<Node> leftShots = new ArrayList<>();
    private int[][] map;
    private static int tileSize = 32;
    private Node player;
    private ImageView astronautV;
    private AnimationTimer timer;

    private MediaPlayer shotSound;
    private MediaPlayer laserSound;

    private Boolean isJumping = false;
    private Boolean isFalling = false;
    private Boolean hasShot = false;
    private Boolean addShot = false;
    private Boolean powershot = false;
    private Boolean decreasePowershot = true;
    private Boolean enablePowershot = false;
    private Boolean addLaser = false;
    private Boolean isPlayerDead = false;

    private Rectangle fuelMeasurer;
    private Node laser = initLaser();
    private Rectangle powershotMeasurer;
    private int position = 1;
    private ImageView imgV;

    public void run() {
        launch(Window.class);
    }

    public Pane createMenu() {
        TileMapReader m = new TileMapReader();

        MusicPlayer music = new MusicPlayer();
        music.randomMusic();

        root = new Pane();
        map = m.readMap();
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
        root.getChildren().add(player);
        root.getChildren().add(initFuelInscription());
        root.getChildren().add(initPowershotInscription());
        root.getChildren().add(initFuelBar());
        root.getChildren().add(fuelMeasurer);
        root.getChildren().add(initPowershotBar());
        root.getChildren().add(powershotMeasurer);
        timer = new AnimationTimer() {

            @Override
            public void handle(long a) {
                onUpdate();
                shotMechanics();
                jumpingMechanics();
            }

        };

        timer.start();

        return root;
    }

    private Node initAlienL() {
        Image alien = new Image("images/AlienL.png");
        ImageView alienV = new ImageView(alien);
        alienV.setFitHeight(175);
        alienV.setFitWidth(90);
        alienV.setTranslateX(-50);
        alienV.setTranslateY(343);

        root.getChildren().add(alienV);

        return alienV;
    }

    private Node initAlienR() {
        Image alien = new Image("images/AlienR.png");
        ImageView alienV = new ImageView(alien);
        alienV.setFitHeight(175);
        alienV.setFitWidth(90);
        alienV.setTranslateX(1300);
        alienV.setTranslateY(343);

        root.getChildren().add(alienV);

        return alienV;
    }

    private Node initPowershotInscription() {
        Image fuel = new Image("images/powershot.png");
        ImageView fuelV = new ImageView(fuel);

        fuelV.setX(805);
        fuelV.setY(4);
        fuelV.setFitWidth(220);
        fuelV.setFitHeight(80);

        return fuelV;
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

    private Node initFuelInscription() {
        Image fuel = new Image("images/fuelxd.png");
        ImageView fuelV = new ImageView(fuel);

        fuelV.setX(35);
        fuelV.setY(17);
        fuelV.setFitWidth(110);
        fuelV.setFitHeight(50);

        return fuelV;
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
        Image astronaut = new Image("images/BestAstronautRight.png");

        astronautV = new ImageView(astronaut);
        astronautV.setX(300);
        astronautV.setY(340);
        astronautV.setFitWidth(200);
        astronautV.setFitHeight(200);

        return astronautV;
    }

    private Node initRightShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        shotV.setX(player.getTranslateX() + 480);
        shotV.setY(player.getTranslateY() + 365);
        shotV.setFitWidth(60);
        shotV.setFitHeight(40);
        root.getChildren().add(shotV);

        return shotV;
    }

    private Node initLeftShot() {
        Image shot = new Image("images/shot.png");

        ImageView shotV = new ImageView(shot);
        shotV.setX(player.getTranslateX() + 260);
        shotV.setY(player.getTranslateY() + 365);
        shotV.setFitWidth(60);
        shotV.setFitHeight(40);
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
                if (!isPlayerDead) {
                    moveRight();

                }
                break;
            case LEFT:
                if (!isPlayerDead) {
                    moveLeft();
                }
                break;
            case UP:
                if (!isPlayerDead) {
                    jump();
                }
                break;
            case W:
                if (!isPlayerDead) {
                    if (!isJumping && !isFalling && !root.getChildren().contains(imgV)) {

                        if (position == 1) {

                            Image img = new Image("images/MLG_Glasses.png");
                            imgV = new ImageView(img);

                            imgV.setX(player.getTranslateX() + 360);
                            imgV.setY(player.getTranslateY() + 350);
                            imgV.setFitWidth(80);
                            imgV.setFitHeight(50);

                            root.getChildren().add(imgV);
                        }
                        if (position == 0) {

                            Image img = new Image("images/MLG_Glasses2.png");
                            imgV = new ImageView(img);

                            imgV.setX(player.getTranslateX() + 360);
                            imgV.setY(player.getTranslateY() + 350);
                            imgV.setFitWidth(80);
                            imgV.setFitHeight(50);

                            root.getChildren().add(imgV);
                        }
                    }
                }
                break;

            case SPACE:
                if (!isPlayerDead) {

                    shot();
                }
                break;
            case E:
                if (!isPlayerDead) {
                    if (powershot) {
                        laserShot();
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
        player.setTranslateX(player.getTranslateX() + 40);
        position = 1;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            astronautV.setImage(new Image("images/BestAstronautFlyingRight.png"));
        } else {
            astronautV.setImage(new Image("images/BestAstronautRight.png"));

        }

    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 40);
        position = 0;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            astronautV.setImage(new Image("images/BestAstronautFlyingLeft.png"));
        } else {
            astronautV.setImage(new Image("images/BestAstronautLeft.png"));

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
            astronautV.setImage(new Image("images/BestAstronautFlyingRight.png"));

        } else if (position == 0) {
            astronautV.setImage(new Image("images/BestAstronautFlyingLeft.png"));

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
                astronautV.setImage(new Image("images/BestAstronautRight.png"));

            } else if (position == 0) {
                astronautV.setImage(new Image("images/BestAstronautLeft.png"));

            }

        }
        if (isFalling) {
            fuelMeasurer.setWidth(fuelMeasurer.getWidth() + 3);
            player.setTranslateY(player.getTranslateY() + 5);
        }

    }

    private Rectangle initLaser() {

        Rectangle laser = new Rectangle(1000, 10, Color.RED);

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

        if (Math.random() < 0.020) {
            aliensL.add(initAlienL());
        }
        if (Math.random() < 0.020) {
            aliensR.add(initAlienR());
        }

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

            laser.setTranslateY(player.getTranslateY() + 390);
            if (position == 1) {
                laser.setTranslateX(player.getTranslateX() + 490);
            }
            if (position == 0) {
                laser.setTranslateX(player.getTranslateX() - 690);
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
