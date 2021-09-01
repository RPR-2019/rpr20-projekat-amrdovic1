package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LatestMoviesController
{
    public ChoiceBox selectDay;
    private PreparedStatement prepareMovies;
    private PreparedStatement getDays;
    private ObservableList<Film> movies = FXCollections.observableArrayList();
    public ListView<Film> moviesList;
    private ObservableList<String> days = FXCollections.observableArrayList();

    private Connection conn;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
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
        conn.close();
        selectDay.setItems(days);
        selectDay.setValue("Monday");
        moviesList.setItems(movies);
        moviesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                ChosenFilm.setChosen(moviesList.getSelectionModel().getSelectedItem());
                Stage latestFilmDetailStage = new Stage();
                FXMLLoader latestFilmDetailLoader = new FXMLLoader(getClass().getResource("/fxml/latestMovieDetail.fxml")); //This path is temporary
                Parent root = null;
                try {
                    root = latestFilmDetailLoader.load();
                    LatestMovieDetailController lmdc = latestFilmDetailLoader.getController();
                    latestFilmDetailStage.setTitle("Movie details - " + ChosenFilm.getChosen().getName());
                    latestFilmDetailStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    latestFilmDetailStage.setResizable(false);
                    latestFilmDetailStage.show();
                    lmdc.movieTitle.setText(ChosenFilm.getChosen().getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
