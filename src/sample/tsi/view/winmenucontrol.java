package sample.tsi.view;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class winmenucontrol {
    public void handleSubmitButt(ActionEvent actionEvent) {

    }

    public void handleNextLevel(ActionEvent actionEvent) {
    }

    public void handleMainMenu(ActionEvent actionEvent) {
        try {
            Main.showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
