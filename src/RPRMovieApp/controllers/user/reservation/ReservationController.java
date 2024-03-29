package RPRMovieApp.controllers.user.reservation;

import RPRMovieApp.CurrentData;
import RPRMovieApp.beans.Screening;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    public void close(ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage reservationStage = (Stage) n.getScene().getWindow();
        reservationStage.close();
    }

    public void seatsClick(ActionEvent actionEvent) throws IOException {
        Stage chooseSeatsStage = new Stage();
        FXMLLoader chooseSeatsLoader = null;
        Screening s = CurrentData.getCurrentScreening();
        int n = s.getCinemaid();
        String path = "/fxml/user/reservation/seatSelection/chooseSeats" + n + ".fxml";
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
        FXMLLoader chooseFoodnDrinksLoader = new FXMLLoader(getClass().getResource("/fxml/user/reservation/chooseFoodnDrinks.fxml")); //This path is temporary
        Parent root = chooseFoodnDrinksLoader.load();
        chooseFoodnDrinksStage.setTitle("Choose food and drinks");
        chooseFoodnDrinksStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        chooseFoodnDrinksStage.setResizable(false);
        chooseFoodnDrinksStage.show();
    }

    public void finishClick(ActionEvent actionEvent) throws IOException
    {
        if (CurrentData.getCurrentSeats().isEmpty())
        {
            seatError.setText("You must choose at least one seat in order to proceed to payment!");
        }
        else
        {
            close(actionEvent);
            Stage reservationPaymentStage = new Stage();
            FXMLLoader reservationPaymentLoader = new FXMLLoader(getClass().getResource("/fxml/user/reservation/reservationPayment.fxml")); //This path is temporary
            Parent root = reservationPaymentLoader.load();
            reservationPaymentStage.setTitle("Choose payment method");
            reservationPaymentStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            reservationPaymentStage.setResizable(false);
            reservationPaymentStage.show();
        }
    }
}
