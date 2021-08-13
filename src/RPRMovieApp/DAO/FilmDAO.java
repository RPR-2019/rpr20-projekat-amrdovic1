package RPRMovieApp.DAO;

import RPRMovieApp.models.Film;

import java.sql.*;

public class FilmDAO
{
    private static FilmDAO instance;
    private Connection fdconn;
    private PreparedStatement findfilmquery, addfilmquery, newidquery, modifyfilmquery, removefilmquery;

    private FilmDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url);
        newidquery = fdconn.prepareStatement("SELECT MAX(id)+1 FROM film");
        addfilmquery = fdconn.prepareStatement("INSERT INTO film VALUES(?,?,?)");
        findfilmquery = fdconn.prepareStatement("SELECT * FROM film WHERE name=?");
        modifyfilmquery = fdconn.prepareStatement("UPDATE film SET name=?, duration=? WHERE id=?");
        removefilmquery = fdconn.prepareStatement("DELETE FROM film WHERE id=?");
    }

    public static FilmDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null)
        {
            instance = new FilmDAO();
        }
        return instance;
    }

    public Film findFilm (Film f) throws SQLException {
        try {
            findfilmquery.setString(1, f.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        findfilmquery.execute();
        return f;
    }

    public Film addFilm (Film f)
    {
        try {
            ResultSet rs = newidquery.executeQuery();
            if(rs.next())
            {
                f.setId(rs.getInt(1));
            }
            else
            {
                f.setId(1);
            }
            addfilmquery.setInt(1, f.getId());
            addfilmquery.setString(2, f.getName());
            addfilmquery.setInt(3, f.getDuration());
            addfilmquery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return f;
    }

    public Film modifyfilm (Film f)
    {
        try {
            modifyfilmquery.setString(1, f.getName());
            modifyfilmquery.setInt(2, f.getDuration());
            modifyfilmquery.setInt(3, f.getId());
            modifyfilmquery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return f;
    }

    public void removeFilm (Film f)
    {
        try {
            removefilmquery.setInt(1, f.getId());
            removefilmquery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
