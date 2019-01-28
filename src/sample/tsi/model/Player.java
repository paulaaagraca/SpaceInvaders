package sample.tsi.model;

public class Player {
    public String name;
    public String highscoreVector;

    public Player(String text) {
        this.name = text;
    }

    public String getPlayerName(){
        return name;
    }
}
