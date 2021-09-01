package RPRMovieApp.controllers;

import RPRMovieApp.models.Genre;
import RPRMovieApp.models.Language;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddFilmController
{
    public ChoiceBox selectDirector;
    public TextField addNewDirector;
    public TextField moviename;
    public Button addGenre;
    public Spinner<Integer> durationSpinner; //Issue when user manually types in value in spinner
    public ChoiceBox selectDay;
    public ChoiceBox selectYear;
    public ChoiceBox selectMonth;
    public Button cancel;
    public Button addFilm; //Return this as default button as soon as spinner issue is resolved
    public ChoiceBox<Genre> selectGenre;
    public ChoiceBox<Language> selectLanguage;
    public Button addLanguage;
    public Label selectedGenres;
    public Label selectedLanguages;
    public TextArea synopsisText;

    private ObservableList<Genre> remainingGenres;
    private ObservableList<Language> remainingLanguages;

    private ArrayList<String> sgenres = new ArrayList<>();
    private ArrayList<String> slanguages = new ArrayList<>();

    private ArrayList<String> directors = new ArrayList<>();

    private int calculateGenres = 0;
    private int calculateLanguages = 0;

    private Connection conn;

    private PreparedStatement getDirectors;
    private PreparedStatement addNewFilm;
    private PreparedStatement addDirector;
    private PreparedStatement getMaxDirectorID;
    private PreparedStatement getExistingDirectorID;
    private PreparedStatement getMaxFilmID;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        SpinnerValueFactory<Integer> dsvf = new SpinnerValueFactory<>() {
            @Override
            public void decrement(int i) {
                i--;
            }

            @Override
            public void increment(int i) {
                i++;
            }
        };
        durationSpinner.setValueFactory(dsvf);
        durationSpinner.getValueFactory().setValue(100);
        durationSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                durationSpinner.increment(0);
            }
        });
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        getDirectors = conn.prepareStatement("SELECT name FROM director");
        addNewFilm = conn.prepareStatement("INSERT INTO film VALUES(?,?,?,?,?,?,?)");
        addDirector = conn.prepareStatement("INSERT INTO director VALUES(?,?)");
        getMaxDirectorID = conn.prepareStatement("SELECT MAX(id) FROM director");
        getExistingDirectorID = conn.prepareStatement("SELECT id FROM director WHERE name=?");
        getMaxFilmID = conn.prepareStatement("SELECT MAX(id) FROM film");
        remainingGenres = FXCollections.observableArrayList(Genre.values());
        selectGenre.setItems(remainingGenres);
        remainingLanguages = FXCollections.observableArrayList(Language.values());
        selectLanguage.setItems(remainingLanguages);
        ResultSet rs = getDirectors.executeQuery();
        while (rs.next())
        {
            directors.add(rs.getString(1));
        }
        Collections.sort(directors);
        selectDirector.setItems(FXCollections.observableArrayList(directors));
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

    public void addFilmClick(ActionEvent actionEvent) throws SQLException, IOException {
        int directorid;
        if (selectDirector.getValue() == null)
        {
            ResultSet gmdid = getMaxDirectorID.executeQuery();
            directorid = gmdid.getInt(1) + 1;
            addDirector.setInt(1, directorid);
            addDirector.setString(2, addNewDirector.getText());
            addDirector.execute();
        }
        else
        {
            getExistingDirectorID.setString(1, selectDirector.getSelectionModel().getSelectedItem().toString());
            ResultSet gedid = getExistingDirectorID.executeQuery();
            directorid = gedid.getInt(1);
        }
        ResultSet gmfid = getMaxFilmID.executeQuery();
        addNewFilm.setInt(1, gmfid.getInt(1) + 1);
        addNewFilm.setString(2, moviename.getText());
        addNewFilm.setInt(3, durationSpinner.getValue());
        addNewFilm.setInt(4, directorid);
        addNewFilm.setInt(5, calculateGenres);
        addNewFilm.setInt(6, calculateLanguages);
        addNewFilm.setString(7, synopsisText.getText());
        addNewFilm.execute();
        conn.close();
        Node n = (Node) actionEvent.getSource();
        Stage addFilmStage = (Stage) n.getScene().getWindow();
        addFilmStage.close();
        conn.close();

        Stage addFilmSuccessStage = new Stage();
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("/fxml/addFilmSuccess.fxml")); //This path is temporary
        Parent root = signUpLoader.load();
        addFilmSuccessStage.setTitle("Registration successful!");
        addFilmSuccessStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        addFilmSuccessStage.setResizable(false);
        addFilmSuccessStage.show();
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
