package sample.tsi.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sample.Main;
import sample.tsi.model.HighScoreFile;
import sample.tsi.model.Player;

import java.io.IOException;


public class winmenucontrol {

    @FXML
    private Label textfortime;
    public String score;
    public Player player;

    @FXML
    public void showTime(String score){

       textfortime.setText(score);
       textfortime.setTextFill(Color.rgb(105, 105, 105));
       textfortime.setLayoutX(230);
       textfortime.setLayoutY(140);
    }

    public void setplayer(Player player){
        this.player=player;
    }

    public void handleSubmitButt(ActionEvent actionEvent) throws IOException{
        HighScoreFile hsfile = new HighScoreFile();
        hsfile.addHighScore(player);
    }

    public void handleNextLevel(ActionEvent actionEvent) {
        try {
            Main.showLevelMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMainMenu(ActionEvent actionEvent) {
        try {
            Main.showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
