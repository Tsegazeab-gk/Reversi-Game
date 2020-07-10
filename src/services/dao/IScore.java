package services.dao;

import models.Score;

import java.util.List;

public interface IScore {

    boolean saveWinner(String playerName,int moves,int score);

    List<Score> getByBestScore();
    List<Score> getByShortMoves();

    void initializeDb();

    boolean connect();

    Score getByName(String name);


}
