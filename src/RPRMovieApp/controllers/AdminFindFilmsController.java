package RPRMovieApp.controllers;

import RPRMovieApp.models.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Locale;

public class AdminFindFilmsController
{
    public ListView filmList;
    public TextField searchText;
    public Button searchBtn;
    public Button backBtn;
    public Label noMoviesError;
    private PreparedStatement findAllFilms;
    private Connection conn;

    private ObservableList<Film> films = FXCollections.observableArrayList();
    private ObservableList<Film> filmsByTitle = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        findAllFilms = conn.prepareStatement("SELECT * FROM film");
        ResultSet rsfaf = findAllFilms.executeQuery();
        while (rsfaf.next())
        {
            films.add(new Film(rsfaf.getInt(1), rsfaf.getString(2), rsfaf.getInt(3), rsfaf.getInt(4), rsfaf.getInt(5), rsfaf.getInt(6)));
        }
        conn.close();
        filmList.setItems(films);
    }

    public void searchBtnClick(ActionEvent actionEvent)
    {
        if (searchText.getText().isBlank())
        {
            filmList.setItems(films);
            return;
        }
        filmsByTitle = FXCollections.observableArrayList();
        String raw = searchText.getText().trim().toLowerCase(Locale.ROOT);
        for (var f : films)
        {
            if (f.getName().trim().toLowerCase(Locale.ROOT).contains(raw))
            {
                filmsByTitle.add(f);
            }
        }
        filmList.setItems(filmsByTitle);
        if (filmList.getItems().isEmpty())
        {
            noMoviesError.setText("No movies found!");
        }
        else
        {
            noMoviesError.setText("");
        }
    }

    public void backBtnClick(ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage findFilmsStage = (Stage) n.getScene().getWindow();
        findFilmsStage.close();
    }
}
