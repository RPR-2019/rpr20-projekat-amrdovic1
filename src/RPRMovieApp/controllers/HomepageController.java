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

public class HomepageController
{
    public Label welcomeMessage;
    public Button latest;
    public Button upcoming;
    public Button checkRes;
    public Button cinemaCard;

    @FXML
    public void initialize()
    {

    }

    public void signOutClick(ActionEvent actionEvent)
    {
    }


    public void latestClick(ActionEvent actionEvent) throws IOException
    {
        Stage latestStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/latestMovies.fxml")); //This path is temporary
        latestStage.setTitle("Now in cinemas");
        latestStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        latestStage.setResizable(false);
        latestStage.show();
    }

    public void upcomingClick(ActionEvent actionEvent) throws IOException
    {
        Stage upcomingStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/upcomingMovies.fxml")); //This path is temporary
        upcomingStage.setTitle("Upcoming movies");
        upcomingStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        upcomingStage.setResizable(false);
        upcomingStage.show();
    }

    public void checkResClick(ActionEvent actionEvent) throws IOException
    {
        Stage checkResStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/checkReservations.fxml")); //This path is temporary
        checkResStage.setTitle("Check reservations");
        checkResStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        checkResStage.setResizable(false);
        checkResStage.show();
    }

    public void cinemaCardClick(ActionEvent actionEvent) throws IOException
    {
        Stage cinemaCardStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/cinemaCard.fxml")); //This path is temporary
        cinemaCardStage.setTitle("Cinema card");
        cinemaCardStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        cinemaCardStage.setResizable(false);
        cinemaCardStage.show();
    }
}
