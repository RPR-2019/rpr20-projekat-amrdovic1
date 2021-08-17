package RPRMovieApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminHomepageController
{

    public Label welcomeMessage;
    public Button viewFilms;

    @FXML
    public void initialize()
    {

    }

    public void signOutClick(ActionEvent actionEvent)
    {
    }


    public void viewFilmsClick(ActionEvent actionEvent) throws IOException {
        Stage viewFilmsStage = new Stage();
        FXMLLoader viewFilmsLoader = new FXMLLoader(getClass().getResource("/fxml/adminViewFilms.fxml")); //This path is temporary
        Parent root = viewFilmsLoader.load();
        AdminViewFilmsController rc = viewFilmsLoader.getController();
        viewFilmsStage.setTitle("View films");
        viewFilmsStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        viewFilmsStage.setResizable(false);
        viewFilmsStage.show();
    }
}
