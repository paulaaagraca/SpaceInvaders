package sample.tsi.model;

public class Player {
    public String name;
    public String highscore;

    public Player(String text) {
        this.name = text;
    }

    public String getPlayerName(){
        return name;
    }

    public String getHighscore() {
        return highscore;
    }
    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }
}
