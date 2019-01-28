package sample.tsi.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.tsi.model.LevelBluePrint;
import sample.tsi.model.Player;
import sample.tsi.model.Sound;
import javafx.scene.canvas.Canvas;
import sample.tsi.controller.Interface;

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
    private double time;
    private Stage stage;

    //TODO:constructor
    /*//public GameManager(Player player){
        this.player = player;
    }
*/


    public void updateloop(){
        root = new Group();

        Interface ninterface = new Interface();
        //ninterface.invaders1();
        Scene setscene = new Scene(root, 512, 384);
        stage.setScene(setscene);

        Canvas canvas = new Canvas(512, 384);
        //navigate();

        final long startTime = System.nanoTime();
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
    }

}
