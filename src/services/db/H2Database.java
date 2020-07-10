package services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database implements IDatabase {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:~/reversi;MV_STORE=false";
    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    private static IDatabase dbInstance;

    public H2Database() {
    }

    //singleton instance
    public static IDatabase getDbInstance() {

        if (dbInstance == null) {
            dbInstance = new H2Database();
        }
        return dbInstance;
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);

            //Open a connection
            //  System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
/*
    public static void main(String[] args) {
IDatabase db=new H2Database();
        System.out.println(db.getConnection());
    }

 */
}
