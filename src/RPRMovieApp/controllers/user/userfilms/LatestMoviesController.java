package RPRMovieApp.controllers.user.userfilms;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.Film;
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
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LatestMoviesController
{
    public ChoiceBox<String> selectDay;
    public ListView<Film> moviesList;
    private ObservableList<String> days = FXCollections.observableArrayList();

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
        ArrayList<Film> movies = cDAO.getAllFilms();
        moviesList.setItems(FXCollections.observableArrayList(movies));
        days = FXCollections.observableArrayList(cDAO.getDayNames());
        selectDay.setItems(days);
        selectDay.getSelectionModel().selectFirst();
        moviesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                CinemaDAO.removeInstance();
                CurrentData.setCurrentFilm(moviesList.getSelectionModel().getSelectedItem());
                Stage latestFilmDetailStage = new Stage();
                FXMLLoader latestFilmDetailLoader = new FXMLLoader(getClass().getResource("/fxml/latestMovieDetail.fxml")); //This path is temporary
                Parent root = null;
                try {
                    root = latestFilmDetailLoader.load();
                    latestFilmDetailStage.setTitle("Movie details - " + CurrentData.getCurrentFilm().getName());
                    latestFilmDetailStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    latestFilmDetailStage.setResizable(false);
                    latestFilmDetailStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
