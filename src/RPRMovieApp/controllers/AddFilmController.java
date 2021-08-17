package RPRMovieApp.controllers;

import RPRMovieApp.models.Genre;
import RPRMovieApp.models.Language;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

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
    public ChoiceBox<Genre> selectGenre;
    public ChoiceBox<Language> selectLanguage;
    public Button addLanguage;
    public Label selectedGenres;
    public Label selectedLanguages;

    private ObservableList<Genre> remainingGenres;
    private ObservableList<Language> remainingLanguages;

    private ArrayList<String> sgenres = new ArrayList<>();
    private ArrayList<String> slanguages = new ArrayList<>();

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

    public int powerof2 (int n)
    {
        int res = 1;
        for (int i = 1; i <= n; i++)
        {
            res *= 2;
        }
        return res;
    }

    public void addGenreClick(ActionEvent actionEvent)
    {
        Genre g = Genre.valueOf(selectGenre.getSelectionModel().getSelectedItem().toString().toUpperCase()); //Solve sci-fi issue
        calculateGenres += (powerof2(g.ordinal()));
        remainingGenres.remove(g);
        sgenres.add(g.toString());
        Collections.sort(sgenres);
        selectedGenres.setText("");
        for (int i = 0; i < sgenres.size(); i++)
        {
            selectedGenres.setText(selectedGenres.getText() + sgenres.get(i));
            if (i < sgenres.size() - 1)
            {
                selectedGenres.setText(selectedGenres.getText() + "|");
            }
        }
        selectGenre.setValue(null);
    }

    public void cancelClick(ActionEvent actionEvent)
    {
    }

    public void addFilmClick(ActionEvent actionEvent)
    {
    }

    public void addLanguageClick(ActionEvent actionEvent)
    {
        Language l = Language.valueOf(selectLanguage.getSelectionModel().getSelectedItem().toString().toUpperCase());
        calculateLanguages += (powerof2(l.ordinal()));
        remainingLanguages.remove(l);
        slanguages.add(l.toString());
        Collections.sort(slanguages);
        selectedLanguages.setText("");
        for (int i = 0; i < slanguages.size(); i++)
        {
            selectedLanguages.setText(selectedLanguages.getText() + slanguages.get(i));
            if (i < slanguages.size() - 1)
            {
                selectedLanguages.setText(selectedLanguages.getText() + "|");
            }
        }
        selectLanguage.setValue(null);
    }
}
