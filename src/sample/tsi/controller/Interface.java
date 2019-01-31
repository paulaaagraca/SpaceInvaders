package sample.tsi.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;
import sample.tsi.model.Invader;
import sample.tsi.model.Player;
import sample.tsi.model.Rocket;
import sample.tsi.model.Sound;
import sample.tsi.view.winmenucontrol;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Interface {
    public List<Invader> invaders;
    public List<Rocket> rockets = new ArrayList<Rocket>();
    public static ImageView viewspaceship;
    public static Group spaceshipgroup;
    public Group root;
    public Stage stage;
    int W = 512;
    int H = 384;
    private boolean cannon_blocked = false;
    public Label playername;
    public Node nodetime = new Group();
    public Label timelabel = new Label("00:00", nodetime);

    boolean goup = false,
            goright = false,
            goleft = false,
            godown = false,
            pause = false,
            space = false;

    public Interface(Stage stage) throws IOException {
        this.stage = stage;

        root = new Group();

        URL url = new File("src/sample/resources/gamemenu.fxml").toURL();
        Parent designroot = FXMLLoader.load(url);

        Scene setscene = new Scene(root, W, H, Color.MIDNIGHTBLUE);
        stage.setScene(setscene);

        Canvas canvas = new Canvas(W, H);
        root.getChildren().add(designroot.getChildrenUnmodifiable().get(0));

        root.getChildren().add(canvas);
        root.getChildren().add(showspaceship());
        timelabel.setLayoutX(250);
        timelabel.setTextFill(Color.rgb(105, 105, 105));
        root.getChildren().add(timelabel);


        place(spaceshipgroup, W / 2 - 30, H - 30);

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
                        pause = !pause;
                        break;
                }
            }
        });
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

    public void setTime(int time) {

        int auxtime = time / 60;
        timelabel.setText(auxtime + ":" + time % 60);
    }

    private Group showinvader(int type, double x, double y) {

        ImageView imginvader;

        if (type == 1) {
            Image img1 = new Image("sample/stuff/invader1.png");
            imginvader = new ImageView(img1);
        } else if (type == 2) {
            Image img2 = new Image("sample/stuff/invader2.png");
            imginvader = new ImageView(img2);
        } else if (type == 3) {
            System.out.println("entered type 3 invader");
            Image img3 = new Image("sample/stuff/invader3.png");
            imginvader = new ImageView(img3);
        } else {
            Image img1 = new Image("sample/stuff/invader1.png");
            imginvader = new ImageView(img1);
            //add image 3
        }

        Group invadersgroup = new Group(imginvader);
        if (place(invadersgroup, x, y) != 0) {
            return null;
        }
        return invadersgroup;
    }

    public List<Invader> loadlevel(int level, Player player, Sound sound) {

        sound.playsound();
        invaders = new ArrayList<Invader>();
        Node labelpname = new Group();
        playername = new Label(player.getPlayerName(), labelpname);
        playername.setTextFill(Color.rgb(105, 105, 105));
        root.getChildren().add(playername);

        if (level == 1) {
            for (double i = 40; i < W; i += 100) {
                Group g;
                Invader invader1 = new Invader(1, i, 50, 0, 0);
                g = showinvader(1, invader1.getx(), invader1.gety());
                if (g == null) continue;
                invader1.setw(g.getBoundsInLocal().getWidth());
                invader1.seth(g.getBoundsInLocal().getHeight());
                root.getChildren().add(g);
                invaders.add(invader1);
            }
        }
        if (level == 2) {
            for (double i = 15, j = 50; i < W; i += 73, j += 74) {
                Group g1, g2;
                Invader invader1 = new Invader(1, i, 100, 0, 0);
                Invader invader2 = new Invader(2, j, 50, 0, 0);
                g1 = showinvader(1, invader1.getx(), invader1.gety());
                if (g1 == null) continue;
                invader1.setw(g1.getBoundsInLocal().getWidth());
                invader1.seth(g1.getBoundsInLocal().getHeight());
                root.getChildren().add(g1);
                invaders.add(invader1);
                g2 = showinvader(2, invader2.getx(), invader2.gety());
                if (g2 == null) continue;
                invader2.setw(g2.getBoundsInLocal().getWidth());
                invader2.seth(g2.getBoundsInLocal().getHeight());
                root.getChildren().add(g2);
                invaders.add(invader2);
            }
        }
        if (level == 3) {
            System.out.println("entered load level 3");
            for (double i = 30, j = 30; i < W; i += 60, j += 60) {
                Group g1, g2;
                Invader invader1 = new Invader(1, i, 150, 0, 0);
                Invader invader2 = new Invader(2, j, 100, 0, 0);
                g1 = showinvader(1, invader1.getx(), invader1.gety());
                if (g1 == null) continue;
                invader1.setw(g1.getBoundsInLocal().getWidth());
                invader1.seth(g1.getBoundsInLocal().getHeight());
                root.getChildren().add(g1);
                invaders.add(invader1);
                g2 = showinvader(2, invader2.getx(), invader2.gety());
                if (g2 == null) continue;
                invader2.setw(g2.getBoundsInLocal().getWidth());
                invader2.seth(g2.getBoundsInLocal().getHeight());
                root.getChildren().add(g2);
                invaders.add(invader2);
            }
            for (double k = 20; k < W; k += 40) {
                Group g3;
                Invader invader3 = new Invader(3, k, 50, 0, 0);
                g3 = showinvader(3, invader3.getx(), invader3.gety());
                if (g3 == null) continue;
                invader3.setw(g3.getBoundsInLocal().getWidth());
                invader3.seth(g3.getBoundsInLocal().getHeight());
                root.getChildren().add(g3);
                invaders.add(invader3);
            }
        } else if (level == 4 || level == 5) {
            for (double i = 25, j = 50; j < W; i += 60, j += 54) {
                Group g2, g3, g22, g33;
                Invader invader2 = new Invader(2, i, 100, 0, 0);
                Invader invader3 = new Invader(3, j, 50, 0, 0);
                Invader invader22 = new Invader(2, i, 140, 0, 0);
                Invader invader33 = new Invader(3, j, 180, 0, 0);
                g2 = showinvader(2, invader2.getx(), invader2.gety());
                if (g2 == null) continue;
                invader2.setw(g2.getBoundsInLocal().getWidth());
                invader2.seth(g2.getBoundsInLocal().getHeight());
                root.getChildren().add(g2);
                invaders.add(invader2);
                g22 = showinvader(2, invader22.getx(), invader22.gety());
                if (g22 == null) continue;
                invader22.setw(g22.getBoundsInLocal().getWidth());
                invader22.seth(g22.getBoundsInLocal().getHeight());
                root.getChildren().add(g22);
                invaders.add(invader22);
                g3 = showinvader(3, invader3.getx(), invader3.gety());
                if (g3 == null) continue;
                invader3.setw(g3.getBoundsInLocal().getWidth());
                invader3.seth(g3.getBoundsInLocal().getHeight());
                root.getChildren().add(g3);
                invaders.add(invader3);
                g33 = showinvader(3, invader33.getx(), invader33.gety());
                if (g33 == null) continue;
                invader33.setw(g33.getBoundsInLocal().getWidth());
                invader33.seth(g33.getBoundsInLocal().getHeight());
                root.getChildren().add(g33);
                invaders.add(invader33);
            }
            if (level == 5) {
                for (double i = 40; i < W; i += 100) {
                    Group g1, g11;
                    Invader invader1 = new Invader(1, i, 20, 0, 0);
                    g1 = showinvader(1, invader1.getx(), invader1.gety());
                    if (g1 == null) continue;
                    invader1.setw(g1.getBoundsInLocal().getWidth());
                    invader1.seth(g1.getBoundsInLocal().getHeight());
                    root.getChildren().add(g1);
                    invaders.add(invader1);
                    Invader invader11 = new Invader(1, i, 225, 0, 0);
                    g11 = showinvader(1, invader11.getx(), invader11.gety());
                    if (g11 == null) continue;
                    invader1.setw(g11.getBoundsInLocal().getWidth());
                    invader1.seth(g11.getBoundsInLocal().getHeight());
                    root.getChildren().add(g11);
                    invaders.add(invader11);
                }
            }
        }
        return invaders;
    }

    public Group showspaceship() {

        Image imgsship = new Image("sample/stuff/spaceship_size.png");
        viewspaceship = new ImageView(imgsship);
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

        final double cx = g.getBoundsInLocal().getWidth();
        final double cy = g.getBoundsInLocal().getHeight();

        if (x >= 0 &&
                x + cx <= W &&
                y >= 0 &&
                y + cy <= H) {
            g.relocate(x, y);
        } else {
            System.out.println("Object out of bounds");
            System.out.println("cx=" + cx + " ;cy=" + cy);
            return -1;
        }
        return 0;
    }

    public Group showrocket() {

        Image imgrocket = new Image("sample/stuff/rocket_size.png");
        ImageView viewrocket = new ImageView(imgrocket);
        Group rocketgroup = new Group(viewrocket);

        return rocketgroup;
    }

    public void placerocket() {
        Group g = showrocket();
        Rocket rocket = new Rocket(spaceshipgroup.getLayoutX() + (spaceshipgroup.getBoundsInLocal().getWidth() / 2) - 2, spaceshipgroup.getLayoutY() - g.getBoundsInLocal().getHeight());
        place(g, rocket.getx(), rocket.gety());
        root.getChildren().add(g);
        rockets.add(rocket);
    }

    public void moverockets() {
        for (int i = 0; i < rockets.size(); i++) {
            Rocket r = rockets.get(i);
            for (int j = 0; j < root.getChildren().size(); j++) {
                Node n = root.getChildren().get(j);
                if (n.getLayoutX() == r.getx() && n.getLayoutY() == r.gety()) {
                    if (r.gety() <= 0) {
                        System.out.println("remove rocket hits up");
                        root.getChildren().remove(j);
                        rockets.remove(i);
                    } else {
                        r.sety(r.gety() - 5);
                        place(n, r.getx(), r.gety());
                        rockets.set(i, r);
                    }
                }
            }
        }
    }

    public void win(Player p) {
        try {
            Main.showWinMenu(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}