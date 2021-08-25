package RPRMovieApp.controllers;

import RPRMovieApp.models.Screening;
import RPRMovieApp.models.ScreeningRow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatestMovieDetailController
{
    public Label movieTitle;
    public TableView<ScreeningRow> screeningTable;
    public TableColumn<ScreeningRow, String> screeningDay;
    public TableColumn<ScreeningRow, String> screeningHour;
    public TableColumn<ScreeningRow, String> selectScreening;
    public TextArea extendedInfo;

    private Connection conn;
    private PreparedStatement getAllScreenings;

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
            allScreenings.add(new Screening(rsgas.getInt(1), rsgas.getInt(2), rsgas.getInt(3), rsgas.getInt(4), rsgas.getInt(5)));
        }
        System.out.println("Found " + allScreenings.size() + " screenings\n");
        screeningDay.setCellValueFactory(features -> features.getValue().dayProperty());
        screeningHour.setCellValueFactory(features -> features.getValue().hourProperty());
        selectScreening.setCellValueFactory(features -> features.getValue().selProperty());
        for (Screening s : allScreenings)
        {
            ScreeningRow sr = new ScreeningRow(s.getDayName(), s.getHourInfo(), "Select");
            screeningTable.getItems().add(sr);
        }
        extendedInfo.setText(ChosenFilm.getChosen().toString());
    }
}
