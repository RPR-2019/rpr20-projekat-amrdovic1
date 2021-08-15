package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.List;

public class LatestMoviesController
{
    public ListView moviesList;

    private PreparedStatement prepareMovies;
    private List<Film> movies;
    private SimpleObjectProperty<Film> movie;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url, "username", "password");
        prepareMovies = conn.prepareStatement("SELECT * FROM Film"); //This is not the correct statement (now it's only used for testing purposes)
        ResultSet pmrs = prepareMovies.executeQuery();
        while (pmrs.next())
        {
            //Fetch films and add them to list
            Film f = new Film();
            movies.add(f);
        }
        //Bind movies to ListView

    }
}
