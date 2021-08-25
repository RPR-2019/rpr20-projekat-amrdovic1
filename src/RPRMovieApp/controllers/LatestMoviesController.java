package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.List;

public class LatestMoviesController
{
    public ChoiceBox selectDay;
    private PreparedStatement prepareMovies;
    private PreparedStatement getDays;
    private ObservableList<Film> movies = FXCollections.observableArrayList();
    public ListView<Film> moviesList;
    private ObservableList<String> days = FXCollections.observableArrayList();

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
            Film f = new Film(pmrs.getInt(1), pmrs.getString(2), pmrs.getInt(3), pmrs.getInt(5), pmrs.getInt(6), pmrs.getInt(4));
            movies.add(f);
        }
        getDays = conn.prepareStatement("SELECT name FROM day");
        ResultSet gdrs = getDays.executeQuery();
        while (gdrs.next())
        {
            days.add(gdrs.getString(1));
        }
        selectDay.setItems(days);
        selectDay.setValue("Monday");
        moviesList.setItems(movies);
        moviesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {

            }
        });
    }
}
