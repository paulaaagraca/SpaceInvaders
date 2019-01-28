package sample.tsi.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Main;
import sample.tsi.model.Player;

import java.io.IOException;


public class playermenucontrol {

    @FXML
    public TextField boxname;


    public void handleinsertname(ActionEvent actionEvent) throws IOException {

    }

    public void handlePlay(ActionEvent actionEvent) throws IOException {

        if(boxname.getText().length()>3 && boxname.getText().length()<8){
            Player p1 = new Player(boxname.getText());

            Main.player = p1;

            Main.showMainMenu();
        }
        else{
            System.out.println("ERROR:TRY AGAIN");
       //     error.setVisible(true);
        }
    }
}
