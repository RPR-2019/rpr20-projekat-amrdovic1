package RPRMovieApp.controllers.user.reservation.seatselection;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ChooseSeats4Controller
{
    public Button a1; public Button a2; public Button a3; public Button a4; public Button a5; public Button a6;public Button a7; public Button a8; public Button a9;public Button a10;
    public Button b1; public Button b2; public Button b3; public Button b4; public Button b5; public Button b6;public Button b7; public Button b8; public Button b9;public Button b10;
    public Button c1; public Button c2; public Button c3; public Button c4; public Button c5; public Button c6;public Button c7; public Button c8; public Button c9;public Button c10;
    public Button d1; public Button d2; public Button d3; public Button d4; public Button d5; public Button d6;public Button d7; public Button d8; public Button d9;public Button d10;
    public Button e1; public Button e2; public Button e3; public Button e4; public Button e5; public Button e6;public Button e7; public Button e8; public Button e9;public Button e10;
    public Button f1; public Button f2; public Button f3; public Button f4; public Button f5; public Button f6;public Button f7; public Button f8; public Button f9;public Button f10;
    public Button g1; public Button g2; public Button g3; public Button g4; public Button g5; public Button g6;public Button g7; public Button g8; public Button g9;public Button g10;
    public Button h1; public Button h2; public Button h3; public Button h4; public Button h5; public Button h6;public Button h7; public Button h8; public Button h9;public Button h10;
    public Button i1; public Button i2; public Button i3; public Button i4; public Button i5; public Button i6;public Button i7; public Button i8; public Button i9;public Button i10;
    public Button j1; public Button j2; public Button j3; public Button j4; public Button j5; public Button j6;public Button j7; public Button j8; public Button j9;public Button j10;
    public Button k1; public Button k2; public Button k3; public Button k4; public Button k5; public Button k6;public Button k7; public Button k8; public Button k9;public Button k10;
    public Button l1; public Button l2; public Button l3; public Button l4; public Button l5; public Button l6;public Button l7; public Button l8; public Button l9;public Button l10;

    private ArrayList<Integer> takenSeats = new ArrayList<>();

    public Button[][] seats;

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
        seats = new Button[][]
                {
                        {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10},
                        {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10},
                        {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10},
                        {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10},
                        {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10},
                        {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10},
                        {g1, g2, g3, g4, g5, g6, g7, g8, g9, g10},
                        {h1, h2, h3, h4, h5, h6, h7, h8, h9, h10},
                        {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10},
                        {j1, j2, j3, j4, j5, j6, j7, j8, j9, j10},
                        {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10},
                        {l1, l2, l3, l4, l5, l6, l7, l8, l9, l10}
                };
        takenSeats = cDAO.getTakenSeatsForScreening(CurrentData.getCurrentScreening().getId());
        String taken = "-fx-background-color: lightpink";
        String available = "-fx-background-color: yellowgreen";
        String selected = "-fx-background-color: lightblue";
        int i, j;
        for (i = 0; i < 12; i++)
        {
            for (j = 0; j < 10; j++)
            {
                Button b = seats[i][j];
                int p = 10*i + j + 856;
                if (takenSeats.contains(p))
                {
                    b.setStyle(taken);
                }
                else if (CurrentData.getCurrentSeats().contains(p)) //Though this can't happen before user selects a seat
                {
                    b.setStyle(selected);
                }
                else
                {
                    b.setStyle(available);
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            if (b.getStyle().equals(available)) //Take a free seat
                            {
                                b.setStyle(selected);
                                CurrentData.getCurrentSeats().add(p);
                            }
                            else if (b.getStyle().equals(selected)) //Free a taken seat
                            {
                                b.setStyle(available);
                                CurrentData.getCurrentSeats().remove(p);
                            }
                        }
                    });
                }
            }
        }
    }

    public void takeSeatsClick(ActionEvent actionEvent) throws SQLException, IOException {
        Node n = (Node) actionEvent.getSource();
        Stage seatsStage = (Stage) n.getScene().getWindow();
        seatsStage.close();
        CinemaDAO.removeInstance();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NOTE");
        alert.setHeaderText("This seat selection isn't final");
        alert.setContentText("You may proceed to select more\n" +
                "available seats before finalizing your reservation!");

        alert.showAndWait();
    }
}
