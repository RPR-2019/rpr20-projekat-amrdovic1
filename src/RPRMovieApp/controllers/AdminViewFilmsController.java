package RPRMovieApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminViewFilmsController
{
    public Button addNewFilm;

    public void addNewFilmClick(ActionEvent actionEvent) throws IOException {
        Stage addFilmStage = new Stage();
        FXMLLoader addFilmLoader = new FXMLLoader(getClass().getResource("/fxml/addFilm.fxml")); //This path is temporary
        Parent root = addFilmLoader.load();
        addFilmStage.setTitle("Add new film");
        addFilmStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        addFilmStage.show();
    }

    public void findFilmsClick(ActionEvent actionEvent) throws IOException {
        Stage findFilmsStage = new Stage();
        FXMLLoader addFilmLoader = new FXMLLoader(getClass().getResource("/fxml/adminFindFilm.fxml")); //This path is temporary
        Parent root = addFilmLoader.load();
        findFilmsStage.setTitle("Find a film");
        findFilmsStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        findFilmsStage.show();
    }
}
