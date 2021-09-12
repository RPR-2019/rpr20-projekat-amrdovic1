package RPRMovieApp.controllers.user.reservation;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class ReservationPaymentController
{

    public RadioButton cinemaCard;
    public RadioButton creditCard;
    public RadioButton cash;
    public Label errorText;
    public Button finishBtn;

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException
    {
        cDAO = CinemaDAO.getInstance();
    }

    public void close(ActionEvent actionEvent)
    {
        Node n = (Node) actionEvent.getSource();
        Stage reservationStage = (Stage) n.getScene().getWindow();
        reservationStage.close();
    }

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

    public void finishClick(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (!(cinemaCard.isSelected() || creditCard.isSelected() || cash.isSelected()))
        {
            errorText.setText("You must select a payment method!");
        }
        else
        {
                int n = cDAO.getMaxTicketID() + 1;
                Collections.sort(CurrentData.getCurrentSeats());
                for (int i = 0; i < CurrentData.getCurrentSeats().size(); i++)
                {
                    int seat = CurrentData.getCurrentSeats().get(i);
                    Ticket t = new Ticket(n + i, seat, CurrentData.getCurrentScreening().getId(), CurrentData.getCurrentUser().getId());
                    cDAO.addTicket(t);
                }
            CinemaDAO.removeInstance();

            close(actionEvent);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reservation successful");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations, your reservation for film " + CurrentData.getCurrentFilm().getName() + " was successful!\n" +
                    "Thank you for using our app!");
            CurrentData.setCurrentSeats(new ArrayList<>());
            CurrentData.setCurrentFilm(null);
            CurrentData.setCurrentScreening(null);

            alert.showAndWait();
        }
    }
}
