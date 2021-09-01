package RPRMovieApp.controllers;

import javafx.fxml.FXML;

import java.sql.*;
import java.util.Collections;

public class ReservationThanksController
{
    private Connection conn;
    private PreparedStatement getMaxTicketID;
    private PreparedStatement insertTickets;


    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:" + System.getProperty("user.home") + "\\IdeaProjects\\RPRprojekat\\RPRMovieApp.db";
        conn = DriverManager.getConnection(url, "username", "password");
        getMaxTicketID = conn.prepareStatement("SELECT MAX(id) FROM ticket");
        ResultSet gmtid = getMaxTicketID.executeQuery();
        insertTickets = conn.prepareStatement("INSERT INTO ticket VALUES(?,?,?,?)");
        int n = gmtid.getInt(1) + 1;
        Collections.sort(ChosenSeats.getSeats());
        for (int i = 0; i < ChosenSeats.getSeats().size(); i++)
        {
            insertTickets.setInt(1,n + i);
            insertTickets.setInt(2, ChosenSeats.getSeats().get(i));
            insertTickets.setInt(3, ChosenProjection.getChosenProjection().getId());
            insertTickets.setInt(4, ChosenUser.getChosen().getId());
            insertTickets.execute();
        }
        conn.close();
        ChosenSeats.getSeats().removeAll(ChosenSeats.getSeats());
        Price.setPrice(0);
        ChosenFilm.setChosen(null);
        ChosenProjection.setChosenProjection(null);
    }
}
