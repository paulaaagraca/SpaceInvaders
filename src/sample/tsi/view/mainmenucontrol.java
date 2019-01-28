package sample.tsi.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import sample.Main;


import java.io.IOException;

public class mainmenucontrol {

    public void handleStart(ActionEvent actionEvent) throws IOException {
        Main.showLevelMenu();
    }

    public void handleHighScores(ActionEvent actionEvent) throws IOException {
        Main.showHScoreMenu();
    }

    public void handleQuit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
