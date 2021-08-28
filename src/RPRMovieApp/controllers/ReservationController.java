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

public class ReservationController
{
    public Label movieName;
    public Label screeningTime;
    public Label noOfTickets;
    public Label price;
    public Button seatsBtn;
    public Button cancelBtn;
    public Button finishBtn;
    public Button foodBtn;

    @FXML
    public void initialize()
    {

    }

    public void seatsClick(ActionEvent actionEvent) throws IOException {
        Stage chooseSeatsStage = new Stage();
        FXMLLoader chooseSeatsLoader = new FXMLLoader(getClass().getResource("/fxml/chooseSeats.fxml")); //This path is temporary
        Parent root = chooseSeatsLoader.load();
        chooseSeatsStage.setTitle("Choose seats");
        chooseSeatsStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        chooseSeatsStage.setMaximized(true);
        chooseSeatsStage.setResizable(false);
        chooseSeatsStage.show();
    }

    public void foodClick(ActionEvent actionEvent) throws IOException {
        Stage chooseFoodnDrinksStage = new Stage();
        FXMLLoader chooseFoodnDrinksLoader = new FXMLLoader(getClass().getResource("/fxml/chooseFoodnDrinks.fxml")); //This path is temporary
        Parent root = chooseFoodnDrinksLoader.load();
        chooseFoodnDrinksStage.setTitle("Choose food and drinks");
        chooseFoodnDrinksStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        chooseFoodnDrinksStage.setResizable(false);
        chooseFoodnDrinksStage.show();
    }
}
