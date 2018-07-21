import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Application {

    private Pane root;
    private int[][] map;
    private static int tileSize = 32;
    private Node player;
    private ImageView astronautV;
    private AnimationTimer timer;
    private Boolean isJumping = false;
    private Boolean isFalling = false;
    private int position = 1;
    private ImageView imgV;

    public void run() {
        launch(Window.class);
    }

    public Pane createMenu() {
        TileMapReader m = new TileMapReader();

        root = new Pane();
        map = m.readMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < getMapHeight(map); j++) {
                if (map[i][j] == 0) {
                    Image img = new Image("realSky.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }
                if (map[i][j] == 1) {
                    Image img = new Image("Mars ground.png");
                    ImageView imgV = new ImageView(img);
                    imgV.setX(i * tileSize);
                    imgV.setY(j * tileSize);
                    imgV.setFitWidth(32);
                    imgV.setFitHeight(32);

                    root.getChildren().add(imgV);
                }
                if (map[i][j] == 2) {
                    Image img = new Image("Mars upper ground.png");
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

        root.getChildren().add(player);

        timer = new AnimationTimer() {

            @Override
            public void handle(long a) {
                jumpingMechanics();

            }

        };

        timer.start();

        return root;
    }

    private Node initPlayer() {
        Image astronaut = new Image("BestAstronautRight.png");

        astronautV = new ImageView(astronaut);
        astronautV.setX(300);
        astronautV.setY(340);
        astronautV.setFitWidth(200);
        astronautV.setFitHeight(200);

        return astronautV;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createMenu());
        stage.setScene(scene);
        stage.setMaxWidth(1300);
        stage.setMaxHeight(700);
        stage.setTitle("Space Combat™");
        stage.getIcons().add(new Image("SpaceCombatIcon.png"));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();

                break;
            case UP:
                jump();
                break;
            case W:
                if (!isJumping && !root.getChildren().contains(imgV)) {

                    if (position == 1) {

                        Image img = new Image("MLG_Glasses.png");
                        imgV = new ImageView(img);

                        imgV.setX(player.getTranslateX() + 360);
                        imgV.setY(player.getTranslateY() + 350);
                        imgV.setFitWidth(80);
                        imgV.setFitHeight(50);

                        root.getChildren().add(imgV);
                    }
                    if (position == 0) {

                        Image img = new Image("MLG_Glasses2.png");
                        imgV = new ImageView(img);

                        imgV.setX(player.getTranslateX() + 360);
                        imgV.setY(player.getTranslateY() + 350);
                        imgV.setFitWidth(80);
                        imgV.setFitHeight(50);

                        root.getChildren().add(imgV);
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
            astronautV.setImage(new Image("BestAstronautFlyingRight.png"));
        } else {
            astronautV.setImage(new Image("BestAstronautRight.png"));

        }

    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 40);
        position = 0;

        if (root.getChildren().contains(imgV)) {
            root.getChildren().remove(imgV);
        }

        if (isJumping || isFalling) {
            astronautV.setImage(new Image("BestAstronautFlyingLeft.png"));
        } else {
            astronautV.setImage(new Image("BestAstronautLeft.png"));

        }
    }

    private void jump() {

        if (!isFalling) {
            isJumping = true;

            if (root.getChildren().contains(imgV)) {
                root.getChildren().remove(imgV);
            }
            if (position == 1) {
                astronautV.setImage(new Image("BestAstronautFlyingRight.png"));

            } else if (position == 0) {
                astronautV.setImage(new Image("BestAstronautFlyingLeft.png"));

            }
        }
    }

    private void jumpingMechanics() {
        System.out.println(player.getTranslateY());
        if (isJumping) {

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
                astronautV.setImage(new Image("BestAstronautRight.png"));

            } else if (position == 0) {
                astronautV.setImage(new Image("BestAstronautLeft.png"));

            }

        }
        if (isFalling) {
            player.setTranslateY(player.getTranslateY() + 5);
        }

    }
}
