package RPRMovieApp.controllers.user.reservation;

import RPRMovieApp.controllers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Collections;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ReservationPaymentController
{

    public RadioButton cinemaCard;
    public RadioButton creditCard;
    public RadioButton cash;
    public Label errorText;
    public Button finishBtn;

    private Connection conn;
    private PreparedStatement insertTickets;
    private PreparedStatement getMaxTicketID;

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
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
            try {
                conn = DriverManager.getConnection(url, "username", "password");
                getMaxTicketID = conn.prepareStatement("SELECT MAX(id) FROM ticket");
                ResultSet gmtid = getMaxTicketID.executeQuery();
                int n = gmtid.getInt(1) + 1;
                Collections.sort(ChosenSeats.getSeats());
                getMaxTicketID.close();
                conn.close();
                conn = DriverManager.getConnection(url, "username", "password");
                for (int i = 0; i < ChosenSeats.getSeats().size(); i++)
                {
                    insertTickets = conn.prepareStatement("INSERT INTO ticket VALUES(?,?,?,?)");
                    insertTickets.setInt(1, n + i);
                    insertTickets.setInt(2, ChosenSeats.getSeats().get(i));
                    insertTickets.setInt(3, ChosenProjection.getChosenProjection().getId());
                    insertTickets.setInt(4, ChosenUser.getChosen().getId());
                    insertTickets.executeUpdate();
                    insertTickets.close();
                }
                ChosenSeats.getSeats().removeAll(ChosenSeats.getSeats());
                Price.setPrice(0);
                ChosenFilm.setChosen(null);
                ChosenProjection.setChosenProjection(null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally
            {
                try {
                    if (conn != null)
                    {
                        conn.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

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
