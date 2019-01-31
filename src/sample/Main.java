package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Pair;
import sample.tsi.controller.GameManager;

import sample.tsi.controller.Interface;
import sample.tsi.model.HighScoreFile;
import sample.tsi.model.Player;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import sample.tsi.view.highscoremenucontrol;
import sample.tsi.view.levelmenucontrol;
import sample.tsi.view.mainmenucontrol;
import sample.tsi.view.winmenucontrol;


public class Main extends Application {

    public static Player player;
    public static Stage stage;
    public static Label timeshow;
    public static HighScoreFile highScoreFile;
    public static GameManager game;


    @FXML
    @Override
    public void start(Stage stage) throws Exception {
        highScoreFile = new HighScoreFile();
        highScoreFile.generateFile();
        URL url = new File("src/sample/resources/playermenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        //window title
        stage.setTitle("Space Invaders");
        //window specs
        stage.setScene(new Scene(root, 512, 384));
        this.stage = stage;
        //show window
        stage.show();
    }


    public static void showMainMenu() throws IOException {

        URL url = new File("src/sample/resources/mainmenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/mainmenu.fxml"));
        mainmenucontrol controller = loader.getController();

        stage.show();
    }

    public static void showLevelMenu() throws IOException {

        URL url = new File("src/sample/resources/levelmenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/levelmenu.fxml"));
        levelmenucontrol controller = loader.getController();

        stage.show();
    }

    public static void showGameMenu(int level) throws IOException {

        Interface gameinterface = new Interface(stage);
        gameinterface.updateInterface();
        game = new GameManager(player, stage, gameinterface);
        System.out.println("entered showgamemenu");
        game.paused = false;
        game.setLevel(level);
        game.loop();
    }

    public static void showWinMenu(Player player) throws IOException {

        System.out.println("highscore in menu:" + player.getHighscore());
        //URL url = new File("src/sample/resources/winmenu.fxml").toURL();
        //Parent root = FXMLLoader.load(url);
        //stage.setScene(new Scene(root, 512, 384));

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/winmenu.fxml"));
        loader.setLocation(new File("src/sample/resources/winmenu.fxml").toURL());
        Parent root = loader.load();
        stage.setScene(new Scene(root, 512, 384));
        winmenucontrol controller = loader.getController();
        controller.setplayer(player);
        if (controller == null)
            System.out.println("controller null");
        controller.showTime(player.getHighscore());
        stage.show();
    }

    private static ObservableList<String> item = FXCollections.<String>observableArrayList();
    private static ListView<String> items = new ListView<String>(item);


    public static void showHScoreMenu() throws IOException {
        items.setPrefSize(120,100);
        items.setOrientation(Orientation.VERTICAL);

        String line;
        URL url = new File("src/sample/resources/highscoremenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/highscoremenu.fxml"));
        highscoremenucontrol controller = loader.getController();

        System.out.println("COULD NOT GET HIGH SCORES! ---> CHECK THE FILE <---");

        ArrayList<Pair<Player, String>> lisths = highScoreFile.getHighscores();
        for (int i = 0; i < lisths.size(); i++) {
            items.getItems().add(lisths.get(i).getValue() + lisths.get(i).getKey().getPlayerName());
        }
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
