package sample.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActorDAO
{
    private static ActorDAO instance;
    private Connection fdconn;
    private PreparedStatement fdps;

    private ActorDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "/.RPRProjectapp/RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url);
    }

    public static ActorDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new ActorDAO();
        }
        return instance;
    }
}
