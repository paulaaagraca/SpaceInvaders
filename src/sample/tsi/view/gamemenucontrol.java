package sample.tsi.view;

import javafx.event.ActionEvent;
import sample.Main;

import java.io.IOException;

public class gamemenucontrol {
    public void handleGameMenu(ActionEvent actionEvent) {
        try {
            Main.showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
