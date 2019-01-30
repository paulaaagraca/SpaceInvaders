package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import sample.tsi.controller.GameManager;

import sample.tsi.controller.Interface;
import sample.tsi.model.Player;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import sample.tsi.model.Sound;
import sample.tsi.view.gamemenucontrol;
import sample.tsi.view.highscoremenucontrol;
import sample.tsi.view.mainmenucontrol;
import sample.tsi.view.winmenucontrol;


public class Main extends Application {

    public static Player player;
    public static Stage stage;


    @FXML
    @Override
    public void start(Stage stage) throws Exception{
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

    public static void showLevelMenu() throws IOException{

        URL url = new File("src/sample/resources/levelmenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/levelmenu.fxml"));
        mainmenucontrol controller = loader.getController();

        stage.show();
    }

    public static void showGameMenu(int level) throws IOException{

        Interface gameinterface = new Interface(stage);
        gameinterface.updateInterface();
        GameManager game = new GameManager(player, stage, gameinterface);
        game.setLevel(level);
        game.loop();
    }

    public static void showWinMenu() throws IOException {

        URL url = new File("src/sample/resources/winmenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/winmenu.fxml"));
        winmenucontrol controller = loader.getController();
        stage.show();
    }

    public static void showHScoreMenu() throws IOException {

        URL url = new File("src/sample/resources/highscoremenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 512, 384));
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("src/sample/resources/highscoremenu.fxml"));
        highscoremenucontrol controller = loader.getController();

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
