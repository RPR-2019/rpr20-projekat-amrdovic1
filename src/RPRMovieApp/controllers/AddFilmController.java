package RPRMovieApp.controllers;

import RPRMovieApp.models.Genre;
import RPRMovieApp.models.Language;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddFilmController
{
    public ChoiceBox selectDirector;
    public TextField addNewDirector;
    public TextField moviename;
    public Button addGenre;
    public Spinner durationSpinner;
    public ChoiceBox selectDay;
    public ChoiceBox selectYear;
    public ChoiceBox selectMonth;
    public Button cancel;
    public Button addFilm;
    public ChoiceBox selectGenre;
    public ChoiceBox selectLanguage;
    public Button addLanguage;

    private ObservableList<Genre> remainingGenres;
    private ObservableList<Language> remainingLanguages;

    private int calculateGenres = 0;
    private int calculateLanguages = 0;

    private PreparedStatement getDirectors;
    private PreparedStatement addNewFilm;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url, "username", "password");
        getDirectors = conn.prepareStatement("SELECT * FROM director");
        addNewFilm = conn.prepareStatement("INSERT INTO film VALUES(?,?,?,?,?,?)");
        remainingGenres = FXCollections.observableArrayList(Genre.values());
        selectGenre.setItems(remainingGenres);
        remainingLanguages = FXCollections.observableArrayList(Language.values());
        selectLanguage.setItems(remainingLanguages);
    }

    public void addGenreClick(ActionEvent actionEvent)
    {

    }

    public void cancelClick(ActionEvent actionEvent)
    {
    }

    public void addFilmClick(ActionEvent actionEvent)
    {
    }

    public void addLanguageClick(ActionEvent actionEvent)
    {
    }
}
