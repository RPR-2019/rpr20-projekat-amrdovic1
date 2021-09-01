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
    public Label seatError;

    @FXML
    public void initialize()
    {

    }

    public void seatsClick(ActionEvent actionEvent) throws IOException {
        Stage chooseSeatsStage = new Stage();
        FXMLLoader chooseSeatsLoader = null;
        int n = ChosenProjection.getChosenProjection().getCinemaid();
        String path = "/fxml/chooseSeats" + n + ".fxml";
        chooseSeatsLoader = new FXMLLoader(getClass().getResource(path)); //This path is temporary
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

    public void finishClick(ActionEvent actionEvent) throws IOException
    {
        if (ChosenSeats.getSeats().isEmpty())
        {
            seatError.setText("You must choose at least one seat in order to proceed to payment!");
        }
        else
        {
            Stage reservationPaymentStage = new Stage();
            FXMLLoader reservationPaymentLoader = new FXMLLoader(getClass().getResource("/fxml/reservationPayment.fxml")); //This path is temporary
            Parent root = reservationPaymentLoader.load();
            reservationPaymentStage.setTitle("Choose food and drinks");
            reservationPaymentStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            reservationPaymentStage.setResizable(false);
            reservationPaymentStage.show();
        }
    }
}
