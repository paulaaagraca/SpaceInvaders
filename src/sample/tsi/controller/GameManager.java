package sample.tsi.controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public Group root;
    public boolean paused;
    public double time;
    public Stage stage;
    public Interface theinterface;



    //constructor
    public GameManager(Player player, Stage stage, Interface uinterface) { //add level
        this.player = player;
        this.stage = stage;
        this.theinterface = uinterface;

    }

    public void loop() throws IOException {

        final long startTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {
                if(!paused){
                    time = (currentNanoTime - startTime) / 1000000000.0;
                }
            }
        };
        timer.start();
    }

    public void shoot(){
        theinterface.moverocket();
    }

/*

    void playSound(){

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

