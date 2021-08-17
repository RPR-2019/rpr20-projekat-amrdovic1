package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.List;

public class LatestMoviesController
{
    private PreparedStatement prepareMovies;
    private ObservableList<Film> movies = FXCollections.observableArrayList();
    public ListView<Film> moviesList;

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
            Film f = new Film(pmrs.getString(2), pmrs.getInt(3));
            movies.add(f);
        }
        moviesList.setItems(movies);
    }
}
