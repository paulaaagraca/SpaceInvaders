package sample.tsi.controller;

import javafx.animation.AnimationTimer;

import javafx.scene.Node;
import javafx.stage.Stage;
import sample.tsi.model.*;

import java.io.IOException;


public class GameManager {
    public int gamestate;
    public String[] registeredplayers;
    public Player player;
    public static Sound sound = new Sound();
    public Sound crashsound = new Sound();
    public boolean paused = true;
    public double time;
    public Stage stage;
    public Interface theinterface;
    int level;
    private double startTimePaused;
    private double timePaused = 0;
    private boolean endgame = false;


    //constructor
    public GameManager(Player player, Stage stage, Interface uinterface) {
        this.player = player;
        this.stage = stage;
        this.theinterface = uinterface;
    }

    public void loop() throws IOException {

        theinterface.invaders = theinterface.loadlevel(level, player,sound);

        final long startTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                if (!paused) {
                    time = (currentNanoTime - startTime - timePaused) / 1000000000.0;
                    theinterface.updateInterface();
                    checkcollisions();
                    if (theinterface.invaders.size() == 0 && !endgame) {
                        endgame = true;
                        System.out.println("invaders vector empty");
                        paused = true;

                        player.setHighscore((int)time/60 + ":" + (int)time%60);
                        System.out.println("highscore in gamemanager:" + player.getHighscore());
                        sound.stopsound();
                        theinterface.win(player);
                        this.stop();
                    }
                    theinterface.setTime((int) time);
                    if (theinterface.pause) {
                        paused = true;
                        startTimePaused = currentNanoTime;
                    }
                } else {
                    sound.pausesound();
                    timePaused = (currentNanoTime - startTimePaused);
                    if (!theinterface.pause) {
                        paused = false;
                        sound.playsound();
                    }
                }
            }
        };
        timer.start();
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void checkcollisions() {
        for (int i = 0; i < theinterface.rockets.size(); i++) {
            Rocket r = theinterface.rockets.get(i);
            for (int j = 0; j < theinterface.invaders.size(); j++) {
                Invader inv = theinterface.invaders.get(j);
                System.out.println("checking for invaders and rockets");
                if (inv.getx() + inv.getw() / 2 < r.getx() + 15 && inv.getx() + inv.getw() / 2 > r.getx() - 15 && inv.gety() < r.gety() + 10 && inv.gety() > r.gety() - 10) {
                    System.out.println("Rocket entered square");

                    for (int k = 0; k < theinterface.root.getChildren().size(); k++) {
                        Node n = theinterface.root.getChildren().get(k);
                        if (n.getLayoutX() == r.getx() && n.getLayoutY() == r.gety()) {
                            theinterface.root.getChildren().remove(k);
                            theinterface.rockets.remove(i);
                            System.out.println("Removed rocket");
                        }
                    }
                    for (int u = 0; u < theinterface.root.getChildren().size(); u++) {
                        Node n = theinterface.root.getChildren().get(u);
                        if (n.getLayoutX() == inv.getx() && n.getLayoutY() == inv.gety()) {
                            theinterface.root.getChildren().remove(u);
                            theinterface.invaders.remove(j);
                            System.out.println("Removed invader");
                        }

                    }
                }
            }
        }
    }

/*


    void submit(){

    }

    String[] getHighscore(){
        return null;
    }

    }*/

}
