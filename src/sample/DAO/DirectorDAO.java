package sample.DAO;

import sample.models.Director;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DirectorDAO
{
    private static DirectorDAO instance;
    private Connection fdconn;
    private PreparedStatement fdps;

    private DirectorDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url);
    }

    public static DirectorDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new DirectorDAO();
        }
        return instance;
    }
}
