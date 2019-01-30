package sample.tsi.view;

import javafx.event.ActionEvent;
import sample.Main;
import sample.tsi.controller.GameManager;
import sample.tsi.controller.Interface;
import sample.tsi.model.Player;

import java.io.IOException;

public class levelmenucontrol {

    public void handleLevel1(ActionEvent actionEvent) {

        try {
            Main.showGameMenu(1);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleLevel2(ActionEvent actionEvent) {
        try {
            Main.showGameMenu(2);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleLevel3(ActionEvent actionEvent) {
        try {
            Main.showGameMenu(3);
            System.out.println("entered handle level 3");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleLevel4(ActionEvent actionEvent) {
        try {
            Main.showGameMenu(4);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void handleLevel5(ActionEvent actionEvent) {
        try {
            Main.showGameMenu(5);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}