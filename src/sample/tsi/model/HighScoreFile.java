package sample.tsi.model;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;


public class HighScoreFile {

    public void generateFile() {
        File highscorefile = new File("src/sample/resources/highscore.txt");
        try {
            highscorefile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHighScore(Player player) {
        try (FileWriter fw = new FileWriter("src/sample/resources/highscore.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
             pw.println(player.getHighscore() + "\t" + player.getPlayerName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pair<Player, String>> getHighscores() {
        ArrayList<Pair<Player, String>> highscorefile = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/sample/resources/highscore.txt"))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] block = data.split("\t");
                highscorefile.add(new Pair(new Player(block[0]), new String(block[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highscorefile;
    }
}
