package RPRMovieApp.controllers.admin.adminfilms;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.Screening;
import RPRMovieApp.controllers.ChosenFilm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminAddScreeningsController
{
    public Label movieTitle;
    public Button addScreeningBtn;
    public DatePicker newDate;
    public Spinner<Integer> newTimeHour;
    public Spinner<Integer> newTimeMinute;
    public ChoiceBox<Integer> cinemaChoiceBox;
    public Label dateError;
    public Label screeningError;

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        cDAO = CinemaDAO.getInstance();
        movieTitle.setText(ChosenFilm.getChosen().getName() + "- screenings for week ");
        for (int i = 1; i <= 5; i++)
        {
            cinemaChoiceBox.getItems().add(i);
        }
    }

    public void addScreeningBtnClick(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        boolean error = false;
        if (newDate.getValue() == null)
        {
            dateError.setText("You must select a screening date!");
            error = true;
        }
        if (cinemaChoiceBox.getValue() == null)
        {
            screeningError.setText("You must select a cinema hall!");
            error = true;
        }
        if (!error)
        {
            int max = cDAO.getMaxScreeningID();
            LocalDate date = newDate.getValue();
            Screening s = new Screening(max, CurrentData.getCurrentFilm().getId(), date.getDayOfWeek().getValue(), newTimeHour.getValue(), newTimeMinute.getValue(), cinemaChoiceBox.getValue());
            cDAO.addScreening(s);

            Node n = (Node) actionEvent.getSource();
            Stage addScreeningStage = (Stage) n.getScene().getWindow();
            addScreeningStage.close();
            CinemaDAO.removeInstance();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Adding new screening successful!");
            alert.setContentText("You successfully added the screening for " + ChosenFilm.getChosen().getName());
            alert.showAndWait();
        }
    }
}
