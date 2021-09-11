package RPRMovieApp.controllers.admin.adminfilms;

import RPRMovieApp.controllers.ChosenFilm;
import RPRMovieApp.beans.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
        filmList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try {
                    conn = DriverManager.getConnection(url, "username", "password");
                    ChosenFilm.setChosen((Film)filmList.getSelectionModel().getSelectedItem());
                    Stage filmDetailsStage = new Stage();
                    FXMLLoader filmDetailsLoader = new FXMLLoader(getClass().getResource("/fxml/adminMovieDetail.fxml")); //This path is temporary
                    Parent root;
                    try {
                        root = filmDetailsLoader.load();
                        AdminMovieDetailController amdc = filmDetailsLoader.getController();
                        filmDetailsStage.setTitle("Movie details - " + ChosenFilm.getChosen().getName());
                        filmDetailsStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                        filmDetailsStage.setResizable(false);
                        filmDetailsStage.show();
                        amdc.selectedMovie.setText(ChosenFilm.getChosen().getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
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
