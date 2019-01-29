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
import sample.tsi.model.Rocket;

import java.util.ArrayList;
import java.util.List;

public class Interface {
    public static List<Invader> invaders;
    public static List<Rocket> rockets = new ArrayList<Rocket>();
    public static ImageView viewspaceship;
    public static Group spaceshipgroup;
    public Group root;
    public Stage stage;
    int W = 512;
    int H = 384;
    private boolean cannon_blocked = false;

    boolean goup = false,
            goright = false,
            goleft = false,
            godown = false,
            pause = false,
            space = false;

    public Interface(Stage stage) {
        this.stage = stage;

        root = new Group();
        Scene setscene = new Scene(root, W, H, Color.MIDNIGHTBLUE);
        stage.setScene(setscene);

        Canvas canvas = new Canvas(W, H);
        root.getChildren().add(canvas);
        root.getChildren().add(showspaceship());

        place(spaceshipgroup,W/2 - 30, H - 30);

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
                        cannon_blocked = false;
                        break;
                }
            }
        });
    }

    public int updateInterface() {

        int dx = 0, dy = 0;

        if (goright) dx += 5;
        if (goleft) dx -= 5;
        if (space && !cannon_blocked) {
            cannon_blocked = true;
            placerocket();
        }

        move(spaceshipgroup, dx, dy);
        moverockets();

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

    public List<Invader> loadlevel(int level){
        invaders = new ArrayList<Invader>();
        Group g;

        for (double i = 0; i < W; i += 100) {
            Invader invader1 = new Invader(1,i,50);
            g = showinvader(1,invader1.getx(),invader1.gety());
            if(g == null) continue;
            root.getChildren().add(g);
            invaders.add(invader1);
        }
        return invaders;
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

    public Group showrocket(){

        Image imgrocket = new Image("sample/stuff/rocket_size.png");
        ImageView viewrocket =  new ImageView(imgrocket);
        Group rocketgroup = new Group(viewrocket);

        return rocketgroup;
    }

    public void placerocket() {
        Group g = showrocket();
        Rocket rocket = new Rocket(spaceshipgroup.getLayoutX() + (spaceshipgroup.getBoundsInLocal().getWidth()/2) - 2, spaceshipgroup.getLayoutY() - g.getBoundsInLocal().getHeight());
        place(g,rocket.getx(),rocket.gety());
        root.getChildren().add(g);
        rockets.add(rocket);
    }

    public void moverockets(){
        for(int i=0; i<rockets.size(); i++){
            Rocket r = rockets.get(i);
            for(int j=0; j<root.getChildren().size(); j++){
                Node n = root.getChildren().get(j);
                if(n.getLayoutX() == r.getx() && n.getLayoutY() == r.gety()){
                    if (r.gety() == 0) {
                        root.getChildren().remove(j);
                        rockets.remove(i);
                    }
                    else {
                        r.sety(r.gety() - 5);
                        place(n, r.getx(), r.gety());
                        rockets.set(i, r);
                    }
                }
            }
        }
    }

}