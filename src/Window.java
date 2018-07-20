import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        astronautV.setImage(new Image("BestAstronautRight.png"));

    }

    private void moveLeft() {
        player.setTranslateX(player.getTranslateX() - 40);
        astronautV.setImage(new Image("BestAstronautLeft.png"));

    }

    private void jump() {

    }
}
