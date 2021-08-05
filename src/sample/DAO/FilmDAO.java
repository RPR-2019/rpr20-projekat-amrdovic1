package sample.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmDAO
{
    private static FilmDAO instance;
    private Connection fdconn;
    private PreparedStatement fdps;

    private FilmDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url);
    }

    public static FilmDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new FilmDAO();
        }
        return instance;
    }
}
