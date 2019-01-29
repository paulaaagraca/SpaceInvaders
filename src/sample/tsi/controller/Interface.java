package sample.tsi.controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.tsi.model.Invader;

import java.util.ArrayList;
import java.util.List;

public class Interface {
    public List<Invader> invaders;
    public static ImageView viewspaceship;
    public static Group spaceshipgroup;
    public Group root;
    public Stage stage;
    int W = 512;
    int H = 384;

    boolean goup = false,
            goright = false,
            goleft = false,
            godown = false,
            pause = false,
            space = false;

    public Interface(Stage stage) {
        this.stage = stage;

        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goup = true;
                        break;
                    case DOWN:
                        godown = true;
                        break;
                    case LEFT:
                        goleft = true;
                        break;
                    case RIGHT:
                        goright = true;
                        break;
                    case SPACE:
                        space = true;
                        break;
                    case P:
                        pause = true;
                        //pause();
                        break;
                }
            }
        });
        //stage.getScene().enableInputMethodEvents();
        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goup = false;
                        break;
                    case DOWN:
                        godown = false;
                        break;
                    case LEFT:
                        goleft = false;
                        break;
                    case RIGHT:
                        goright = false;
                        break;
                    case SPACE:
                        space = false;
                        break;
                }
            }
        });
    }

    public int updateInterface(){

        int dx = 0, dy = 0;

        if (goright) dx += 5;
        if (goleft) dx -= 5;
        if (space) moverocket();

        theinterface.move(theinterface.spaceshipgroup, dx, dy);



        return 1;
    }

    private Group showinvader(int type, double x, double y){

        ImageView imginvader;

        if(type == 1) {
            Image img1 = new Image("sample/stuff/invader_size.png");
            imginvader =  new ImageView(img1);
        }
        else {
            Image img2 = new Image("sample/stuff/invader4_size.png");
            imginvader =  new ImageView(img2);
        }

        Group invadersgroup = new Group(imginvader);
        if (place(invadersgroup,x,y) != 0){
            return null;
        }
        return invadersgroup;
    }

    public void loadlevel(int level){
        invaders = new ArrayList<Invader>();
        Group g;

        for (double i = 0; i < W; i += 100) {
            Invader invader1 = new Invader(1,i,50);
            g = showinvader(1,invader1.getx(),invader1.gety());
            if(g == null) continue;
            root.getChildren().add(g);
            invaders.add(invader1);
        }
    }

    public Group showspaceship(){

        Image imgsship = new Image("sample/stuff/spaceship_size.png");
        viewspaceship =  new ImageView(imgsship);
        spaceshipgroup = new Group(viewspaceship);

        return spaceshipgroup;
    }

    public void move(Node g, int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        double x = g.getLayoutX() + dx;
        double y = g.getLayoutY() + dy;

        place(g, x, y);
    }

    public int place(Node g, double x, double y) {

        final double cx = g.getBoundsInLocal().getWidth() ;
        final double cy = g.getBoundsInLocal().getHeight();

        if (x >= 0 &&
                x + cx <= W &&
                y >= 0 &&
                y + cy <= H) {
            g.relocate(x , y);
        }
        else {
            System.out.println("Object out of bounds");
            System.out.println("cx=" + cx + " ;cy=" + cy );
            return -1;
        }
        return 0;
    }

    public Group rocket(){

        Image imgrocket = new Image("sample/stuff/rocket_size.png");
        ImageView viewrocket =  new ImageView(imgrocket);
        Group rocketgroup = new Group(viewrocket);

        return rocketgroup;
    }

    public void moverocket() {
        Group g = rocket();
        root.getChildren().add(g);
        place(g,spaceshipgroup.getLayoutX() + (spaceshipgroup.getBoundsInLocal().getWidth()/2) - 2, spaceshipgroup.getLayoutY() - g.getBoundsInLocal().getHeight());
    }
}