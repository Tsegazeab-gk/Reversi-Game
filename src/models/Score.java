package models;

public class Score {

   int id;
   String playerName;
   int numberOfMove;
   int score;

    public Score(String playerName, int numberOfMove, int score) {
        this.playerName = playerName;
        this.numberOfMove = numberOfMove;
        this.score = score;
    }

    public Score(int id, String playerName, int numberOfMove, int score) {
        this.id = id;
        this.playerName = playerName;
        this.numberOfMove = numberOfMove;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getNumberOfMove() {
        return numberOfMove;
    }

    public int getScore() {
        return score;
    }
}
