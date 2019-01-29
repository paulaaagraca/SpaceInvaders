package sample.tsi.controller;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.tsi.model.Entity;
import sample.tsi.model.Invader;
import sample.tsi.model.MenuBluePrint;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Interface {
    public Entity rockets;
    public Entity spaceship;
    public List<Node> invaders;
    int invadercount;


    int updateInterface(){



        return 1;
    }

   /* public static Group invaders1(){
        List<Node> invaders;
        invaders = new ArrayList<>();
        Image img1 = new Image("sample/stuff/invader1b.png");
        Node invader1 = new ImageView(img1);

        for (double i = 50; i < 500; i += 50) {
            //Invader invader12 = new Invader(1, i, 50);
            ImageView invaderview = new ImageView(img1);
            //invaderview.setX(i * 2);
            invaders.add(invaderview);
        }

        Group invadersgroup = new Group(invaders);
        return invadersgroup;
    }*/


    public static Group showinvader(){

        Image img1 = new Image("sample/stuff/invader1b.png");
        ImageView imginvader =  new ImageView(img1);
        imginvader.setX(50);
        Group invadersgroup = new Group(imginvader);

        return invadersgroup;
    }

    public static ImageView viewspaceship(){
        Image imgsship = new Image("sample/stuff/spaceshipb.png");
        ImageView viewspaceship =  new ImageView(imgsship);

        return viewspaceship;
    }

    public static Group showspaceship(){

        Group spaceshipgroup = new Group(viewspaceship());

        return spaceshipgroup;
    }

    public void movespaceship(ImageView viewspaceship, double x, double y){

        viewspaceship.setX(x);
        viewspaceship.setY(y);

    }




}
