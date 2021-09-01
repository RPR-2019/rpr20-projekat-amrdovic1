package RPRMovieApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ReservationPaymentController
{

    public RadioButton cinemaCard;
    public RadioButton creditCard;
    public RadioButton cash;
    public Label errorText;
    public Button finishBtn;

    public void cinemaCardClick(ActionEvent actionEvent)
    {
        cinemaCard.setSelected(true);
        creditCard.setSelected(false);
        cash.setSelected(false);
    }

    public void creditCardClick(ActionEvent actionEvent)
    {
        cinemaCard.setSelected(false);
        creditCard.setSelected(true);
        cash.setSelected(false);
    }

    public void cashClick(ActionEvent actionEvent)
    {
        cinemaCard.setSelected(false);
        creditCard.setSelected(false);
        cash.setSelected(true);
    }

    public void finishClick(ActionEvent actionEvent) throws IOException {
        if (!(cinemaCard.isSelected() || creditCard.isSelected() || cash.isSelected()))
        {
            errorText.setText("You must select a payment method!");
        }
        else
        {
            Stage reservationThanks = new Stage();
            FXMLLoader reservationThanksLoader = new FXMLLoader(getClass().getResource("/fxml/reservationThanks.fxml")); //This path is temporary
            Parent root = reservationThanksLoader.load();
            reservationThanks.setTitle("Thank you for your reservation!");
            reservationThanks.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            reservationThanks.setResizable(false);
            reservationThanks.show();
        }
    }
}
