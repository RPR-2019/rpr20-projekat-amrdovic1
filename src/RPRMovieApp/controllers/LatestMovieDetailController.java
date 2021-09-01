package RPRMovieApp.controllers;

import RPRMovieApp.models.Day;
import RPRMovieApp.models.Screening;
import RPRMovieApp.models.ScreeningRow;
import javafx.beans.property.SimpleIntegerProperty;
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

    private Connection conn;
    private PreparedStatement getAllScreenings;
    private PreparedStatement checkExistingTicket;
    private PreparedStatement getSelectedScreening;

    private ObservableList<Screening> allScreenings = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        Connection conn = DriverManager.getConnection(url, "username", "password");
        getAllScreenings = conn.prepareStatement("SELECT p.* FROM projection p, film f WHERE f.id=? AND p.filmid = f.id");
        getAllScreenings.setInt(1, ChosenFilm.getChosen().getId());
        ResultSet rsgas = getAllScreenings.executeQuery();
        while (rsgas.next())
        {
            allScreenings.add(new Screening(rsgas.getInt(1), rsgas.getInt(2), rsgas.getInt(3), rsgas.getInt(4), rsgas.getInt(5), rsgas.getInt(6)));
        }
        screeningDay.setCellValueFactory(features -> features.getValue().dayProperty());
        screeningHour.setCellValueFactory(features -> features.getValue().hourProperty());
        screeningCinema.setCellValueFactory(new PropertyValueFactory<ScreeningRow, Integer>("cinema"));
        selectScreening.setCellValueFactory(features -> features.getValue().selProperty());
        for (Screening s : allScreenings)
        {
            ScreeningRow sr = new ScreeningRow(s.getDayName(), s.getHourInfo(), s.getCinemaid(), "Select");
            screeningTable.getItems().add(sr);
        }
        extendedInfo.setText(ChosenFilm.getChosen().toString());
        screeningTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            var selected = screeningTable.getSelectionModel().getSelectedItem();
            Integer day = Day.valueOf(selected.getDay().toUpperCase()).ordinal() + 1;
            var hour_min = selected.getHour().split(":");
            Integer cinema = selected.getCinema();
            Integer hour = Integer.parseInt(hour_min[0]);
            Integer minute = Integer.parseInt(hour_min[1]);
            //Find a way to get this info only by passing screening id
            try
            {
                checkExistingTicket = conn.prepareStatement("SELECT * FROM ticket WHERE userid=? AND projectionid=?");
                checkExistingTicket.setInt(1, ChosenUser.getChosen().getId());


                getSelectedScreening = conn.prepareStatement("SELECT * FROM projection WHERE filmid=? AND dayid=? AND hour=? AND minute=? AND hallid=?");
                getSelectedScreening.setInt(1, ChosenFilm.getChosen().getId());
                getSelectedScreening.setInt(2, day);
                getSelectedScreening.setInt(3, hour);
                getSelectedScreening.setInt(4, minute);
                getSelectedScreening.setInt(5, cinema);
                ResultSet rsgss = getSelectedScreening.executeQuery(); //There should only be one!
                int scr_id = rsgss.getInt(1);
                ChosenProjection.setChosenProjection(new Screening(rsgss.getInt(1), rsgss.getInt(2), rsgss.getInt(3), rsgss.getInt(4), rsgss.getInt(5), rsgss.getInt(6)));

                checkExistingTicket.setInt(2, ChosenProjection.getChosenProjection().getId());
                ResultSet rscet = checkExistingTicket.executeQuery();
                if (rscet.next()) //User can't book the same screening twice
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
                    FXMLLoader newReservationLoader = new FXMLLoader(getClass().getResource("/fxml/newReservation.fxml")); //This path is temporary
                    Parent root = newReservationLoader.load();
                    ReservationController nrc = newReservationLoader.getController();
                    newReservationStage.setTitle("Projection");
                    newReservationStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    newReservationStage.setResizable(false);
                    newReservationStage.show();
                    nrc.movieName.setText(nrc.movieName.getText() + " " + ChosenFilm.getChosen().getName());
                    nrc.screeningTime.setText(nrc.screeningTime.getText() + " " + ChosenProjection.getChosenProjection().getDayName() + " " + ChosenProjection.getChosenProjection().getHourInfo());
                    nrc.noOfTickets.setText(nrc.noOfTickets.getText() + " 0");
                    conn.close();
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }

        });
    }
}
