package sample.tsi.controller;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.tsi.model.LevelBluePrint;
import sample.tsi.model.Player;
import sample.tsi.model.Sound;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

public class GameManager {
    public int gamestate;
    public String[] registeredplayers;
    public String timer;
    public Player player;
    public Sound sound;
    public LevelBluePrint level;
    public Interface uinterface;
    public Group root;
    public boolean pauseFlag;
    public double time;
    public Stage stage;
    int W = 512;
    int H = 384;

    boolean goup = false,
            goright = false,
            goleft = false,
            godown = false,
            paused = false,
            space = false;


    //constructor
    public GameManager(Player player, Stage stage){ //add level
        this.player = player;
        this.stage = stage;
    }

    public void loop() throws IOException {

        root = new Group();
        Interface theinterface = new Interface();

        Scene setscene = new Scene(root, W, H, Color.MIDNIGHTBLUE);
        stage.setScene(setscene);

        Canvas canvas = new Canvas(W, H);
        root.getChildren().add(theinterface.showspaceship());

        moveTo(W / 2, H / 2, theinterface.showspaceship());

        //Scene scene = new Scene(dungeon, W, H, Color.FORESTGREEN);





/*        final long startTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime){
                if(!pauseFlag){
                    time = (currentNanoTime - startTime) / 1000000000.0;
                }
            }
        }.start();

    }


    void navigate (Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT){
                    //goLeft
                }
                else if (keyEvent.getCode() == KeyCode.P){
                    pausegame();
                }
            }
        });
    }
    void moveSpaceShip(){

    }

    void playSound(){

    }

    void moveRocket(){

    }

    void submit(){

    }

    void loadlevel(){

    }

    String[] getHighscore(){
        return null;
    }

    void loadLevel(){
        level = new LevelBluePrint();

    }

    void checkCollision(){

    }

    public void pausegame(){
        pauseFlag = !pauseFlag;
        if(pauseFlag){


        }
    }*/

    }

    private void moveBy(int dx, int dy, Group g) {
        if (dx == 0 && dy == 0) return;

        final double cx = g.getBoundsInLocal().getWidth() / 2;
        final double cy = g.getBoundsInLocal().getHeight() / 2;

        double x = cx + g.getLayoutX() + dx;
        double y = cy + g.getLayoutY() + dy;

        moveTo(x, y, g);
    }

    private void moveTo(double x, double y, Node g) {

        final double cx = g.getBoundsInLocal().getWidth() ;
        final double cy = g.getBoundsInLocal().getHeight();

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            g.relocate(x - cx, y - cy);
        }
    }

}
