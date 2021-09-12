package RPRMovieApp.controllers.user.userfilms;

import RPRMovieApp.CurrentData;
import RPRMovieApp.DAO.CinemaDAO;
import RPRMovieApp.beans.Film;
import RPRMovieApp.controllers.user.reservation.ReservationController;
import RPRMovieApp.beans.Day;
import RPRMovieApp.beans.Screening;
import RPRMovieApp.beans.ScreeningRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LatestMovieDetailController
{
    public Label movieTitle;
    public TableView<ScreeningRow> screeningTable;
    public TableColumn<ScreeningRow, String> screeningDay;
    public TableColumn<ScreeningRow, String> screeningHour;
    public TableColumn<ScreeningRow, Integer> screeningCinema;
    public TableColumn<ScreeningRow, String> selectScreening;
    public TextArea extendedInfo;

    private ObservableList<Screening> allScreenings = FXCollections.observableArrayList();

    private CinemaDAO cDAO;

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        cDAO = CinemaDAO.getInstance();
        allScreenings = FXCollections.observableArrayList(cDAO.getAllScreeningsForFilm(CurrentData.getCurrentFilm()));
        screeningDay.setCellValueFactory(features -> features.getValue().dayProperty());
        screeningHour.setCellValueFactory(features -> features.getValue().hourProperty());
        screeningCinema.setCellValueFactory(new PropertyValueFactory<ScreeningRow, Integer>("cinema"));
        selectScreening.setCellValueFactory(features -> features.getValue().selProperty());
        for (Screening s : allScreenings)
        {
            ScreeningRow sr = new ScreeningRow(s.getDayName(), s.getHourInfo(), s.getCinemaid(), "Select");
            screeningTable.getItems().add(sr);
        }
        extendedInfo.setText(CurrentData.getCurrentFilm().toString());
        screeningTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            var selected = screeningTable.getSelectionModel().getSelectedItem();
            Integer day = Day.valueOf(selected.getDay().toUpperCase()).ordinal() + 1;
            var hour_min = selected.getHour().split(":");
            Integer cinema = selected.getCinema();
            Integer hour = Integer.parseInt(hour_min[0]);
            Integer minute = Integer.parseInt(hour_min[1]);
            Film f = CurrentData.getCurrentFilm();
            Screening selectedScr = cDAO.getScreening(f.getId(), day, hour, minute, cinema);
            CurrentData.setCurrentScreening(selectedScr);
            if (cDAO.checkExistingTicketForUser(CurrentData.getCurrentUser(), selectedScr))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Reservation error");
                alert.setContentText("You already booked this screening!\nIn order to book it again, you have to remove your previous reservation.\n" );
                alert.showAndWait();
            }
            else
            {
                Stage newReservationStage = new Stage();
                FXMLLoader newReservationLoader = new FXMLLoader(getClass().getResource("/fxml/user/reservation/newReservation.fxml")); //This path is temporary
                Parent root = null;
                try {
                    root = newReservationLoader.load();
                    ReservationController nrc = newReservationLoader.getController();
                    newReservationStage.setTitle("Projection");
                    newReservationStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    newReservationStage.setResizable(false);
                    newReservationStage.show();
                    nrc.movieName.setText(nrc.movieName.getText() + " " + CurrentData.getCurrentFilm().getName());
                    nrc.screeningTime.setText(nrc.screeningTime.getText() + " " + CurrentData.getCurrentScreening().getDayName() + " " + CurrentData.getCurrentScreening().getHourInfo());
                    nrc.noOfTickets.setText(nrc.noOfTickets.getText() + " 0");
                    CinemaDAO.removeInstance();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
