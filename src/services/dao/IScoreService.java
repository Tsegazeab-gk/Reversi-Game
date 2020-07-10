package services.dao;

import models.Score;

import java.util.List;

public interface IScoreService {

    boolean saveWinner(String playerName,int moves,int score);
    List<Score> getByBestScore();
    List<Score> getByShortMoves();

}
