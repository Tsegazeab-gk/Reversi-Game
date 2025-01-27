package services.dao;

import models.Score;
import services.db.H2Database;

import java.util.List;

public class ScoreService implements IScoreService {

    IScore iScore;

    public ScoreService() {
        iScore = new ScoreDao(H2Database.getDbInstance());
    }

    @Override
    public boolean saveWinner(String playerName, int moves, int score) {
        return iScore.saveWinner(playerName,moves,score);
    }

    @Override
    public List<Score> getByBestScore() {
        return iScore.getByBestScore();
    }

    @Override
    public List<Score> getByShortMoves() {
        return iScore.getByShortMoves();
    }
}