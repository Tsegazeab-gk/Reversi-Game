package services.dao;

import models.Score;
import services.dao.IScore;
import services.db.IDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao implements IScore {

    Connection connection;
    Statement statement;
    IDatabase database;

    public ScoreDao(IDatabase db) {
        database = db;
        initializeDb();
    }

    @Override
    public boolean saveWinner(String playerName, int moves, int score) {
        int isSaved = 0;
        if (connect()) {
            try {
                if (getByName(playerName) == null) {
                    String sql = "insert into score(name,number_of_move,score)  VALUES ('" + playerName + "', " + moves +
                            ", " + score + ");";
                    statement = connection.createStatement();
                    isSaved = statement.executeUpdate(sql);

                } else {
                    String sql = "update score  set number_of_move= " + moves +
                            ", score=" + score + " where name='" + playerName + "'";
                    statement = connection.createStatement();
                    isSaved = statement.executeUpdate(sql);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (isSaved > 0) return true;
        return false;
    }

    @Override
    public List<Score> getByBestScore() {
        List<Score> scoreList = new ArrayList<>();
        ResultSet res;
        if (connect()) {
            String sql = "select * from score s  order By s.score desc limit 5;";
            try {
                statement = connection.createStatement();
                res = statement.executeQuery(sql);
                while (res.next()) {
                    Score score = new Score(res.getInt("id"), res.getString("name"),
                            res.getInt("number_of_move"), res.getInt("score"));
                    scoreList.add(score);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return scoreList;
    }

    @Override
    public List<Score> getByShortMoves() {
        List<Score> scoreList = new ArrayList<>();
        ResultSet res;
        if (connect()) {
            String sql = "select * from score s  order By s.number_of_move asc limit 5;";
            try {
                statement = connection.createStatement();
                res = statement.executeQuery(sql);
                while (res.next()) {
                    Score score = new Score(res.getInt("id"), res.getString("name"),
                            res.getInt("number_of_move"), res.getInt("score"));
                    scoreList.add(score);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
        return scoreList;
    }

    @Override
    public void initializeDb() {
        if (connect()) {
            String sql = "create table if not exists score ( id identity PRIMARY KEY auto_increment," +
                    "name varchar(30), number_of_move int , score int); ";
            try {
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public boolean connect() {
        connection = database.getConnection();
        if (connection != null) {
            return true;
        }
        return false;
    }

    @Override
    public Score getByName(String name) {

        Score score = null;
        ResultSet res;
        if (connect()) {
            String sql = "select * from score where name='" + name + "';";
            try {
                statement = connection.createStatement();
                res = statement.executeQuery(sql);
                while (res.next()) {
                    score = new Score(res.getInt("id"), res.getString("name"),
                            res.getInt("number_of_move"), res.getInt("score"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return score;
    }
}
